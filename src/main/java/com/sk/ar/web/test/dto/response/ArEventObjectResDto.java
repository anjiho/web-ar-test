package com.sk.ar.web.test.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class ArEventObjectResDto {

    private String clickEventType;

    // 오브젝트 설정 값
    private String objectSettingType;

    // 오브젝트 인덱스
    private Integer arEventObjectId;

    // 오브젝트 순서
    private Integer objectSort;

    // 오브젝트 크기(x)
    private BigDecimal objectSizeX;

    // 오브젝트 크기(y)
    private BigDecimal objectSizeY;

    // 오브젝트 크기(z)
    private BigDecimal objectSizeZ;

    // 오브젝트 설정 파일 URL
    private String objectSettingUrl;

    // STAY EFFECT 설정  값
    private String stayEffectType;

    // 동영상 재생반복 여부 값
    private String videoPlayRepeatType;

    //터치 관련 데이터 시작
    // 캐치 사운드 설정 값
    private String catchSoundType;

    // 캐치 사운드  값(URL, Library)
    private String catchSoundFile;

    // 오브젝트 change 설정 값(touch관련)
    private String objectChangeSettingType;

    // 오브젝트 change 설정 파일 URL
    private String objectChangeSettingVideoUrl;

    // 오브젝트 change 크기(x)
    private BigDecimal objectChangeSizeX;

    // 오브젝트 change 크기(y)
    private BigDecimal objectChangeSizeY;

    // 오브젝트 change 크기(z)
    private BigDecimal objectChangeSizeZ;
    //터치 관련 데이터 끝

    // 미션클리어형 비활성 썸네일 url
    private String missionInactiveThumbnailUrl;

    // 미션클리어형 활성 썸네일 url
    private String missionActiveThumbnailUrl;

    //AR_EVENT_LOGICAL
    // 판 설정  값(판 위치 셀렉트박스)
    private String panPositionType;




}
