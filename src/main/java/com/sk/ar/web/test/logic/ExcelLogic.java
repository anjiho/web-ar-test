package com.sk.ar.web.test.logic;

import com.sk.ar.web.test.dto.response.ApiResultObjectDto;
import com.sk.ar.web.test.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Repository
public class ExcelLogic {

    private final int httpSuccessCode = HttpStatus.OK.value();

    @Autowired
    private ExcelService excelService;

    public ApiResultObjectDto isDuplicateAttendCode(MultipartFile excelFile) {
        int resultCode = httpSuccessCode;
        //참여코드 중복 확인
        Map<String, Object> attendCodeExcelMap = excelService.isValidationAttendCodeByExcelFile(excelFile);

        return new ApiResultObjectDto().builder()
                .result(attendCodeExcelMap)
                .resultCode(resultCode)
                .traceNo("")
                .build();
    }
}
