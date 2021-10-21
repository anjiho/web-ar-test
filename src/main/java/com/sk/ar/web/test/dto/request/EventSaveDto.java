package com.sk.ar.web.test.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.lang.reflect.Member;
import java.util.List;

@NoArgsConstructor
@Data
public class EventSaveDto {
    //이벤트 기본 정보
    private EventBaseDto eventBaseInfo;

    //AR 이벤트 설정 공통 정보
    private EventDto arEventInfo;

    //AR 이벤트 버튼 정보
    private EventButtonDto arEventButtonInfo;

    //AR 구동 정보(기본형, 브릿지형, 미션클리어판)
    private List<EventObjectDto> arEventObjectInfo;

    //AR 구동정보 공통
    private EventLogicalDto arEventLogicalInfo;

    //이미지스캐닝 정보(AR 구동정보가 이미지스캐닝일때만 저장)
    private List<EvenScanningImageDto> arEventScanningImageInfo;

    //이벤트 당첨정보, 당첨버튼정보 저장
    private List<EventWinningDto> arEventWinningInfo;

    private List<EventHtmlDto> arEventHtmlInfo;

}
