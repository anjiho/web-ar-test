package com.sk.ar.web.test.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventButtonDto {

    // 버튼 배경색 지정 여부 값(AR_EVENT_CATEGORY)
    private String arButtonBgColorAssignType;

    // 버튼 배경색 지정일떄 RGB, HEX 여부
    private String arButtonBgColorInputType;

    // 버튼 배경색 rgb 값
    private Integer arButtonBgColorRed;

    // 버튼 배경색 rgb 값
    private Integer arButtonBgColorGreen;

    // 버튼 배경색 rgb 값
    private Integer arButtonBgColorBlue;

    // 버튼 배경색 hex 값
    private String arButtonBgColorHex;

    // 버튼색 지정 여부 값
    private String arButtonColorAssignType;

    // 버튼색 지정일떄 RGB, HEX 여부
    private String arButtonColorInputType;

    // 버튼색 rgb 값
    private Integer arButtonColorRed;

    // 버튼색 rgb 값
    private Integer arButtonColorGreen;

    // 버튼색 rgb 값
    private Integer arButtonColorBlue;

    // 버튼색 hex
    private String arButtonColorHex;

    // 버튼 text 색 지정 여부 값
    private String arButtonTextColorAssignType;

    // 버튼 text 색 지정일떄 RGB, HEX 여부
    private String arButtonTextColorInputType;

    // 버튼 text 색 rgb값
    private Integer arButtonTextColorRed;

    // 버튼 text 색 rgb값
    private Integer arButtonTextColorGreen;

    // 버튼 text 색 rgb값
    private Integer arButtonTextColorBlue;

    // 버튼 text 색 hext값
    private String arButtonTextColorHex;

    // 버튼 text 문구 지정
    private String arButtonText;

}
