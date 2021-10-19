package com.sk.ar.web.test.entity;

import com.sk.ar.web.test.dto.request.EventBaseDto;
import com.sk.ar.web.test.jpa.event.EventJpa;
import com.sk.ar.web.test.utils.DateUtils;
import com.sk.ar.web.test.utils.ModelMapperUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "EVENT_BASE")
public class EventBaseEntity {

    // 인데스
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    // 이벤트 타이틀
    private String eventTitle;

    // 계약 인덱스 값
    private String marketingId;

    // 계약상태 값
    private String contractStatus;

    // 서비스 시작일
    private Date eventStartDate;

    // 서비스 종료일
    private Date eventEndDate;

    // QR코드 이미지 URL
    private String qrCodeUrl;

    // 생성자
    private String createdBy;

    // 생성일
    private Date createdDate;

    // 수정자
    private String lastModifiedBy;

    // 수정일
    private Date lastModifiedDate;

    public static EventBaseEntity of(EventBaseDto dto) {
        EventBaseEntity entity = ModelMapperUtils.getModelMapper().map(dto, EventBaseEntity.class);
        entity.setCreatedDate(DateUtils.returnNowDate());
        return entity;
    }
}
