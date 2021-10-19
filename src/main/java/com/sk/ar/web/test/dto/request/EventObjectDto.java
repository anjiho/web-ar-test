package com.sk.ar.web.test.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Data
public class EventObjectDto {

    // 오브젝트 순서
    private Integer objectSort;

    // 오브젝트 설정 값
    private String objectSettingType;

    // 오브젝트 설정 파일 URL
    private String objectSettingUrl;

    // 오브젝트 크기(x)
    private BigDecimal objectSizeX;

    // 오브젝트 크기(y)
    private BigDecimal objectSizeY;

    // 오브젝트 크기(z)
    private BigDecimal objectSizeZ;

    // 동영상 재생반복 여부 값
    private String videoPlayRepeatType;

    // 동영상 투과색 지정 여부
    private String videoPenetrationAssignType;

    // 동영상 투과색(hex)
    private String videoPenetrationColorHex;

    // 오브젝트 위치지정 값
    private String objectPositionAssignType;

    // 오브젝트 위치 지정(x)
    private BigDecimal objectLocationX;

    // 오브젝트 위치 지정(y)
    private BigDecimal objectLocationY;

    // 오브젝트 위치 지정(z)
    private BigDecimal objectLocationZ;

    // STAY EFFECT 설정  값
    private String stayEffectType;

    // 클릭 이벤트 설정  값
    private String clickEventType;

    // 오브젝트 change 설정 값
    private String objectChangeSettingType;

    // 오브젝트 change 설정 파일 URL
    private String objectChangeSettingVideoUrl;

    // 오브젝트 change 크기(x)
    private BigDecimal objectChangeSizeX;

    // 오브젝트 change 크기(y)
    private BigDecimal objectChangeSizeY;

    // 오브젝트 change 크기(z)
    private BigDecimal objectChangeSizeZ;

    // 오브젝트 change 동영상 투과색 지정 여부 코드
    private String objectChangeVideoPenetrationAssignType;

    // 오브젝트 change 동영상 투과색
    private String objectChangeVideoPenetrationColor;

    // 캐치 사운드 설정 값
    private String catchSoundType;

    // 캐치 사운드  값(URL, Library)
    private String catchSoundFile;

    // 노출제어 값
    private String exposureControlType;

    // 위치 노출제어 값
    private String locationExposureControlType;

    // 이벤트 pid
    private String locationExposureControlPid;

    // 최대 노출 여부 값
    private String maxExposureType;

    // 최대 노출 수
    private Integer maxExposureCount;

    // 일 노출 여부  값
    private String dayExposureType;

    // 일 노출 수
    private Integer dayExposureCount;

    // 시간당 노출 여부 값
    private String hourExposureType;

    // 시간당 노출 수
    private Integer hourExposureCount;

    // 노출 확률 여부 값
    private String exposurePercentType;

    // 노출 확률 %(0.01 ~ 100)
    private String exposurePercent;

    // 브릿지 타입 값
    private String bridgeType;

    // 브릿지 파일 url
    private String bridgeUrl;

    // 브릿지 노출 시간 여부 값(설정 라디오버튼)
    private String bridgeExposureTimeType;

    // 브릿지 노출 시간 값
    private Integer bridgeExposureTimeSecond;

    // 브릿지 화면 방향  값(화면 방향 라디오 코드 값)
    private String bridgeDisplayDirectionType;

    // 미션클리어형 비활성 썸네일 url
    private String missionInactiveThumbnailUrl;

    // 미션클리어형 활성 썸네일 url
    private String missionActiveThumbnailUrl;

}
