package com.sk.ar.web.test.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventHtmlDto {

    private Integer eventHtmlId;

    // html 정보 타입(1:이미지, 2:버튼, 3:공유하기)
    private Integer htmlType;

    // 이미지 url
    private String htmlImageUrl;

    // 버튼 유형
    private String htmlButtonType;

    // 버튼 배경색 지정여부
    private Integer htmlButtonBgColorAssignType;

    // 버튼 배경색 rgb 값
    private Integer htmlButtonBgColorReg;

    // 버튼 배경색 rgb 값
    private Integer htmlButtonBgColorGreen;

    // 버튼 배경색 rgb 값
    private Integer htmlButtonBgColorBlue;

    // 버튼 배경색 hex 값
    private String htmlButtonBgColorHex;

    // 버튼 text
    private String htmlButtonText;

    // 버튼 target url
    private String htmlButtonTargetUrl;

    // 공유하기 버튼 사용 여부
    private Boolean htmlShareButtonUseYn;

    // 순서
    private Integer htmlTypeSortNumber;

}
