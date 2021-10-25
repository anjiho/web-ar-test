package com.sk.ar.web.test.logic;

import com.sk.ar.web.test.define.ErrorCodeDefine;
import com.sk.ar.web.test.dto.response.ApiResultObjectDto;
import com.sk.ar.web.test.dto.response.WebArObjectResDto;
import com.sk.ar.web.test.entity.ArEventEntity;
import com.sk.ar.web.test.entity.ArEventHtmlEntity;
import com.sk.ar.web.test.entity.ArEventObjectEntity;
import com.sk.ar.web.test.entity.WebEventBaseEntity;
import com.sk.ar.web.test.service.ArEventFrontService;

import com.sk.ar.web.test.service.ArEventService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class ArEventFrontLogic {
    private final int httpSuccessCode = HttpStatus.OK.value();

    @Autowired
    private ArEventService arEventService;

    @Autowired
    private ArEventFrontService arEventFrontService;

    public ApiResultObjectDto getGatePageLogic(String eventId) {
        int resultCode = httpSuccessCode;

        if (StringUtils.isEmpty(eventId)) {

            resultCode = ErrorCodeDefine.CUSTOM_ERROR_EVENT_ID_NULL.code();
            log.error(ErrorCodeDefine.getLogErrorMessage(resultCode));

        } else {
            /**
             * TODO 이벤트 조건에 따른 gate 페이지를 불러오는 로직 추가해야함(안지호, 2021. 10. 25)
             */
            //AR_EVENT 정보 가져오기
            ArEventEntity arEvent = arEventService.findArEventByEventId(eventId);

            if (arEvent == null) {

                resultCode = ErrorCodeDefine.CUSTOM_ERROR_AR_EVENT_INFO_NULL.code();
                log.error(ErrorCodeDefine.getLogErrorMessage(resultCode));

            } else {

                List<ArEventHtmlEntity> arEventHtmlEntityList = arEventService.findAllArEventHtmlByArEventId(arEvent.getArEventId());

                return new ApiResultObjectDto().builder()
                        .resultCode(resultCode)
                        .result(arEventHtmlEntityList)
                        .traceNo("")
                        .build();
            }
        }
        return new ApiResultObjectDto();
    }

    public ApiResultObjectDto getWebArInfoLogic(String eventId) {
        int resultCode = httpSuccessCode;

        if (StringUtils.isEmpty(eventId)) {

            resultCode = ErrorCodeDefine.CUSTOM_ERROR_EVENT_ID_NULL.code();
            log.error(ErrorCodeDefine.getLogErrorMessage(resultCode));

        } else {
            WebEventBaseEntity webEventBase = arEventService.findEventBase(eventId);
            ArEventEntity arEvent = arEventService.findArEventByEventId(eventId);

            List<ArEventObjectEntity> arEventObjectEntityList = arEventService.findAllArEventObjectByArEventId(arEvent.getArEventId());

            WebArObjectResDto webArObjectResDto = new WebArObjectResDto().builder()
                    .eventId(webEventBase.getEventId())
                    .eventTitle(webEventBase.getEventTitle())
                    .eventLogicalType(arEvent.getEventLogicalType())
                    .arBgImage(arEvent.getArBgImage())
                    .arSkinImage(arEvent.getArSkinImage())
                    .arObjectInfo(null)
                    .arBridgeInfo(null)
                    .arScanningImageInfo(null)
                    .build();

            return new ApiResultObjectDto().builder()
                    .resultCode(resultCode)
                    .result(webArObjectResDto)
                    .traceNo("")
                    .build();

        }
        return new ApiResultObjectDto();

    }

}
