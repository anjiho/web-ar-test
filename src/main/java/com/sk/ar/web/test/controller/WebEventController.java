package com.sk.ar.web.test.controller;

import com.sk.ar.web.test.dto.request.EventSaveDto;
import com.sk.ar.web.test.dto.response.ApiResultObjectDto;
import com.sk.ar.web.test.logic.ArEventLogic;
import com.sk.ar.web.test.logic.ExcelLogic;
import com.sk.ar.web.test.service.ArEventService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/api/v1/web-event")
public class WebEventController {

    @Autowired
    private ArEventService arEventService;

    @Autowired
    private ExcelLogic excelLogic;

    @Autowired
    private ArEventLogic arEventLogic;

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

    @GetMapping(value = "/save/request/sample")
    @ApiOperation("AR 이벤트 저장 데이터 샘플")
    public ResponseEntity<ApiResultObjectDto> saveEvent(@RequestBody EventSaveDto eventSaveDto) {
        return ResponseEntity.ok(new ApiResultObjectDto());
    }

    @PostMapping(value = "/save")
    @ApiOperation("AR 이벤트 저장")
    public ResponseEntity<ApiResultObjectDto> saveEvent(
            @RequestPart(value = "jsonStr") String jsonStr,
            @RequestPart(value = "excelFile", required = false) MultipartFile excelFile) {
        return ResponseEntity.ok(arEventLogic.saveArEventLogic(jsonStr, excelFile));
    }

    @PostMapping(value = "/update")
    @ApiOperation("AR 이벤트 수정")
    public ResponseEntity<ApiResultObjectDto> updateEvent(@RequestPart(value = "eventId") String eventId,
                                                          @RequestPart(value = "jsonStr") String jsonStr,
                                                          @RequestPart(value = "excelFile", required = false) MultipartFile excelFile) {
        return ResponseEntity.ok(arEventLogic.updateArEventLogic(eventId, jsonStr, excelFile));
    }

    @PostMapping(value = "/verification/attend-code")
    @ApiOperation("참여코드 검증")
    public ResponseEntity<ApiResultObjectDto> verificationAttendCodeByExcelFile(@RequestPart(value = "excelFile") MultipartFile excelFile) {
        return ResponseEntity.ok(excelLogic.isDuplicateAttendCode(excelFile));
    }

    @GetMapping(value = "/{eventId}")
    @ApiOperation("AR 이벤트 상세")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eventId", value = "이벤트 아이디", dataType = "string", paramType = "path", required = true)
    })
    public ResponseEntity<ApiResultObjectDto> getArEvent(@PathVariable(value = "eventId") String eventId) {
        return ResponseEntity.ok(arEventLogic.getArEventDetailLogic(eventId));
    }
}
