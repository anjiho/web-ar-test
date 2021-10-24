package com.sk.ar.web.test.logic;

import com.sk.ar.web.test.dto.response.ApiResultObjectDto;
import com.sk.ar.web.test.service.ArEventFrontService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ArEventFrontLogic {

    @Autowired
    private ArEventFrontService arEventFrontService;

    public ApiResultObjectDto getGatePageLogic(String eventId) {
        return null;
    }

}
