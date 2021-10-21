package com.sk.ar.web.test.service;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.SplittableRandom;

@Component("myKeyGen")
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder sb = new StringBuilder();
        sb.append(target.getClass().getSimpleName())
                .append("-")
                .append(method.getName());

        if (params != null) {
            for (Object param : params) {
                sb.append("-")
                        .append(param.getClass().getSimpleName())
                        .append(":").append(param);
            }
        }
        return sb.toString();
    }
}
