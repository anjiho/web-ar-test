package com.sk.ar.web.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sk.ar.web.test.dto.request.EventBaseDto;
import com.sk.ar.web.test.utils.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "WEB_EVENT_BASE")
public class WebEventBaseEntity {
    // 인데스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //이벤트 아이디
    private String eventId;

    // 이벤트 타이틀
    private String eventTitle;

    // 계약 인덱스 값
    private String marketingId;

    // 계약상태 값
    private String contractStatus;

    // 이벤트 종류 타입(AR, ROULETTE)
    private String eventType;

    // 서비스 시작일
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date eventStartDate;

    // 서비스 종료일
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
    private Date eventEndDate;

    // QR코드 이미지 URL
    private String qrCodeUrl;

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

    public static WebEventBaseEntity of(WebEventBaseEntity webEventBaseEntity, EventBaseDto dto) {
        WebEventBaseEntity entity = new WebEventBaseEntity();
        entity.setEventId(webEventBaseEntity == null ? "00001" : generateEventId(String.valueOf(webEventBaseEntity.getId())));
        entity.setEventTitle(dto.getEventTitle());
        entity.setMarketingId(dto.getMarketingId());
        entity.setContractStatus(dto.getContractStatus());
        entity.setEventType("AR");
        entity.setEventStartDate(dto.getEventStartDate());
        entity.setEventEndDate(dto.getEventEndDate());
        entity.setQrCodeUrl(dto.getQrCodeUrl());
        entity.setCreatedDate(DateUtils.returnNowDate());
        return entity;
    }

    public static WebEventBaseEntity updateOf(WebEventBaseEntity webEventBaseEntity, String eventId, EventBaseDto dto) {
        WebEventBaseEntity entity = new WebEventBaseEntity();
        entity.setId(webEventBaseEntity.getId());
        entity.setEventId(eventId);
        entity.setEventTitle(dto.getEventTitle());
        entity.setMarketingId(dto.getMarketingId());
        entity.setContractStatus(dto.getContractStatus());
        entity.setEventType(dto.getEventType());
        entity.setEventStartDate(dto.getEventStartDate());
        entity.setEventEndDate(dto.getEventEndDate());
        entity.setQrCodeUrl(dto.getQrCodeUrl());
        entity.setCreatedDate(webEventBaseEntity.getCreatedDate());
        entity.setLastModifiedDate(DateUtils.returnNowDate());
        return entity;
    }

    public static String generateEventId(String prevEventId) {
        String generateCode = "";
        if (!"".equals(prevEventId)) {
            generateCode = String.format("%05d", Integer.parseInt(prevEventId) + 1);
        } else {
            generateCode = "00001";
        }
        return generateCode;
    }
}
