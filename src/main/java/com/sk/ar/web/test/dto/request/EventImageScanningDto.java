package com.sk.ar.web.test.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventImageScanningDto {

    // 스캐닝 이미지 url
    private String scanningImageUrl;

    // 스캐닝 사운드 선택 타입 값
    private String scanningSoundType;

    // 스캐닝 사운드 데이터
    private String scanningSoundData;

    // 정렬 순서
    private Integer scanningImageNumber;
}
