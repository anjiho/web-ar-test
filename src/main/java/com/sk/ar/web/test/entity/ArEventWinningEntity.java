package com.sk.ar.web.test.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "AR_EVENT_WINNING")
public class ArEventWinningEntity {

    // 아이디
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // 아이디
    private Integer arEventWinningId;

    // 이벤트 아이디
    private Integer arEventId;

    // 당첨자 정보 설정 넘버
    private Integer eventWinningSort;

    // 오브젝트 매핑 선택 타입 값
    private String objectMappingType;

    // 매핑정보 설정 넘버
    private Integer objectMappingNumber;

    // 당첨 타입  값(기프티콘, 기타, 꽝)
    private String winningType;

    // 기프티콘 상품 코드 값
    private String giftCardProductCode;

    // 가프티콘 캠패인 ID 값
    private String giftCardCampaignId;

    // 당첨시간설정 여부  값
    private String winningTimeType;

    // 당첨 시작 시간(0 ~ 23)
    private Integer startWinningTime;

    // 당첨 종료 시간(1 ~ 24)
    private Integer endWinningTime;

    // 전체 당첨 수량
    private Integer totalWinningNumber;

    // 일 당첨 수량
    private Integer dayWinningNumber;

    // 시간당 당첨 수량
    private Integer hourWinningNumber;

    // 당첨률
    private String winningPercent;

    // 당첨 이미지 url
    private String winningImageUrl;

    // 당첨 상품명
    private String productName;

    // 참여번호당 당첨제한 타입 값
    private String attendCodeWinningType;

    // 참여번호당 당첨제한 (전체 : 0 , 1일 : 1)
    private Integer attendCodeLimitType;

    // 참여번호당 당첨제한 회수
    private Integer attendCodeWinningCount;

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
}
