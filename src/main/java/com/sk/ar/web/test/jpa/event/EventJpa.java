package com.sk.ar.web.test.jpa.event;

import com.sk.ar.web.test.dto.request.EventDto;
import com.sk.ar.web.test.utils.DateUtils;
import com.sk.ar.web.test.utils.ModelMapperUtils;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AR_EVENT")
public class EventJpa {

    // 이벤트 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer eventId;

    // 이벤트 타이틀
    @NotNull(message = "이벤트 제목이 없습니다.")
    private String eventTitle;

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

    // AR 구동 정보 타입
    private String eventLogicalType;

    // 계약 인덱스 값
    private String marketingId;

    // 계약상태 값
    private String contractStatus;

    // 서비스 시작일
    private Date eventStartDate;

    // 서비스 종료일
    private Date eventEndDate;

    // 생성일
    private Date createdDate;

//    @Builder
//    public EventJpa(EventDto eventDto) {
//        this.eventTitle = eventDto.getEventTitle();
//        this.pageConnectPopupYn = eventDto.getPageConnectPopupYn();
//        this.arAttendConditionAllYn = eventDto.getArAttendConditionAllYn();
//        this.arAttendConditionSpecialPositionYn = eventDto.getArAttendConditionSpecialPositionYn();
//        this.arAttendConditionHourlyYn = eventDto.getArAttendConditionHourlyYn();
//        this.pid = eventDto.getPid();
//        this.positionMessageAttend = eventDto.getPositionMessageAttend();
//        this.attendHourStart = eventDto.getAttendHourStart();
//        this.attendHourEnd = eventDto.getAttendHourEnd();
//        this.attendHourMessage = eventDto.getAttendHourMessage();
//        this.eventLogicalType = eventDto.getEventLogicalType();
//        this.createdDate = DateUtils.returnNowDate();
//    }

    public static EventJpa of(EventDto dto) {
        EventJpa jpa = ModelMapperUtils.getModelMapper().map(dto, EventJpa.class);
        jpa.setCreatedDate(DateUtils.returnNowDate());

        return jpa;
    }
}
