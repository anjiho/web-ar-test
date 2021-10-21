package com.sk.ar.web.test.service;

import com.sk.ar.web.test.config.AppConfig;
import com.sk.ar.web.test.entity.ArEventCategoryEntity;
import com.sk.ar.web.test.entity.repository.ArEventCategoryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames = "employee-cache", keyGenerator = "myKeyGen")
public class CategoryService {

    @Cacheable
    public List<String> getEmployeeById(int id) {
        System.out.println("getEmployeeById() invoked");
        List<String>strList = Arrays.asList("1","2");
        return strList;
    }

    @Cacheable
    public List<String> getEmployeeById2(int id) {
        System.out.println("getEmployeeById() invoked");
        List<String>strList = Arrays.asList("3","4");
        return strList;
    }
}
