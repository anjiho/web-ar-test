package com.sk.ar.web.test.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.ar.web.test.dto.request.*;
import com.sk.ar.web.test.entity.*;
import com.sk.ar.web.test.service.ArEventService;
import com.sk.ar.web.test.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import springfox.documentation.annotations.Cacheable;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Repository
public class ArEventLogic {
    private final int httpSuccessCode = HttpStatus.OK.value();

    @Autowired
    private ArEventService arEventService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * AR 이벤트 저장하기
     * @param jsonStr
     * @return
     */
    @Transactional
    public ApiResultObjectDto saveArEventLogic(String jsonStr) {
        int resultCode = httpSuccessCode;

        Map<String, Object>resultMap = new HashMap<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            EventSaveDto eventSaveDto = objectMapper.readValue(jsonStr, EventSaveDto.class);

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
            }

            resultMap.put("eventId", eventId);

        } catch (JsonProcessingException jpe) {
            log.info("json parse error");
            jpe.printStackTrace();
        }

        return new ApiResultObjectDto().builder()
                .result(resultMap)
                .resultCode(resultCode)
                .traceCd("")
                .build();
    }

    /**
     * AR 이벤트 수정하기
     */
    public void updateArEventLogic() {

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

    public List<ArEventAttendTimeEntity> convertEventAttendTimeDtoListToArEventAttendEntityList(List<EventAttendTimeDto>eventAttendTimeDtoList) {
        return eventAttendTimeDtoList
                .stream()
                .map(dto -> modelMapper.map(dto, ArEventAttendTimeEntity.class))
                .collect(Collectors.toList());

    }
}
