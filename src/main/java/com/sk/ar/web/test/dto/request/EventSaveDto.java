package com.sk.ar.web.test.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@NoArgsConstructor
@Data
public class EventSaveDto {

    //이벤트 메인 정보
    @Valid
    private EventDto eventMainInfo;

    //이벤트 메인 버튼 정보
    @Valid
    private EventButtonDto eventMainButtonInfo;

}
