package com.sk.ar.web.test.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArEventBridgeResDto {

    private Integer arEventLogicalId;

    //브릿지 타입
    private String bridgeType;

    //브릿지 URL
    private String bridgeUrl;

    // 브릿지 노출 시간 여부 값(설정 라디오버튼)
    private String bridgeExposureTimeType;

    // 브릿지 노출 시간 값
    private Integer bridgeExposureTimeSecond;

    // 브릿지 화면 방향  값(화면 방향 라디오 코드 값)
    private String bridgeDisplayDirectionType;




}
