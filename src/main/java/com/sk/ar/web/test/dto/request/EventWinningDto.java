package com.sk.ar.web.test.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class EventWinningDto {

    private Integer arEventWinningId;

    // 당첨자 정보 설정 넘버
    private Integer eventWinningNumber;

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

    // 당첨 버튼 정보
    private List<EventWinningButtonDto> arEventWinningButtonInfo;
}
