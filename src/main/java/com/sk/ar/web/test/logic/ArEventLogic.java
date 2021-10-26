package com.sk.ar.web.test.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.ar.web.test.define.ErrorCodeDefine;
import com.sk.ar.web.test.dto.request.*;
import com.sk.ar.web.test.dto.response.ApiResultObjectDto;
import com.sk.ar.web.test.dto.response.ArEventDetailResDto;
import com.sk.ar.web.test.dto.response.ArEventResDto;
import com.sk.ar.web.test.dto.response.ArEventWinningResDto;
import com.sk.ar.web.test.entity.*;
import com.sk.ar.web.test.service.ArEventService;
import com.sk.ar.web.test.service.ExcelService;
import com.sk.ar.web.test.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ArEventLogic {
    private final int httpSuccessCode = HttpStatus.OK.value();

    @Autowired
    private ArEventService arEventService;

    @Autowired
    private ExcelService excelService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * AR 이벤트 저장하기
     * @param jsonStr
     * @return
     */
    @Transactional
    public ApiResultObjectDto saveArEventLogic(String jsonStr, MultipartFile attendCodeExcelFile) {
        int resultCode = httpSuccessCode;

        Map<String, Object>resultMap = new HashMap<>();

        try {
            //json string 인코딩 변경
            String encodingJsonStr = "";
            if (!StringUtils.isEmpty(jsonStr)) {
                encodingJsonStr = new String(jsonStr.getBytes("iso-8859-1"), "utf-8");
            }
            EventSaveDto eventSaveDto = objectMapper.readValue(encodingJsonStr, EventSaveDto.class);

            /**
             * EVENT_BASE 저장
             */
            String eventId = arEventService.saveEventBase(WebEventBaseEntity.of(arEventService.findLastWebEventBase(), eventSaveDto.getEventBaseInfo()));

            log.info("eventId >> " + eventId);

            if (StringUtils.isNotEmpty(eventId)) {
                /**
                 * AR_EVENT 저장
                 */
                int arEventId = arEventService.saveEvent(ArEventEntity.of(eventId, eventSaveDto.getArEventInfo()));

                if (arEventId > 0) {
                    /**
                     * AR_EVENT_ATTEND_TIME 저장
                     */
                    arEventService.saveAllEventAttendTime(arEventId, convertEventAttendTimeDtoListToArEventAttendEntityList(eventSaveDto.getArEventInfo().getArEventAttendTimeInfo()));
                    /**
                     * AR_EVENT_BUTTON 저장
                     */
                    arEventService.saveEventButton(ArEventButtonEntity.of(arEventId, eventSaveDto.getArEventButtonInfo()));

                    /**
                     * AR_EVENT_OBJECT 저장 (이미지스캐닝이 아날떄만)
                     */
                    if (!"SCANNING".equals(eventSaveDto.getArEventInfo().getEventLogicalType())) {
                        List<ArEventObjectEntity> eventObjectEntityList = convertEventObjectDtoListToArEventObjectEntityList(eventSaveDto.getArEventObjectInfo());
                        eventObjectEntityList
                                .stream()
                                .filter(Objects::nonNull)
                                .forEach(objectEntity -> {
                                    objectEntity.setArEventId(arEventId);
                                    objectEntity.setCreatedDate(DateUtils.returnNowDate());
                                });
                        arEventService.saveAllArEventObject(eventObjectEntityList);
                    } else {
                        /**
                         * AR_EVENT_SCANNING_IMAGE(이미지스캔형일때만)
                         */
                        List<ArEventScanningImageEntity> arEventImageScanningEntityList = convertEventScanningImageDtoListToArEventImageScanningEntityList(eventSaveDto.getArEventScanningImageInfo());
                        arEventImageScanningEntityList
                                .stream()
                                .filter(Objects::nonNull)
                                .forEach(entity -> {
                                    entity.setArEventId(arEventId);
                                    entity.setCreatedDate(DateUtils.returnNowDate());
                                });

                        arEventService.saveAllEventImageScanning(arEventImageScanningEntityList);
                    }
                    /**
                     * AR_EVENT_LOGICAL 저장
                     */
                    arEventService.saveEventLogical(ArEventLogicalEntity.of(arEventId, eventSaveDto.getArEventLogicalInfo()));

                }

                /**
                 * AR_EVENT_WINNING, AR_EVENT_WINNING_BUTTON 저장
                 */
                //당첨정보, 당첨버튼정보 저장하기
                int i = 0;
                List<ArEventWinningEntity> arEventWinningEntityList = convertEventWinningDtoToArEventWinningEntityList(eventSaveDto.getArEventWinningInfo());
                for (ArEventWinningEntity arEventWinningEntity : arEventWinningEntityList) {

                    arEventWinningEntity.setArEventId(arEventId);
                    arEventWinningEntity.setCreatedDate(DateUtils.returnNowDate());
                    //당첨정보 저장
                    arEventService.saveEventWinning(arEventWinningEntity);

                    //당첨버튼 정보 저장하기
                    List<ArEventWinningButtonEntity> arEventWinningButtonEntityList = convertEventWinningButtonDtoToArEventWinningButtonEntityList(eventSaveDto.getArEventWinningInfo().get(i).getArEventWinningButtonInfo());
                    for (ArEventWinningButtonEntity buttonEntity : arEventWinningButtonEntityList) {

                        buttonEntity.setArEventWinningId(arEventService.findEventWinningEntityByArEventId(arEventId).getArEventWinningId());
                        buttonEntity.setCreatedDate(DateUtils.returnNowDate());

                        arEventService.saveEventWinningButton(buttonEntity);
                    }
                    i++;
                }

                /**
                 * AR_EVENT_HTML저장
                 */
                arEventService.saveAllEventHtml(arEventId, eventSaveDto.getArEventHtmlInfo());

                //참여코드 엑셀파일 추출 후 저장하기 시작
                if (attendCodeExcelFile != null) {
                    List<ArEventGateCodeEntity>arEventGateCodeEntityList = new ArrayList<>();
                    List<Map<String, Object>> attendCodeList = excelService.extractionAttendCodeByExcelFile(attendCodeExcelFile);
                    attendCodeList.forEach(attendCodeMap -> {
                        ArEventGateCodeEntity gateCodeEntity = new ArEventGateCodeEntity();
                        gateCodeEntity.setEventId(eventId);
                        gateCodeEntity.setAttendCode(String.valueOf(attendCodeMap.get("A")));
                        gateCodeEntity.setUseYn(false);

                        arEventGateCodeEntityList.add(gateCodeEntity);
                    });
                    arEventService.saveAllArEventGateCode(arEventGateCodeEntityList);
                } else {
                    /**
                     * TODO 엑셀파일 없을때 코드 자동 생성 후 저장하기 기능 추가
                     */
                }
                //참여코드 엑셀파일 추출 후 저장하기 끝

            }

            resultMap.put("eventId", eventId);

        } catch (JsonProcessingException jpe) {
            log.info("json parse error");
            jpe.printStackTrace();
        } catch (UnsupportedEncodingException uee) {
            log.info("json encoding error");
            uee.printStackTrace();;
        }

        return new ApiResultObjectDto().builder()
                .result(resultMap)
                .resultCode(resultCode)
                .traceNo("")
                .build();
    }

    /**
     * AR 이벤트 수정하기
     */
    @Transactional
    public ApiResultObjectDto updateArEventLogic(String eventId, String jsonStr, MultipartFile attendCodeExcelFile) {
        int resultCode = httpSuccessCode;

        EventSaveDto eventSaveDto = new EventSaveDto();

        try {
            String encodingJsonStr = "";
            if (!StringUtils.isEmpty(jsonStr)) {
                encodingJsonStr = new String(jsonStr.getBytes("iso-8859-1"), "utf-8");
            }
            eventSaveDto = objectMapper.readValue(encodingJsonStr, EventSaveDto.class);
        } catch (JsonProcessingException jpe) {
            log.info("json parse error");
            jpe.printStackTrace();
        } catch (UnsupportedEncodingException uee) {
            log.info("json encoding error");
            uee.printStackTrace();;
        }

        WebEventBaseEntity webEventBase = arEventService.findEventBase(eventId);

        //WEB_EVENT_BASE 없으면 에러처리
        if (webEventBase == null) {

            resultCode = ErrorCodeDefine.CUSTOM_ERROR_WEB_EVENT_BASE_NULL.code();
            log.error(ErrorCodeDefine.getLogErrorMessage(resultCode));

        } else {
            /**
             * WEB_EVENT_BASE 수정
             */
            arEventService.saveEventBase(WebEventBaseEntity.updateOf(webEventBase, eventId, eventSaveDto.getEventBaseInfo()));

            /**
             * AR_EVENT 수정
             */
            ArEventEntity arEventEntity = arEventService.findArEventByEventId(eventId);
            arEventService.saveEvent(ArEventEntity.updateOf(arEventEntity, eventId, eventSaveDto.getArEventInfo()));
            /**
             * AR_EVENT_ATTEND_TIME 삭제 후 저장
             */
            arEventService.deleteArEventAttendTimeByArEventId(arEventEntity.getArEventId());
            arEventService.saveAllEventAttendTime(arEventEntity.getArEventId(), convertEventAttendTimeDtoListToArEventAttendEntityList(eventSaveDto.getArEventInfo().getArEventAttendTimeInfo()));

            /**
             * AR_EVENT_BUTTON 수정
             */
            ArEventButtonEntity arEventButtonEntity = arEventService.findArEventButtonByArEventId(arEventEntity.getArEventId());
            arEventService.saveEventButton(ArEventButtonEntity.updateOf(arEventButtonEntity, eventSaveDto.getArEventButtonInfo()));

            /**
             * AR_EVENT_OBJECT 수정 (이미지스캐닝이 아날떄만)
             */
            if (!"SCANNING".equals(eventSaveDto.getArEventInfo().getEventLogicalType())) {
                //AR_EVENT_OBJECT 삭제
                arEventService.deleteArEventObjectByArEventId(arEventEntity.getArEventId());

                List<ArEventObjectEntity> eventObjectEntityList = convertEventObjectDtoListToArEventObjectEntityList(eventSaveDto.getArEventObjectInfo());
                eventObjectEntityList
                        .stream()
                        .filter(Objects::nonNull)
                        .forEach(objectEntity -> {
                            objectEntity.setArEventId(arEventEntity.getArEventId());
                            objectEntity.setCreatedDate(DateUtils.returnNowDate());
                        });

                arEventService.saveAllArEventObject(eventObjectEntityList);

            } else {
                /**
                 * AR_EVENT_SCANNING_IMAGE 수정 (이미지스캔형일때만)
                 */
                //AR_EVENT_SCANNING_IMAGE 삭제
                arEventService.deleteArEventScanningImageByArEventId(arEventEntity.getArEventId());

                List<ArEventScanningImageEntity> arEventImageScanningEntityList = convertEventScanningImageDtoListToArEventImageScanningEntityList(eventSaveDto.getArEventScanningImageInfo());
                arEventImageScanningEntityList
                        .stream()
                        .filter(Objects::nonNull)
                        .forEach(entity -> {
                            entity.setArEventId(arEventEntity.getArEventId());
                            entity.setCreatedDate(DateUtils.returnNowDate());
                        });

                arEventService.saveAllEventImageScanning(arEventImageScanningEntityList);
            }
            /**
             * AR_EVENT_LOGICAL 수정
             */
            ArEventLogicalEntity arEventLogicalEntity = arEventService.findArEventLogicalByArEventId(arEventEntity.getArEventId());
            arEventService.saveEventLogical(ArEventLogicalEntity.updateOf(arEventLogicalEntity, eventSaveDto.getArEventLogicalInfo()));

            /**
             * AR_EVENT_WINNING, AR_EVENT_WINNING_BUTTON 수정
             */
            //당첨정보, 당첨버튼정보 수정하기
            List<Integer> arEventWinningIdList = arEventService.findArEventWinningIdListByArEventId(arEventEntity.getArEventId());
            //AR_EVENT_WINNING 삭제
            arEventService.deleteArEventWinningByArEventId(arEventEntity.getArEventId());
            //AR_EVENT_WINNING_BUTTON 삭제
            arEventService.deleteArEventWinningButtonByArEventWinningIdIn(arEventWinningIdList);


            int i = 0;
            List<ArEventWinningEntity> arEventWinningEntityList = convertEventWinningDtoToArEventWinningEntityList(eventSaveDto.getArEventWinningInfo());
            for (ArEventWinningEntity arEventWinningEntity : arEventWinningEntityList) {

                arEventWinningEntity.setArEventId(arEventEntity.getArEventId());
                arEventWinningEntity.setCreatedDate(DateUtils.returnNowDate());
                //당첨정보 저장
                arEventService.saveEventWinning(arEventWinningEntity);

                //당첨버튼 정보 저장하기
                List<ArEventWinningButtonEntity> arEventWinningButtonEntityList = convertEventWinningButtonDtoToArEventWinningButtonEntityList(eventSaveDto.getArEventWinningInfo().get(i).getArEventWinningButtonInfo());
                for (ArEventWinningButtonEntity buttonEntity : arEventWinningButtonEntityList) {

                    buttonEntity.setArEventWinningId(arEventService.findEventWinningEntityByArEventId(arEventEntity.getArEventId()).getArEventWinningId());
                    buttonEntity.setCreatedDate(DateUtils.returnNowDate());

                    arEventService.saveEventWinningButton(buttonEntity);
                }
                i++;
            }

            /**
             * AR_EVENT_HTML저장
             */
            arEventService.deleteArEventHtmlByArEventId(arEventButtonEntity.getArEventId());
            arEventService.saveAllEventHtml(arEventEntity.getArEventId(), eventSaveDto.getArEventHtmlInfo());

            //참여코드 엑셀파일 추출 후 저장하기 시작
            if (attendCodeExcelFile != null) {
                //AR_EVENT_GATE_CODE 삭제
                arEventService.deleteArEventGateCodeByEventId(eventId);

                //신규 저장
                List<ArEventGateCodeEntity>arEventGateCodeEntityList = new ArrayList<>();
                List<Map<String, Object>> attendCodeList = excelService.extractionAttendCodeByExcelFile(attendCodeExcelFile);
                attendCodeList.forEach(attendCodeMap -> {
                    ArEventGateCodeEntity gateCodeEntity = new ArEventGateCodeEntity();
                    gateCodeEntity.setEventId(eventId);
                    gateCodeEntity.setAttendCode(String.valueOf(attendCodeMap.get("A")));
                    gateCodeEntity.setUseYn(false);

                    arEventGateCodeEntityList.add(gateCodeEntity);
                });
                arEventService.saveAllArEventGateCode(arEventGateCodeEntityList);
            } else {
                /**
                 * TODO 엑셀파일 없을때 코드 자동 생성 후 저장하기 기능 추가
                 */
            }
            //참여코드 엑셀파일 추출 후 저장하기 끝

        }

        Map<String, Object>resultMap = new HashMap<>();
        resultMap.put("eventId", eventId);

        return new ApiResultObjectDto().builder()
                .result(resultMap)
                .resultCode(resultCode)
                .traceNo("")
                .build();

    }

    public ApiResultObjectDto getArEventDetailLogic(String eventId) {
        int resultCode = httpSuccessCode;

        if (StringUtils.isEmpty(eventId)) {
            resultCode = ErrorCodeDefine.CUSTOM_ERROR_EVENT_ID_NULL.code();
            log.error(ErrorCodeDefine.getLogErrorMessage(resultCode));

        } else {

            WebEventBaseEntity webEventBaseInfo = arEventService.findEventBase(eventId);

            ArEventResDto arEventResDto = arEventService.findArEventByEventIdOfResDto(eventId);

            //AR_EVENT 정보가 없으면 에러처리
            if (arEventResDto == null) {
                resultCode = ErrorCodeDefine.CUSTOM_ERROR_AR_EVENT_INFO_NULL.code();
                log.error(ErrorCodeDefine.getLogErrorMessage(resultCode));
            } else {
                int arEventId = arEventResDto.getArEventId();
                //AR_EVENT_ATTEND_TIME 정보
                arEventResDto.setArEventAttendTimeInfo(arEventService.findAllArEventAttendTimeByArEventId(arEventId));
                //AR_EVENT_BUTTON 정보
                ArEventButtonEntity arEventButtonEntity = arEventService.findArEventButtonByArEventId(arEventId);
                //AR_EVENT_OBJECT 정보
                List<ArEventObjectEntity> arEventObjectEntityList = arEventService.findAllArEventObjectByArEventId(arEventId);

                ArEventLogicalEntity arEventLogicalEntity = null;
                List<ArEventScanningImageEntity> arEventScanningImageEntityList = null;

                //오브젝트 종류가 스캐닝이 아닐때 AR_EVENT_LOGICAL 정보 주입하기
                if (!"SCANNING".equals(arEventResDto.getEventLogicalType())) {
                    arEventLogicalEntity = arEventService.findArEventLogicalByArEventId(arEventId);
                } else {
                    arEventScanningImageEntityList = arEventService.findAllArEventScanningImageByEventId(arEventId);
                }
                //AR_EVENT_WINNING 정보
                List<ArEventWinningResDto> arEventWinningResDtoList = arEventService.findAllArEventWinningByArEventIdOfResDto(arEventId);
                //AR_EVENT_WINNING_BUTTON 정보 주입
                arEventWinningResDtoList
                        .stream()
                        .filter(Objects::nonNull)
                        .forEach(resDto -> {
                            resDto.setArEventWinningButtonInfo(
                                    arEventService.findAllArEventWinningButtonByArEventWinningId(resDto.getArEventWinningId())
                            );
                        });
                //AR_EVENT_HTML 정보
                List<ArEventHtmlEntity> arEventHtmlList = arEventService.findAllArEventHtmlByArEventId(arEventId);

                ArEventDetailResDto arEventDetailResDto = new ArEventDetailResDto().builder()
                        .webEventBaseInfo(webEventBaseInfo) //WEB_EVENT_BASE 정보
                        .arEventInfo(arEventResDto) //AR_EVENT 정보
                        .arEventButtonInfo(arEventButtonEntity) //AR_EVENT_BUTTON
                        .arEventObjectInfo(arEventObjectEntityList) //AR_EVENT_OBJECT
                        .arEventLogicalInfo(arEventLogicalEntity)   //AR_EVENT_LOGICAL
                        .arEventScanningImageInfo(arEventScanningImageEntityList)   //AR_EVENT_SCANNING_IMAGE
                        .arEventWinningInfo(arEventWinningResDtoList)   //AR_EVENT_WINNING
                        .arEventHtmlInfo(arEventHtmlList)   //AR_EVENT_HTML
                        .arEventUrl("http://ar.skevent.com/" + eventId) //TODO 추후 정식 URL패턴으로 변경 해야함
                        .build();

                return new ApiResultObjectDto().builder()
                        .result(arEventDetailResDto)
                        .resultCode(resultCode)
                        .traceNo("")
                        .build();
            }
        }
        return new ApiResultObjectDto();
    }

    private List<ArEventObjectEntity> convertEventObjectDtoListToArEventObjectEntityList(List<EventObjectDto> eventObjectDtoList) {
        return eventObjectDtoList.
                stream()
                .map(dto -> modelMapper.map(dto, ArEventObjectEntity.class))
                .collect(Collectors.toList());
    }

    private List<ArEventScanningImageEntity> convertEventScanningImageDtoListToArEventImageScanningEntityList(List<EvenScanningImageDto> evenScanningImageDtoList) {
        return evenScanningImageDtoList.
                stream()
                .map(dto -> modelMapper.map(dto, ArEventScanningImageEntity.class))
                .collect(Collectors.toList());
    }

    private List<ArEventWinningEntity> convertEventWinningDtoToArEventWinningEntityList(List<EventWinningDto>eventWinningDtoList) {
        return eventWinningDtoList
                .stream()
                .map(dto -> modelMapper.map(dto, ArEventWinningEntity.class))
                .collect(Collectors.toList());
    }

    private List<ArEventWinningButtonEntity> convertEventWinningButtonDtoToArEventWinningButtonEntityList(List<EventWinningButtonDto>eventWinningButtonDtoList) {
        return eventWinningButtonDtoList
                .stream()
                .map(dto -> modelMapper.map(dto, ArEventWinningButtonEntity.class))
                .collect(Collectors.toList());
    }

    private List<ArEventAttendTimeEntity> convertEventAttendTimeDtoListToArEventAttendEntityList(List<EventAttendTimeDto>eventAttendTimeDtoList) {
        return eventAttendTimeDtoList
                .stream()
                .map(dto -> modelMapper.map(dto, ArEventAttendTimeEntity.class))
                .collect(Collectors.toList());

    }
}
