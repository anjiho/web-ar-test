package com.sk.ar.web.test.jpa.event;

import com.sk.ar.web.test.dto.request.EvenScanningImageDto;
import com.sk.ar.web.test.utils.DateUtils;
import com.sk.ar.web.test.utils.ModelMapperUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

// 이미지스캐닝 스캐닝 이미지 정보 리스트
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AR_EVENT_IMAGE_SCANNING")
public class ArEventImageScanningEntity {

    // 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // AR 구동 정보 아이디
    private Integer eventLogicalId;

    // 스캐닝 이미지 url
    private String scanningImageUrl;

    // 스캐닝 사운드 선택 타입 값
    private String scanningSoundType;

    // 스캐닝 사운드 데이터
    private String scanningSoundData;

    // 정렬 순서
    private Integer scanningImageNumber;

    // 생성일
    private Date createdDate;

    public static ArEventImageScanningEntity of (int eventLogicalId, EvenScanningImageDto dto) {
        ArEventImageScanningEntity entity = ModelMapperUtils.getModelMapper().map(dto, ArEventImageScanningEntity.class);
        entity.setEventLogicalId(eventLogicalId);
        entity.setCreatedDate(DateUtils.returnNowDate());

        return entity;
    }
}
