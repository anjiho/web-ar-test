package com.sk.ar.web.test.dto.response;

import com.sk.ar.web.test.define.ErrorCodeDefine;
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

    private String traceNo;

    @Builder
    public ApiResultObjectDto(Object result, int resultCode, String traceNo) {
        this.resultCode = resultCode;
        this.result = result;
        this.resultMsg = ErrorCodeDefine.getEventErrorMessage(resultCode) == null ? "SUCCESS" : ErrorCodeDefine.getEventErrorMessage(resultCode);;
        this.traceNo = traceNo;
    }
}
