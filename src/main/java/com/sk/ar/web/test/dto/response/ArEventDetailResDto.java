package com.sk.ar.web.test.dto.response;

import com.sk.ar.web.test.entity.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArEventDetailResDto {

    private WebEventBaseEntity webEventBaseInfo;

    private ArEventResDto arEventInfo;

    private ArEventButtonEntity arEventButtonInfo;

    private List<ArEventObjectEntity> arEventObjectInfo;

    private ArEventLogicalEntity arEventLogicalInfo;

    private List<ArEventScanningImageEntity> arEventScanningImageInfo;

    private List<ArEventWinningResDto> arEventWinningInfo;

    private List<ArEventHtmlEntity> arEventHtmlInfo;

    private String arEventUrl;

}
