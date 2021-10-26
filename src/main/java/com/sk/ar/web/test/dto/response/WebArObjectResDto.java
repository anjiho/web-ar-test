package com.sk.ar.web.test.dto.response;

import lombok.*;

import java.util.List;

/**
 * Web AR API 규격서 Response DTO
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebArObjectResDto {

    private String eventId;

    private String eventTitle;

    private String eventLogicalType;

    private String arBgImage;

    private String arSkinImage;

    private List<ArEventObjectResDto> arObjectInfo;

    private ArEventLogicalResDto arEventLogicalInfo;

    private List<ArEventScanningImageResDto> arScanningImageInfo;
}
