package com.sk.ar.web.test.controller;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk.ar.web.test.dto.request.*;
import com.sk.ar.web.test.entity.*;
import com.sk.ar.web.test.logic.ArEventLogic;
import com.sk.ar.web.test.logic.ExcelLogic;
import com.sk.ar.web.test.service.ArEventService;
import com.sk.ar.web.test.service.CategoryService;
import com.sk.ar.web.test.service.ExcelService;
import com.sk.ar.web.test.utils.DateUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/api/v1/ar-event")
public class ArEventController {

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

    @PostMapping(value = "/save")
    @ApiOperation("AR 이벤트 저장")
    public ResponseEntity<ApiResultObjectDto> saveEvent(
            @RequestPart(value = "jsonStr") String jsonStr,
            @RequestPart(value = "excelFile", required = false) MultipartFile excelFile) {
        return ResponseEntity.ok(arEventLogic.saveArEventLogic(jsonStr, excelFile));
    }

    @PostMapping(value = "/verification/attend-code")
    @ApiOperation("참여코드 검증")
    public ResponseEntity<ApiResultObjectDto> verificationAttendCodeByExcelFile(@RequestPart(value = "excelFile") MultipartFile excelFile) {
        return ResponseEntity.ok(excelLogic.isDuplicateAttendCode(excelFile));
    }

    @PostMapping(value = "/update")
    @ApiOperation("AR 이벤트 수정")
    public ResponseEntity<ApiResultObjectDto> updateEvent(@RequestPart(value = "eventId") String eventId,
                                                          @RequestPart(value = "jsonStr") String jsonStr,
                                                          @RequestPart(value = "excelFile", required = false) MultipartFile excelFile) {
        return ResponseEntity.ok(arEventLogic.updateArEventLogic(eventId, jsonStr, excelFile));
    }
}
