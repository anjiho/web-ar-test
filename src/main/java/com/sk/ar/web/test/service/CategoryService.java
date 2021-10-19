package com.sk.ar.web.test.service;

import com.sk.ar.web.test.entity.ArEventCategoryEntity;
import com.sk.ar.web.test.entity.repository.ArEventCategoryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private ArEventCategoryEntityRepository arEventCategoryEntityRepository;

    public ArEventCategoryEntity findArEventCategoryByCategoryCode(String categoryCode) {
        return arEventCategoryEntityRepository.findByCategoryCode(categoryCode).orElseGet(ArEventCategoryEntity::new);
    }
}
