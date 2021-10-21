package com.sk.ar.web.test.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AR_EVENT_SCANNING_IMAGE")
public class ArEventScanningImageEntity {

    // 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer arEventScanningImageId;

    // 이미지스캐닝 정보 아이디
    private Integer arEventId;

    // 이미지 설정 넘버
    private Integer scanningImageNumber;

    // 스캐닝 이미지 url
    private String scanningImageUrl;

    // 스캐닝 사운드 선택 타입 값
    private String scanningSoundType;

    // 스캐닝 사운드 데이터
    private String scanningSoundData;

    // 활성화 썸네일
    private String activeThumbnailUrl;

    // 비활성화 썸네일
    private String inactiveThumbnailUrl;

    // 생성자
    private String createdBy;

    // 생성일
    private Date createdDate;

    // 수정자
    private String lastModifiedBy;

    // 수정일
    private Date lastModifiedDate;
}
