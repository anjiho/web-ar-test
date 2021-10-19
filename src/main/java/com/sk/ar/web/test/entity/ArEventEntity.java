package com.sk.ar.web.test.entity;

import com.sk.ar.web.test.dto.request.EventDto;
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
@Table(name = "AR_EVENT")
public class ArEventEntity {

    // 이벤트 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer arEventId;

    // 이벤트 기본 테이블 아이디
    private Integer eventId;

    // AR 구동 정보(기본형 ~ 이미지스캐닝형)
    private String eventLogicalType;

    // 페이지 접속 팝업
    private Boolean pageConnectPopupYn;

    // AR 참여조건(전체)
    private Boolean arAttendConditionAllYn;

    // AR 참여조건(특정위치)
    private Boolean arAttendConditionSpecialPositionYn;

    // AR 참여조건(시간별)
    private Boolean arAttendConditionHourlyYn;

    // pid
    private String pid;

    // 위치메세지 등록(위치 참여시)
    private String positionMessageAttend;

    // 위치메세지 등록(위치 미 참여시)
    private String positionMessageNotAttend;

    // 참여시간 설정(시작)
    private Integer attendHourStart;

    // 참여시간 설정(종료)
    private Integer attendHourEnd;

    // 시간참여 불가시
    private String attendHourMessage;

    // 생성자
    private String createdBy;

    // 생성일
    private Date createdDate;

    // 수정자
    private String lastModifiedBy;

    // 수정일
    private Date lastModifiedDate;

    public static ArEventEntity of(int eventId, EventDto dto) {
        ArEventEntity entity = ModelMapperUtils.getModelMapper().map(dto, ArEventEntity.class);
        entity.setEventId(eventId);
        entity.setCreatedDate(DateUtils.returnNowDate());
        return entity;
    }
}
