package com.sk.ar.web.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sk.ar.web.test.dto.request.EventLogicalDto;
import com.sk.ar.web.test.utils.DateUtils;
import com.sk.ar.web.test.utils.ModelMapperUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AR_EVENT_LOGICAL")
public class ArEventLogicalEntity {

    // 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer arEventLogicalId;

    // 이벤트 아이디
    private Integer arEventId;

    // 판 설정  값(판 위치 셀렉트박스)
    private String panPositionType;

    // 판 미션 수
    private Integer panMissionNumber;

    // 브릿지 타입 값
    private String bridgeType;

    // 브릿지 url
    private String bridgeUrl;

    // 브릿지 노출 시간 여부 값(설정 라디오버튼)
    private String bridgeExposureTimeType;

    // 브릿지 노출 시간 값
    private Integer bridgeExposureTimeSecond;

    // 브릿지 화면 방향  값(화면 방향 라디오 코드 값)
    private String bridgeDisplayDirectionType;

    // 생성자
    private String createdBy;

    // 생성일
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date createdDate;

    // 수정자
    private String lastModifiedBy;

    // 수정일
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date lastModifiedDate;

    public static ArEventLogicalEntity of(int arEventId, EventLogicalDto dto) {
        ArEventLogicalEntity logicalEntity = ModelMapperUtils.getModelMapper().map(dto, ArEventLogicalEntity.class);
        logicalEntity.setArEventId(arEventId);
        logicalEntity.setCreatedDate(DateUtils.returnNowDate());
        return logicalEntity;
    }

    public static ArEventLogicalEntity updateOf(ArEventLogicalEntity arEventLogicalEntity, EventLogicalDto dto) {
        ArEventLogicalEntity logicalEntity = ModelMapperUtils.getModelMapper().map(dto, ArEventLogicalEntity.class);
        logicalEntity.setArEventLogicalId(arEventLogicalEntity.getArEventLogicalId());
        logicalEntity.setArEventId(arEventLogicalEntity.getArEventId());
        logicalEntity.setCreatedDate(arEventLogicalEntity.getCreatedDate());
        logicalEntity.setLastModifiedDate(DateUtils.returnNowDate());
        return logicalEntity;
    }
}
