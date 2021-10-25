package com.sk.ar.web.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sk.ar.web.test.dto.request.EventDto;
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
    private String eventId;

    // AR 구동 정보(기본형 ~ 이미지스캐닝형)
    private String eventLogicalType;

    // 페이지 접속 팝업(위치설정조건)
    private Boolean locationSettingYn;

    // AR 참여조건(전체)
    private Boolean arAttendConditionAllYn;

    // AR 참여조건(특정위치)
    private Boolean arAttendConditionSpecialPositionYn;

    // AR 참여조건(시간별)
    private Boolean arAttendConditionHourlyYn;

    // AR 참여조건(참여번호)
    private Boolean arAttendConditionCodeYn;

    // 기간참여조건 타입(제한없음, 기간제한)
    private String arAttendTermType;

    // 기간참여조건 종류(1일, 이벤트기간내)
    private String arAttendTermLimitType;

    // 기간참여조건 회수
    private Integer arAttendTermLimitCount;

    // pid
    private String pid;

    // 위치메세지 등록(위치 참여시)
    private String positionMessageAttend;

    // 위치메세지 등록(위치 미 참여시)
    private String positionMessageNotAttend;

    // 시간참여 불가시
    private String attendHourMessage;

    // 참여번호 미 매칭시
    private String attendCodeMisMatchMessage;

    // AR BG 이미지
    private String arBgImage;

    // AR 스킨 이미지
    private String arSkinImage;

    // 당첨정보(공통)설정 > 중복당첨수 제한 타입
    private String duplicateWinningType;

    // 중복당첨 당첨제한 (전체 : 0 , 1일 : 1)
    private Integer duplicateWinningLimitType;

    // 중복 당첨 당첨제한 회수
    private Integer duplicateWinningCount;

    // 생성자
    private String createdBy;

    // 생성일
    private Date createdDate;

    // 수정자
    private String lastModifiedBy;

    // 수정일
    private Date lastModifiedDate;

    public static ArEventEntity of(String eventId, EventDto dto) {
        ArEventEntity entity = ModelMapperUtils.getModelMapper().map(dto, ArEventEntity.class);
        entity.setEventId(eventId);
        entity.setCreatedDate(DateUtils.returnNowDate());
        return entity;
    }

    public static ArEventEntity updateOf(ArEventEntity arEventEntity, String eventId, EventDto dto) {
        ArEventEntity entity = ModelMapperUtils.getModelMapper().map(dto, ArEventEntity.class);
        entity.setArEventId(arEventEntity.getArEventId());
        entity.setEventId(eventId);
        entity.setCreatedDate(arEventEntity.getCreatedDate());
        entity.setLastModifiedDate(DateUtils.returnNowDate());
        return entity;
    }
}
