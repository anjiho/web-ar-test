package com.sk.ar.web.test.entity;

import com.sk.ar.web.test.dto.request.EventObjectDto;
import com.sk.ar.web.test.utils.DateUtils;
import com.sk.ar.web.test.utils.ModelMapperUtils;
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
@Table(name = "AR_EVENT_OBJECT")
public class ArEventObjectEntity {

    // 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer arEventObjectId;

    // 이벤트 아이디
    private Integer arEventId;

    // 오브젝트 순서
    private Integer objectSort;

    // 오브젝트 설정 값
    private String objectSettingType;

    // 오브젝트 설정 파일 URL
    private String objectSettingUrl;

    // 오브젝트 크기(x)
    @Column(name = "object_size_x")
    private BigDecimal objectSizeX;

    // 오브젝트 크기(y)
    @Column(name = "object_size_y")
    private BigDecimal objectSizeY;

    // 오브젝트 크기(z)
    @Column(name = "object_size_z")
    private BigDecimal objectSizeZ;

    // 동영상 재생반복 여부 값
    private String videoPlayRepeatType;

    // 오브젝트 위치지정 값
    private String objectPositionAssignType;

    // 오브젝트 위치 지정(x)
    @Column(name = "object_location_x")
    private BigDecimal objectLocationX;

    // 오브젝트 위치 지정(y)
    @Column(name = "object_location_y")
    private BigDecimal objectLocationY;

    // 오브젝트 위치 지정(z)
    @Column(name = "object_location_z")
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
    @Column(name = "object_change_size_x")
    private BigDecimal objectChangeSizeX;

    // 오브젝트 change 크기(y)
    @Column(name = "object_change_size_y")
    private BigDecimal objectChangeSizeY;

    // 오브젝트 change 크기(z)
    @Column(name = "object_change_size_z")
    private BigDecimal objectChangeSizeZ;

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

    // 참여번호당 노출수 타입 값
    private String attendCodeExposureType;

    // 참여번호당 노출수 지정시 타입(0:전체기한내, 1일)
    private Integer attendCodeLimitType;

    // 참여번호당 노출수
    private Integer attendCodeExposureCount;

    // 노출 확률 여부 값
    private String exposurePercentType;

    // 노출 확률 %(0.01 ~ 100)
    private String exposurePercent;

    // 브릿지 타입 값
    private String bridgeType;

    // 브릿지 파일 url
    private String bridgeUrl;

    // 브릿지 노출 시간 여부 값(설정 라디오버튼)
    //private String bridgeExposureTimeType;

    // 브릿지 노출 시간 값
    private Integer bridgeExposureTimeSecond;

    // 브릿지 화면 방향  값(화면 방향 라디오 코드 값)
    private String bridgeDisplayDirectionType;

    // 미션클리어형 비활성 썸네일 url
    private String missionInactiveThumbnailUrl;

    // 미션클리어형 활성 썸네일 url
    private String missionActiveThumbnailUrl;

    // 생성자
    private String createdBy;

    // 생성일
    private Date createdDate;

    // 수정자
    private String lastModifiedBy;

    // 수정일
    private Date lastModifiedDate;

    public static ArEventObjectEntity of(int arEventId, EventObjectDto dto) {
        ArEventObjectEntity arEventObjectEntity = ModelMapperUtils.getModelMapper().map(dto, ArEventObjectEntity.class);
        arEventObjectEntity.setArEventId(arEventId);
        arEventObjectEntity.setCreatedDate(DateUtils.returnNowDate());
        return arEventObjectEntity;
    }
}
