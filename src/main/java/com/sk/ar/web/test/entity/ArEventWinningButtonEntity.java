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
@Table(name = "AR_EVENT_WINNING_BUTTON")
public class ArEventWinningButtonEntity {

    // 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer arEventWinningButtonId;

    // AR_EVENT_WINNING.id
    private Integer arEventWinningId;

    // 버튼 액션 타입
    private String buttonActionType;

    // 버튼 문구
    private String buttonText;

    // 버튼 링크 url
    private String buttonLinkUrl;

    // 순서
    private Integer buttonSortNumber;

    // 버튼 액션 타입이 경품배송일때 성명 사용여부
    private Boolean deliveryNameYn;

    // 버튼 액션 타입이 경품배송일때 전화번호 사용여부
    private Boolean deliveryPhoneNumberYn;

    // 버튼 액션 타입이 경품배송일때 배송주소 사용여부
    private Boolean deliveryAddressYn;

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
