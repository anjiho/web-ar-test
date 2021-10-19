package com.sk.ar.web.test.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventLogicalDto {

    // 판 설정  값(판 위치 셀렉트박스)
    private String panPositionType;

    // 판 미션 수
    private Integer panMissionNumber;

    // 브릿지 타입 값
    private String bridgeType;

    // 브릿지 url
    private String bridgeUrl;

    // 브릿지 노출 시간 여부 값(설정 라디오버튼)
    private String bridgeExposureTimeType;

    // 브릿지 노출 시간 값
    private Integer bridgeExposureTimeSecond;

    // 브릿지 화면 방향  값(화면 방향 라디오 코드 값)
    private String bridgeDisplayDirectionType;
}
