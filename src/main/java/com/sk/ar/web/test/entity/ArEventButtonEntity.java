package com.sk.ar.web.test.entity;

import com.sk.ar.web.test.dto.request.EventButtonDto;
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
@Table(name = "AR_EVENT_BUTTON")
public class ArEventButtonEntity {

    // 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer arEventButtonId;

    // 이벤트 아이디
    private Integer arEventId;

    // 버튼 배경색 지정 여부 값(AR_EVENT_CATEGORY)
    private String arButtonBgColorAssignType;

    // 버튼 배경색 지정일떄 RGB, HEX 여부
    private String arButtonBgColorInputType;

    // 버튼 배경색 rgb 값
    private Integer arButtonBgColorRed;

    // 버튼 배경색 rgb 값
    private Integer arButtonBgColorGreen;

    // 버튼 배경색 rgb 값
    private Integer arButtonBgColorBlue;

    // 버튼 배경색 hex 값
    private String arButtonBgColorHex;

    // 버튼색 지정 여부 값
    private String arButtonColorAssignType;

    // 버튼색 지정일떄 RGB, HEX 여부
    private String arButtonColorInputType;

    // 버튼색 rgb 값
    private Integer arButtonColorRed;

    // 버튼색 rgb 값
    private Integer arButtonColorGreen;

    // 버튼색 rgb 값
    private Integer arButtonColorBlue;

    // 버튼색 hex
    private String arButtonColorHex;

    // 버튼 text 색 지정 여부 값
    private String arButtonTextColorAssignType;

    // 버튼 text 색 지정일떄 RGB, HEX 여부
    private String arButtonTextColorInputType;

    // 버튼 text 색 rgb값
    private Integer arButtonTextColorRed;

    // 버튼 text 색 rgb값
    private Integer arButtonTextColorGreen;

    // 버튼 text 색 rgb값
    private Integer arButtonTextColorBlue;

    // 버튼 text 색 hext값
    private String arButtonTextColorHex;

    // 버튼 text 문구 지정
    private String arButtonText;

    // 생성자
    private String createdBy;

    // 생성일
    private Date createdDate;

    // 수정자
    private String lastModifiedBy;

    // 수정일
    private Date lastModifiedDate;

    public static ArEventButtonEntity of(int arEventId, EventButtonDto dto) {
        ArEventButtonEntity entity = ModelMapperUtils.getModelMapper().map(dto, ArEventButtonEntity.class);
        entity.setArEventId(arEventId);
        entity.setCreatedDate(DateUtils.returnNowDate());
        return entity;
    }

    public static ArEventButtonEntity updateOf(ArEventButtonEntity arEventButtonEntity, EventButtonDto dto) {
        ArEventButtonEntity entity = ModelMapperUtils.getModelMapper().map(dto, ArEventButtonEntity.class);
        entity.setArEventButtonId(arEventButtonEntity.getArEventButtonId());
        entity.setArEventId(arEventButtonEntity.getArEventId());
        entity.setCreatedDate(arEventButtonEntity.getCreatedDate());
        entity.setLastModifiedDate(DateUtils.returnNowDate());
        return entity;
    }

}
