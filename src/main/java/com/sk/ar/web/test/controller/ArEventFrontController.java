package com.sk.ar.web.test.controller;

import lombok.extern.slf4j.Slf4j;

import com.sk.ar.web.test.dto.response.ApiResultObjectDto;
import com.sk.ar.web.test.logic.ArEventFrontLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/api/v1/ar-event-front")
public class ArEventFrontController {

    @Autowired
    private ArEventFrontLogic arEventFrontLogic;

    @GetMapping(value = "/gate/{eventId}")
    public ApiResultObjectDto getGatePage(@PathVariable(value = "eventId") String eventId) {
        return null;
    }
}
