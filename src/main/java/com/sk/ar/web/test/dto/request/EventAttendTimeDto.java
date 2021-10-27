package com.sk.ar.web.test.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Positive;

@Setter
@Getter
public class EventAttendTimeDto {

    // 참여시간 설정(시작)
    @Positive
    @Range(min = 0, max = 24)
    private Integer attendStartHour;

    // 참여시간 설정(종료)
    private Integer attendEndHour;
}
