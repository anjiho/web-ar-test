package com.sk.ar.web.test.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.Date;

@AllArgsConstructor
@Data
public class EventDto {

    // AR 구동 정보(기본형 ~ 이미지스캐닝형)
    private String eventLogicalType;

    // 페이지 접속 팝업
    @NotNull(message = "페이지 접속 팝업 값이 없습니다.")
    private Boolean pageConnectPopupYn;

    // AR 참여조건(전체)
    private Boolean arAttendConditionAllYn;

    // AR 참여조건(특정위치)
    private Boolean arAttendConditionSpecialPositionYn;

    // AR 참여조건(시간별)
    private Boolean arAttendConditionHourlyYn;

    // AR 참여조건(참여번호)
    private Boolean arAttendConditionCodeYn;

    // pid
    private String pid;

    // 위치메세지 등록(위치 참여시)
    private String positionMessageAttend;

    // 위치메세지 등록(위치 미 참여시)
    private String positionMessageNotAttend;

    // 참여시간 설정(시작)
    @Positive
    @Range(min = 0, max = 24)
    private Integer attendHourStart;

    // 참여시간 설정(종료)
    private Integer attendHourEnd;

    // 시간참여 불가시
    private String attendHourMessage;

    // 참여번호 미 매칭시
    private String attendCodeMisMatchMessage;

}
