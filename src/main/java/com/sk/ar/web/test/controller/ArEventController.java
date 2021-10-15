package com.sk.ar.web.test.controller;

import com.sk.ar.web.test.dto.request.ApiResultObjectDto;
import com.sk.ar.web.test.dto.request.EventDto;
import com.sk.ar.web.test.dto.request.EventSaveDto;
import com.sk.ar.web.test.jpa.event.ArEventButtonJpa;
import com.sk.ar.web.test.jpa.event.EventJpa;
import com.sk.ar.web.test.jpa.event.repository.EventJpaRepository;
import com.sk.ar.web.test.service.ArEventService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/api/v1/ar/event")
public class ArEventController {

    @Autowired
    private ArEventService arEventService;


    @PostMapping(value = "/save")
    public void saveEvent(@Valid @RequestBody EventSaveDto eventSaveDto) {
        EventJpa eventJpa = new EventJpa().builder().eventDto(eventSaveDto.getEventMainInfo()).build();
        int eventId = arEventService.saveEvent(eventJpa);

        ArEventButtonJpa arEventButtonJpa = new ArEventButtonJpa().builder()
                .eventId(eventId)
                .dto(eventSaveDto.getEventMainButtonInfo())
                .build();

        arEventService.saveEventMainButton(arEventButtonJpa);

        log.info(">>>>> " + eventId);
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
}
