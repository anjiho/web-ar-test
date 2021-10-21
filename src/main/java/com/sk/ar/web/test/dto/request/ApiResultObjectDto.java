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

    private String traceCd;

    @Builder
    public ApiResultObjectDto(Object result, int resultCode, String traceCd) {
        this.resultCode = resultCode;
        this.result = result;
        this.resultMsg = "";
        this.traceCd = traceCd;
    }
}
