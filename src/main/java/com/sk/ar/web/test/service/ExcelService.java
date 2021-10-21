package com.sk.ar.web.test.service;

import com.sk.ar.web.test.dto.request.ApiResultObjectDto;
import com.sk.ar.web.test.utils.ExcelRead;
import com.sk.ar.web.test.utils.ExcelReadOption;
import com.sk.ar.web.test.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.util.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ExcelService {

    protected final String path = "/Users/jihoan/Downloads/";

    /**
     * 참여코드 중복여부 체크
     * @param excelFile
     * @return
     */
    public boolean isValidationAttendCodeByExcelFile(final MultipartFile excelFile) {
        boolean isValidation = false;

        try {
            File destFile = new File(path + excelFile.getOriginalFilename());
            excelFile.transferTo(destFile);

            ExcelReadOption excelReadOption = new ExcelReadOption();
            excelReadOption.setFilePath(path + destFile.getName());
            log.info("================ 파일명 ::: " + destFile.getName() + " =====================");
            excelReadOption.setOutputColumns("A");
            excelReadOption.setStartRow(2);
            List<Map<String, Object>> excelContentList = ExcelRead.read(excelReadOption);
            log.info("================ 엑셀 파일 추출 끝 =====================");
            //엑셀 파일 중복된 맥 어드레스가 있는지 확인
            boolean isDuplicatedExcelContentList = excelContentList.stream()
                    .map(excel->excel.get("A"))
                    .distinct()
                    .count() != excelContentList.size();
            if (isDuplicatedExcelContentList) {
                log.error("=============== 참여코드 중복 ==================");
                isValidation = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.fileDelete(path + excelFile.getOriginalFilename());
            log.info("================ 엑셀 파일 삭제 =====================");
        }
        return isValidation;
    }

    public List<Map<String, Object>> extractionAttendCodeByExcelFile(final MultipartFile excelFile) {
        List<Map<String, Object>> attendCodeList = new ArrayList<>();
        try {
            File destFile = new File(path + excelFile.getOriginalFilename());
            excelFile.transferTo(destFile);

            ExcelReadOption excelReadOption = new ExcelReadOption();
            excelReadOption.setFilePath(path + destFile.getName());
            log.info("================ 파일명 ::: " + destFile.getName() + " =====================");
            excelReadOption.setOutputColumns("A");
            excelReadOption.setStartRow(2);
            attendCodeList = ExcelRead.read(excelReadOption);
            log.info("================ 엑셀 파일 추출 끝 =====================");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.fileDelete(path + excelFile.getOriginalFilename());
            log.info("================ 엑셀 파일 삭제 =====================");
        }
        return attendCodeList;
    }
}
