package com.sk.ar.web.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AR_EVENT_HTML")
public class ArEventHtmlEntity {

    // 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer eventHtmlId;

    // 이벤트 아이디
    private Integer arEventId;

    // html 정보 타입(1:이미지, 2:버튼, 3:공유하기)
    private Integer htmlType;

    // 이미지 url
    private String htmlImageUrl;

    // 버튼 유형
    private String htmlButtonType;

    // 버튼 배경색 지정여부
    private String htmlButtonBgColorAssignType;

    // 버튼 배경색 지정일떄 RGB, HEX 여부)
    private String htmlButtonBgColorInputType;

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

    // 순서
    private Integer htmlTypeSortNumber;

    // 공유하기 버튼 사용 여부
    private Boolean htmlShareButtonUseYn;

    // 버튼색 지정여부
    private String htmlButtonColorAssignType;

    // 버튼색 지정일떄 RGB, HEX 여부)
    private String htmlButtonColorInputType;

    // 버튼색 rgb 값
    private Integer htmlButtonColorRed;

    // 버튼색 rgb 값
    private Integer htmlButtonColorGreen;

    // 버튼색 rgb 값
    private Integer htmlButtonColorBlue;

    // 버튼색 hex 값
    private String htmlButtonColorHex;

    // 버튼 텍스트색 지정여부
    private String htmlButtonTextColorAssignType;

    // 버튼 테스트색 지정일떄 RGB, HEX 여부)
    private String htmlButtonTextColorInputType;

    // 버튼 테스트색 rgb값
    private Integer htmlButtonTextColorRed;

    // 버튼 테스트색 rgb값
    private Integer htmlButtonTextColorGreen;

    // 버튼 테스트색 rgb값
    private Integer htmlButtonTextColorBlue;

    // 버튼 테스트색 rgb값
    private String htmlButtonTextColorHex;

    // 생성자
    private String createdBy;

    // 생성일
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date createdDate;

    // 수정자
    private String lastModifiedBy;

    // 수정일
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date lastModifiedDate;
}
