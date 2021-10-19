package com.sk.ar.web.test.controller;

import com.sk.ar.web.test.dto.request.*;
import com.sk.ar.web.test.entity.*;
import com.sk.ar.web.test.jpa.event.*;
import com.sk.ar.web.test.service.ArEventService;
import com.sk.ar.web.test.utils.DateUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public void saveEvent(@RequestBody EventSaveDto eventSaveDto) {
        /**
         * EVENT_BASE 저장
         */
        EventBaseEntity eventBaseEntity = EventBaseEntity.of(eventSaveDto.getEventBaseInfo());
        int eventId = arEventService.saveEventBase(eventBaseEntity);
        log.info("eventId >> " + eventId);

        if (eventId > 0) {
            /**
             * AR_EVENT 저장
             */
            ArEventEntity arEventEntity = ArEventEntity.of(eventId, eventSaveDto.getArEventInfo());
            int arEventId = arEventService.saveEvent(arEventEntity);
            log.info("arEventId >> " + arEventId);

            if (arEventId > 0) {
                /**
                 * AR_EVENT_BUTTON 저장
                 */
                ArEventButtonEntity arEventButtonEntity = ArEventButtonEntity.of(arEventId, eventSaveDto.getArEventButtonInfo());
                arEventService.saveEventButton(arEventButtonEntity);

                /**
                 * AR_EVENT_OBJECT 저장
                 */
                List<ArEventObjectEntity> eventObjectEntityList = convertEventObjectDtoListToArEventObjectEntityList(eventSaveDto.getArEventObjectInfo());
                eventObjectEntityList
                        .stream()
                        .filter(Objects::nonNull)
                        .forEach(objectEntity -> {
                            objectEntity.setArEventId(arEventId);
                            objectEntity.setCreatedDate(DateUtils.returnNowDate());
                        });
                arEventService.saveAllArEventObject(eventObjectEntityList);

                /**
                 * TODO AR_EVENT_LOGICAL, AR_EVENT_SCANNING_IMAGE(이미지스캔형일때만), AR_EVENT_SCANNING_IMAGE, AR_EVENT_WINNING_BUTTON 저장
                 */
            }
        }


        //메인 버튼 정보 저장
//        ArEventButtonJpa arEventButtonJpa = ArEventButtonJpa.of(
//                eventId, eventSaveDto.getEventMainButtonInfo()
//        );
//        arEventService.saveEventMainButton(arEventButtonJpa);
//
//        //AR_EVENT_LOGICAL 리스트 저장
//        List<ArEventLogicalJpa> eventLogicalList = convertArEventLogicalJpaListToDtoList(eventSaveDto.getEventLogicalInfo());
//        eventLogicalList.
//                stream()
//                .filter(Objects::nonNull)
//                .forEach(jpa -> {
//                    jpa.setEventId(eventId);
//                    jpa.setCreatedDate(DateUtils.returnNowDate());
//                });
//
//        arEventService.saveAllEventLogical(eventLogicalList);
//
//        int eventLogicalId = arEventService.findFirstByEventIdOrderByIdDesc(eventId).getId();

        //이미지 스캐닝형일때 AR_EVENT_IMAGE_SCANNING 저장하기
//        if ("scanning".equals(eventJpa.getEventLogicalType())) {
//
//
//            List<ArEventImageScanningEntity> arEventImageScanningEntityList = convertArEventImageScanningEntityListToDtoList(eventSaveDto.getEventImageScanningInfo());
//            arEventImageScanningEntityList
//                    .stream()
//                    .filter(Objects::nonNull)
//                    .forEach(entity -> {
//                        entity.setEventLogicalId(eventLogicalId);
//                        entity.setCreatedDate(DateUtils.returnNowDate());
//                    });
//
//            arEventService.saveAllEventImageScanning(arEventImageScanningEntityList);
//        }
//
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

    private List<ArEventObjectEntity> convertEventObjectDtoListToArEventObjectEntityList(List<EventObjectDto> eventObjectDtoList) {
        return eventObjectDtoList.
                stream()
                .map(dto -> modelMapper.map(dto, ArEventObjectEntity.class))
                .collect(Collectors.toList());
    }

    private List<ArEventImageScanningEntity> convertArEventImageScanningEntityListToDtoList(List<EventImageScanningDto> eventImageScanningDtoList) {
        return eventImageScanningDtoList.
                stream()
                .map(dto -> modelMapper.map(dto, ArEventImageScanningEntity.class))
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
