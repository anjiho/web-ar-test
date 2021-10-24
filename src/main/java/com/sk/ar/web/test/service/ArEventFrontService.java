package com.sk.ar.web.test.service;

import com.sk.ar.web.test.entity.repository.ArEventHtmlEntityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ArEventFrontService {

    @Autowired
    private ArEventHtmlEntityRepository arEventHtmlEntityRepository;

    
    
}
