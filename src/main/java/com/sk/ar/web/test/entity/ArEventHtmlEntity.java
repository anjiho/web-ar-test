package com.sk.ar.web.test.entity;

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

    // 생성자
    private String createdBy;

    // 생성일
    private Date createdDate;

    // 수정자
    private String lastModifiedBy;

    // 수정일
    private Date lastModifiedDate;
}
