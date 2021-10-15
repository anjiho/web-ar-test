package com.sk.ar.web.test.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResultObjectDto {

    private int resultCode;

    private Object result;

    private String resultMsg;

    @Builder
    public ApiResultObjectDto(Object result, int code) {
        this.resultCode = code;
        this.result = result;
        this.resultMsg = "";
    }
}
