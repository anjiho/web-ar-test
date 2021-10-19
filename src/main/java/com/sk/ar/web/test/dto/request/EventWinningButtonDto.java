package com.sk.ar.web.test.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventWinningButtonDto {

    // AR_EVENT_WINNING.id
    private Integer arEventWinningId;

    // 버튼 액션 타입(1 : 계속하기, 2 : url 접속)
    private String buttonActionType;

    // 버튼 문구
    private String buttonText;

    // 버튼 링크 url
    private String buttonLinkUrl;

    // 순서
    private Integer buttonSortNumber;

}
