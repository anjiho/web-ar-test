package com.sk.ar.web.test.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@NoArgsConstructor
@Data
public class EventSaveDto {

    //이벤트 기본 정보
    @Valid
    private EventBaseDto eventBaseInfo;

    //AR 이벤트 설정 공통 정보
    @Valid
    private EventDto arEventInfo;

    //AR 이벤트 버튼 정보
    @Valid
    private EventButtonDto arEventButtonInfo;

    //AR 구동 정보(기본형, 브릿지형, 미션클리어판)
    @Valid
    private List<EventObjectDto> arEventObjectInfo;

    //AR 구동정보 공통
    @Valid
    private EventLogicalDto arEventLogicalInfo;

    //이미지스캐닝 정보(AR 구동정보가 이미지스캐닝일때만 저장)
    @Valid
    private List<EvenScanningImageDto> arEventScanningImageInfo;

    //이벤트 당첨정보, 당첨버튼정보 저장
    @Valid
    private List<EventWinningDto> arEventWinningInfo;

    private List<EventHtmlDto> arEventHtmlInfo;

}
