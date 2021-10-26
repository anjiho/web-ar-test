package com.sk.ar.web.test.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import com.sk.ar.web.test.dto.response.ApiResultObjectDto;
import com.sk.ar.web.test.logic.ArEventFrontLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/api/v1/web-event-front")
public class WebEventFrontController {

    @Autowired
    private ArEventFrontLogic arEventFrontLogic;


    @GetMapping(value = "/gate/{eventId}")
    @ApiOperation("AR 이벤트 게이트 페이지 정보")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "eventId", value = "이벤트 아이디", dataType = "string", paramType = "path", required = true)
    })
    public ResponseEntity<ApiResultObjectDto> getGatePage(@PathVariable(value = "eventId") String eventId) {
        return ResponseEntity.ok(arEventFrontLogic.getGatePageLogic(eventId));
    }
}
