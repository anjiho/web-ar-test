package com.sk.ar.web.test.controller;

import com.sk.ar.web.test.dto.request.*;
import com.sk.ar.web.test.entity.*;
import com.sk.ar.web.test.service.ArEventService;
import com.sk.ar.web.test.service.CategoryService;
import com.sk.ar.web.test.utils.DateUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/api/v1/ar/event")
public class ArEventController {

    @Autowired
    private ArEventService arEventService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping(value = "/save")
    public void saveEvent(@RequestBody EventSaveDto eventSaveDto,
                          @RequestPart(value = "excelFile", required = false) MultipartFile excelFile) {
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
            log.info("arEventId >> " + arEventId);

            if (arEventId > 0) {
                /**
                 * AR_EVENT_BUTTON 저장
                 */
                arEventService.saveEventButton(ArEventButtonEntity.of(arEventId, eventSaveDto.getArEventButtonInfo()));

                /**
                 * AR_EVENT_OBJECT 저장 (이미지스캐닝이 아날떄만)
                 */
                if (!"scanning".equals(eventSaveDto.getArEventInfo().getEventLogicalType())) {
                    List<ArEventObjectEntity> eventObjectEntityList = convertEventObjectDtoListToArEventObjectEntityList(eventSaveDto.getArEventObjectInfo());
                    eventObjectEntityList
                            .stream()
                            .filter(Objects::nonNull)
                            .forEach(objectEntity -> {
                                objectEntity.setArEventId(arEventId);
                                objectEntity.setCreatedDate(DateUtils.returnNowDate());
                            });
                    arEventService.saveAllArEventObject(eventObjectEntityList);
                }


                /**
                 * AR_EVENT_LOGICAL 저장
                 */
                arEventService.saveEventLogical(ArEventLogicalEntity.of(arEventId, eventSaveDto.getArEventLogicalInfo()));

                /**
                 * AR_EVENT_SCANNING_IMAGE(이미지스캔형일때만)
                 */
                if ("scanning".equals(eventSaveDto.getArEventInfo().getEventLogicalType())) {
                    List<ArEventScanningImageEntity> arEventImageScanningEntityList = convertEventScanningImageDtoListToArEventImageScanningEntityList(eventSaveDto.getEventScanningImageInfo());
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
                 * TODO 당첨정보 >> AR_EVENT_WINNING, AR_EVENT_WINNING_BUTTON 저장
                 */
            }
        }


//        //당첨정보 저장하기
//        int j = 0;
//        List<ArEventWinningEntity> arEventWinningEntityList = convertEventWinningDtoToArEventWinningEntityList(eventSaveDto.getEventWinningInfo());
//        for (ArEventWinningEntity arEventWinningEntity : arEventWinningEntityList) {
//
//            arEventWinningEntity.setArEventId(eventId);
//            arEventWinningEntity.setCreatedDate(DateUtils.returnNowDate());
//
//            arEventService.saveEventWinning(arEventWinningEntity);
//
//            List<ArEventWinningButtonEntity> arEventWinningButtonEntityList = convertEventWinningButtonDtoToArEventWinningButtonEntityList(eventSaveDto.getEventWinningInfo().get(j).getEventWinningButtonInfo());
//
//            for (ArEventWinningButtonEntity buttonEntity : arEventWinningButtonEntityList) {
//
//
//                buttonEntity.setArEventWinningId(arEventService.findEventWinningEntityByEventId(eventId).getId());
//                buttonEntity.setCreatedDate(DateUtils.returnNowDate());
//
//                arEventService.saveEventWinningButton(buttonEntity);
//            }
//
//            j++;
//
//        }
    }

    @GetMapping(value = "/category/all")
    @ApiOperation("이벤트 카테고리 정보 가져오기")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryType", value = "카테고리 타입(라디오버튼 : radio, 셀렉트박스 : select)", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "parentCode", value = "부모의 카테고리 코드", dataType = "string", paramType = "query"),
    })
    public ResponseEntity<ApiResultObjectDto> findAllByEventCategory(@RequestParam(value = "categoryType", required = false) String categoryType,
                                                                     @RequestParam(value = "parentCode", required = false) String parentCode) {
        return ResponseEntity.ok(arEventService.findAllEventCategory(categoryType, parentCode));
    }

    @GetMapping(value = "/test")
    @ApiOperation("")
    public ResponseEntity<WebEventBaseEntity> test(@RequestParam(value = "eventId", required = false) String eventId) {
        return ResponseEntity.ok(arEventService.findEventBase(eventId));
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

}
