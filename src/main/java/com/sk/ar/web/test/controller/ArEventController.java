package com.sk.ar.web.test.controller;

import com.sk.ar.web.test.dto.request.ApiResultObjectDto;
import com.sk.ar.web.test.dto.request.EventDto;
import com.sk.ar.web.test.dto.request.EventLogicalDto;
import com.sk.ar.web.test.dto.request.EventSaveDto;
import com.sk.ar.web.test.jpa.event.ArEventButtonJpa;
import com.sk.ar.web.test.jpa.event.ArEventLogicalJpa;
import com.sk.ar.web.test.jpa.event.EventJpa;
import com.sk.ar.web.test.jpa.event.repository.EventJpaRepository;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collector;
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
        EventJpa eventJpa = new EventJpa().builder().eventDto(eventSaveDto.getEventMainInfo()).build();
        int eventId = arEventService.saveEvent(eventJpa);

//        ArEventButtonJpa arEventButtonJpa = new ArEventButtonJpa().builder()
//                .eventId(eventId)
//                .dto(eventSaveDto.getEventMainButtonInfo())
//                .build();
        //메인 버튼 정보 저장
        ArEventButtonJpa arEventButtonJpa = ArEventButtonJpa.of(eventId, eventSaveDto.getEventMainButtonInfo());
        arEventService.saveEventMainButton(arEventButtonJpa);

        //AR_EVENT_LOGICAL 리스트 저장
        List<ArEventLogicalJpa> eventLogicalList = convertArEventLogicalJpaListToDtoList(eventSaveDto.getEventLogicalInfo());
        eventLogicalList.
                stream()
                .filter(Objects::nonNull)
                .forEach(jpa -> jpa.setCreatedDate(DateUtils.returnNowDate()));

        

//        if ( !eventLogicalList.isEmpty() ) {
//            List<ArEventLogicalJpa> arEventLogicalJpaList = new ArrayList<>();
//
//            eventLogicalList.forEach(logical -> {
//                ArEventLogicalJpa eventLogicalJpa = new ArEventLogicalJpa().builder()
//                        .eventId(eventId)
//                        .dto(logical)
//                        .build();
//
//                arEventLogicalJpaList.add(eventLogicalJpa);
//            });
//            arEventService.saveAllEventLogical(arEventLogicalJpaList);
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

    private List<ArEventLogicalJpa> convertArEventLogicalJpaListToDtoList(List<EventLogicalDto>eventLogicalDtoList) {
        return eventLogicalDtoList.
                stream()
                .map(dto -> modelMapper.map(dto, ArEventLogicalJpa.class))
                .collect(Collectors.toList());
    }
}
