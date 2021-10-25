package com.sk.ar.web.test.service;

import com.sk.ar.web.test.utils.ExcelRead;
import com.sk.ar.web.test.utils.ExcelReadOption;
import com.sk.ar.web.test.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ExcelService {

    @Autowired
    private HttpServletRequest request;


    /**
     * 참여코드 중복여부 체크
     * @param excelFile
     * @return
     */
    public Map<String, Object> isValidationAttendCodeByExcelFile(final MultipartFile excelFile) {
        boolean isValidation = false;
        int attendCodeSize = 0;

        String path = request.getSession().getServletContext().getRealPath("/");

        try {
            File destFile = new File(path + excelFile.getOriginalFilename());
            excelFile.transferTo(destFile);

            ExcelReadOption excelReadOption = new ExcelReadOption();
            excelReadOption.setFilePath(path + destFile.getName());
            log.info("================ 파일명 ::: " + destFile.getName() + " =====================");
            excelReadOption.setOutputColumns("A");
            excelReadOption.setStartRow(2);

            List<Map<String, Object>> excelContentList = ExcelRead.read(excelReadOption);
            //참여코드 개수 주입
            attendCodeSize = excelContentList.size();

            log.info("================ 엑셀 파일 추출 끝 =====================");
            //엑셀 파일 중복된 맥 어드레스가 있는지 확인
            boolean isDuplicatedExcelContentList = excelContentList.stream()
                    .map(excel->excel.get("A"))
                    .distinct()
                    .count() != excelContentList.size();
            if (isDuplicatedExcelContentList) {
                log.error("=============== 참여코드 중복 ==================");
                isValidation = true;
                attendCodeSize = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            FileUtils.fileDelete(path + excelFile.getOriginalFilename());
            log.info("================ 엑셀 파일 삭제 =====================");
        }

        Map<String, Object>resultMap = new HashMap<>();
        resultMap.put("isDuplicate", isValidation);
        resultMap.put("attendCodeCount", attendCodeSize);

        return resultMap;

    }

    public List<Map<String, Object>> extractionAttendCodeByExcelFile(final MultipartFile excelFile) {
        List<Map<String, Object>> attendCodeList = new ArrayList<>();
        String path = request.getSession().getServletContext().getRealPath("/");

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
