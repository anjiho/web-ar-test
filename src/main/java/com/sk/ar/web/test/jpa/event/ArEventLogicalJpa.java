package com.sk.ar.web.test.jpa.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sk.ar.web.test.dto.request.EventLogicalDto;
import com.sk.ar.web.test.utils.DateUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AR_EVENT_LOGICAL")
public class ArEventLogicalJpa {

    // 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 이벤트 아이디
    private Integer eventId;

    // 오브젝트 순서
    private Integer objectSort;

    // 오브젝트 설정 값
    private String objectSettingType;

    // 오브젝트 설정 파일 URL
    private String objectSettingUrl;

    // 오브젝트 크기(x)
    private BigDecimal objectSizeXAxis;

    // 오브젝트 크기(y)
    private BigDecimal objectSizeYAxis;

    // 오브젝트 크기(z)
    private BigDecimal objectSizeZAxis;

    // 동영상 재생반복 여부 값
    private String videoPlayRepeatType;

    // 동영상 투과색 지정 여부
    private String videoPenetrationAssignType;

    // 동영상 투과색(hex)
    private String videoPenetrationColorHex;

    // 오브젝트 위치지정 값
    private String objectPositionAssignType;

    // 오브젝트 위치 지정(x)
    private BigDecimal objectLocationXAxis;

    // 오브젝트 위치 지정(y)
    private BigDecimal objectLocationYAxis;

    // 오브젝트 위치 지정(z)
    private BigDecimal objectLocationZAxis;

    // STAY EFFECT 설정  값
    private String stayEffectType;

    // 클릭 이벤트 설정  값
    private String clickEventType;

    // 오브젝트 change 설정 값
    private String objectChangeSettingType;

    // 오브젝트 change 설정 파일 URL
    private String objectChangeSettingVideoUrl;

    // 오브젝트 change 크기(x)
    private BigDecimal objectChangeSizeXAxis;

    // 오브젝트 change 크기(y)
    private BigDecimal objectChangeSizeYAxis;

    // 오브젝트 change 크기(z)
    private BigDecimal objectChangeSizeZAxis;

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

    // 브릿지 화면 방향  값(화면 방향 라디오 코드 값)
    private String bridgeDisplayDirectionType;

    // 미션클리어판, 이미지가이드판 설정  값(판 위치 셀렉트박스)
    private String panPositionType;

    // 미션클리어판, 이미지가이드판 미션 수
    private Integer panMissionNumber;

    // 미션클리어형 비활성 썸네일 url
    private String missionInactiveThumbnailUrl;

    // 미션클리어형 활성 썸네일 url
    private String missionActiveThumbnailUrl;

    // 정렬 순서
    private Integer eventLogicalNumber;

    // 생성일
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date createdDate;

    @Builder
    public ArEventLogicalJpa(int eventId, EventLogicalDto dto) {
        this.eventId = eventId;
        this.objectSort = dto.getObjectSort();
        this.objectSettingType = dto.getObjectSettingType();
        this.objectSettingUrl = dto.getObjectSettingUrl();
        this.objectSizeXAxis = dto.getObjectSizeXAxis();
        this.objectSizeYAxis = dto.getObjectSizeYAxis();
        this.objectSizeZAxis = dto.getObjectSizeZAxis();
        this.videoPlayRepeatType = dto.getVideoPlayRepeatType();
        this.videoPenetrationAssignType = dto.getVideoPenetrationAssignType();
        this.videoPenetrationColorHex = dto.getVideoPenetrationColorHex();
        this.objectPositionAssignType = dto.getObjectPositionAssignType();
        this.objectLocationXAxis = dto.getObjectLocationXAxis();
        this.objectLocationYAxis = dto.getObjectLocationYAxis();
        this.objectLocationZAxis = dto.getObjectLocationZAxis();
        this.stayEffectType = dto.getStayEffectType();
        this.clickEventType = dto.getClickEventType();
        this.objectChangeSettingType = dto.getObjectChangeSettingType();
        this.objectChangeSettingVideoUrl = dto.getObjectChangeSettingVideoUrl();
        this.objectChangeSizeXAxis = dto.getObjectChangeSizeXAxis();
        this.objectChangeSizeYAxis = dto.getObjectChangeSizeYAxis();
        this.objectChangeSizeZAxis = dto.getObjectChangeSizeZAxis();
        this.objectChangeVideoPenetrationAssignType = dto.getObjectChangeVideoPenetrationAssignType();
        this.objectChangeVideoPenetrationColor = dto.getObjectChangeVideoPenetrationColor();
        this.catchSoundType = dto.getCatchSoundType();
        this.catchSoundFile = dto.getCatchSoundFile();
        this.exposureControlType = dto.getExposureControlType();
        this.locationExposureControlType = dto.getLocationExposureControlType();
        this.locationExposureControlPid = dto.getLocationExposureControlPid();
        this.maxExposureType = dto.getMaxExposureType();
        this.maxExposureCount = dto.getMaxExposureCount();
        this.dayExposureType = dto.getDayExposureType();
        this.dayExposureCount = dto.getDayExposureCount();
        this.hourExposureType = dto.getHourExposureType();
        this.hourExposureCount = dto.getHourExposureCount();
        this.exposurePercentType = dto.getExposurePercentType();
        this.exposurePercent = dto.getExposurePercent();
        this.bridgeType = dto.getBridgeType();
        this.bridgeUrl = dto.getBridgeUrl();
        this.bridgeExposureTimeType = dto.getBridgeExposureTimeType();
        this.bridgeDisplayDirectionType = dto.getBridgeDisplayDirectionType();
        this.panPositionType = dto.getPanPositionType();
        this.panMissionNumber = dto.getPanMissionNumber();
        this.missionInactiveThumbnailUrl = dto.getMissionInactiveThumbnailUrl();
        this.missionActiveThumbnailUrl = dto.getMissionActiveThumbnailUrl();
        this.eventLogicalNumber = dto.getEventLogicalNumber();
        this.createdDate = DateUtils.returnNowDate();
    }
}
