package com.sk.ar.web.test.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@NoArgsConstructor
@Data
public class EventSaveDto {

    //이벤트 메인 정보
    @Valid
    private EventDto eventMainInfo;

    //이벤트 메인 버튼 정보
    @Valid
    private EventButtonDto eventMainButtonInfo;

    //AR 구동 정보
    @Valid
    private List<EventLogicalDto> eventLogicalInfo;

    //이미지스캐닝 정보(AR 구동정보가 이미지스캐닝일때만 저장)
    @Valid
    private List<EventImageScanningDto> eventImageScanningInfo;

    //이벤트 당첨정보, 당첨버튼정보 저장
    @Valid
    private List<EventWinningDto> eventWinningInfo;



}
