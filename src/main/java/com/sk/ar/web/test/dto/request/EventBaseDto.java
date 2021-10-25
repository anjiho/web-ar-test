package com.sk.ar.web.test.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Setter
@Getter
public class EventBaseDto {

    // 이벤트 타이틀
    @NotEmpty(message = "이벤트 제목이 없습니다.")
    private String eventTitle;

    // 계약 인덱스 값
    private String marketingId;

    // 계약상태 값
    private String contractStatus;

    // 이벤트 종류 타입(AR, ROULETTE)
    private String eventType;

    // 서비스 시작일
    private Date eventStartDate;

    // 서비스 종료일
    private Date eventEndDate;

    // QR코드 이미지 URL
    private String qrCodeUrl;

}
