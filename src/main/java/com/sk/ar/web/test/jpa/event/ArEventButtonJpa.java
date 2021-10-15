package com.sk.ar.web.test.jpa.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sk.ar.web.test.dto.request.EventButtonDto;
import com.sk.ar.web.test.dto.request.EventSaveDto;
import com.sk.ar.web.test.utils.DateUtils;
import lombok.Builder;
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
public class ArEventButtonJpa {

    // 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // 이벤트 아이디
    private Integer eventId;

    // 버튼 배경색 지정 여부 값(AR_EVENT_CATEGORY)
    private String arButtonBgColorAssignType;

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

    // AR BG 이미지
    private String arBgImage;

    // AR 스킨 이미지
    private String arSkinImage;

    // 생성일
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="Asia/Seoul")
    private Date createdDate;

    @Builder
    public ArEventButtonJpa(int eventId, EventButtonDto dto) {
        this.eventId = eventId;
        this.arButtonBgColorAssignType = dto.getArButtonBgColorAssignType();
        this.arButtonBgColorRed = dto.getArButtonBgColorRed();
        this.arButtonBgColorGreen = dto.getArButtonBgColorGreen();
        this.arButtonBgColorBlue = dto.getArButtonBgColorBlue();
        this.arButtonBgColorHex = dto.getArButtonBgColorHex();
        this.arButtonColorAssignType = dto.getArButtonColorAssignType();
        this.arButtonColorRed = dto.getArButtonColorRed();
        this.arButtonColorGreen = dto.getArButtonColorGreen();
        this.arButtonColorBlue = dto.getArButtonColorBlue();
        this.arButtonColorHex = dto.getArButtonColorHex();
        this.arButtonTextColorAssignType = dto.getArButtonTextColorAssignType();
        this.arButtonTextColorRed = dto.getArButtonTextColorRed();
        this.arButtonTextColorGreen = dto.getArButtonTextColorGreen();
        this.arButtonTextColorBlue = dto.getArButtonTextColorBlue();
        this.arButtonTextColorHex = dto.getArButtonTextColorHex();
        this.arButtonText = dto.getArButtonText();
        this.arBgImage = dto.getArBgImage();
        this.arSkinImage = dto.getArSkinImage();
        this.createdDate = DateUtils.returnNowDate();
    }
}
