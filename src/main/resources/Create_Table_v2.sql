CREATE TABLE WEB_EVENT_BASE
(
    `id`                  INT             NOT NULL    AUTO_INCREMENT COMMENT '인덱스',
    `event_id`            VARCHAR(5)      NOT NULL    COMMENT '이벤트 아이디(5자리부터 시작)',
    `event_title`         VARCHAR(45)     NULL        COMMENT '이벤트 타이틀',
    `marketing_id`        VARCHAR(45)     NULL        COMMENT '계약 인덱스 값',
    `contract_status`     VARCHAR(10)     NULL        COMMENT '계약상태 값',
    `event_type`          VARCHAR(10)     NULL        COMMENT '이벤트 종류 타입(AR, ROLLET)',
    `event_start_date`    DATE            NULL        COMMENT '서비스 시작일',
    `event_end_date`      DATE            NULL        COMMENT '서비스 종료일',
    `qr_code_url`         VARCHAR(200)    NULL        COMMENT 'QR코드 이미지 URL',
    `created_by`          VARCHAR(50)     NULL        COMMENT '생성자',
    `created_date`        DATETIME        NULL        COMMENT '생성일',
    `last_modified_by`    VARCHAR(50)     NULL        COMMENT '수정자',
    `last_modified_date`  DATETIME        NULL        COMMENT '수정일',
    PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE WEB_EVENT_BASE COMMENT '이벤트 기본 테이블';

CREATE TABLE AR_EVENT
(
    `ar_event_id`                              INT             NOT NULL    AUTO_INCREMENT COMMENT 'AR 이벤트 아이디',
    `event_id`                                 VARCHAR(5)      NULL        COMMENT '이벤트 기본 테이블 아이디',
    `event_logical_type`                       VARCHAR(10)     NULL        COMMENT 'AR 구동 정보(기본형 ~ 이미지스캐닝형)',
    `page_connect_popup_yn`                    TINYINT(1)      NULL        COMMENT '페이지 접속 팝업',
    `ar_attend_condition_all_yn`               TINYINT(1)      NULL        COMMENT 'AR 참여조건(전체)',
    `ar_attend_condition_special_position_yn`  TINYINT(1)      NULL        COMMENT 'AR 참여조건(특정위치)',
    `ar_attend_condition_hourly_yn`            TINYINT(1)      NULL        COMMENT 'AR 참여조건(시간별)',
    `ar_attend_condition_code_yn`              TINYINT(1)      NULL        COMMENT 'AR 참여조건(참여번호)',
    `pid`                                      VARCHAR(50)     NULL        COMMENT 'pid',
    `position_message_attend`                  VARCHAR(100)    NULL        COMMENT '위치메세지 등록(위치 참여시)',
    `position_message_not_attend`              VARCHAR(100)    NULL        COMMENT '위치메세지 등록(위치 미 참여시)',
    `attend_hour_start`                        INT             NULL        COMMENT '참여시간 설정(시작)',
    `attend_hour_end`                          INT             NULL        COMMENT '참여시간 설정(종료)',
    `attend_hour_message`                      VARCHAR(100)    NULL        COMMENT '시간참여 불가시',
    `attend_code_mis_match_message`            VARCHAR(100)    NULL        COMMENT '참여번호 미 매칭시',
    `created_by`                               VARCHAR(50)     NULL        COMMENT '생성자',
    `created_date`                             DATETIME        NULL        DEFAULT now() COMMENT '생성일',
    `last_modified_by`                         VARCHAR(50)     NULL        COMMENT '수정자',
    `last_modified_date`                       DATETIME        NULL        COMMENT '수정일',
    CONSTRAINT PK_AR_EVENT PRIMARY KEY (ar_event_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE AR_EVENT COMMENT '이벤트 설정(공통) 테이블';

CREATE TABLE AR_EVENT_OBJECT
(
    `ar_event_object_id`               INT              NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `ar_event_id`                      INT              NULL        COMMENT '이벤트 아이디',
    `object_sort`                      INT              NULL        COMMENT '오브젝트 순서',
    `object_setting_type`              VARCHAR(10)      NULL        COMMENT '오브젝트 설정 값',
    `object_setting_url`               VARCHAR(100)     NULL        COMMENT '오브젝트 설정 파일 URL',
    `object_size_x`                    NUMERIC(3, 1)    NULL        COMMENT '오브젝트 크기(x)',
    `object_size_y`                    NUMERIC(3, 1)    NULL        COMMENT '오브젝트 크기(y)',
    `object_size_z`                    NUMERIC(3, 1)    NULL        COMMENT '오브젝트 크기(z)',
    `video_play_repeat_type`           VARCHAR(10)      NULL        DEFAULT '1' COMMENT '동영상 재생반복 여부 값',
    `object_position_assign_type`      VARCHAR(10)      NULL        COMMENT '오브젝트 위치지정 값',
    `object_location_x`                NUMERIC(3, 1)    NULL        COMMENT '오브젝트 위치 지정(x)',
    `object_location_y`                NUMERIC(3, 1)    NULL        COMMENT '오브젝트 위치 지정(y)',
    `object_location_z`                NUMERIC(3, 1)    NULL        COMMENT '오브젝트 위치 지정(z)',
    `stay_effect_type`                 VARCHAR(10)      NULL        COMMENT 'STAY EFFECT 설정  값',
    `click_event_type`                 VARCHAR(10)      NULL        COMMENT '클릭 이벤트 설정  값',
    `object_change_setting_type`       VARCHAR(10)      NULL        COMMENT '오브젝트 change 설정 값',
    `object_change_setting_video_url`  VARCHAR(100)     NULL        COMMENT '오브젝트 change 설정 파일 URL',
    `object_change_size_x`             NUMERIC(3, 1)    NULL        COMMENT '오브젝트 change 크기(x)',
    `object_change_size_y`             NUMERIC(3, 1)    NULL        COMMENT '오브젝트 change 크기(y)',
    `object_change_size_z`             NUMERIC(3, 1)    NULL        COMMENT '오브젝트 change 크기(z)',
    `catch_sound_type`                 VARCHAR(10)      NULL        COMMENT '캐치 사운드 설정 값',
    `catch_sound_file`                 VARCHAR(100)     NULL        COMMENT '캐치 사운드  값(URL, Library)',
    `exposure_control_type`            VARCHAR(10)      NULL        COMMENT '노출제어 값',
    `location_exposure_control_type`   VARCHAR(10)      NULL        COMMENT '위치 노출제어 값',
    `location_exposure_control_pid`    VARCHAR(45)      NULL        COMMENT '위치 노출제어',
    `max_exposure_type`                VARCHAR(10)      NULL        COMMENT '최대 노출 여부 값',
    `max_exposure_count`               INT              NULL        COMMENT '최대 노출 수',
    `day_exposure_type`                VARCHAR(10)      NULL        COMMENT '일 노출 여부  값',
    `day_exposure_count`               INT              NULL        COMMENT '일 노출 수',
    `hour_exposure_type`               VARCHAR(10)      NULL        COMMENT '시간당 노출 여부 값',
    `hour_exposure_count`              INT              NULL        COMMENT '시간당 노출 수',
    `attend_code_exposure_type`        VARCHAR(10)      NULL        COMMENT '참여번호당 노출수 타입 값',
    `attend_code_limit_type`           INT              NULL        COMMENT '참여번호당 노출수 지정시 타입(0:전체기한내, 1일)',
    `attend_code_exposure_count`       INT              NULL        COMMENT '참여번호당 노출수',
    `exposure_percent_type`            VARCHAR(10)      NULL        COMMENT '노출 확률 여부 값',
    `exposure_percent`                 VARCHAR(4)       NULL        COMMENT '노출 확률 %(0.01 ~ 100)',
    `bridge_type`                      VARCHAR(10)      NULL        COMMENT '브릿지 타입 값',
    `bridge_url`                       VARCHAR(100)     NULL        COMMENT '브릿지 파일 url',
    `bridge_exposure_time_second`      INT              NULL        COMMENT '브릿지 노출 시간 값',
    `bridge_display_direction_type`    VARCHAR(10)      NULL        COMMENT '브릿지 화면 방향  값(화면 방향 라디오 코드 값)',
    `mission_inactive_thumbnail_url`   VARCHAR(100)     NULL        COMMENT '미션클리어형 비활성 썸네일 url',
    `mission_active_thumbnail_url`     VARCHAR(100)     NULL        COMMENT '미션클리어형 활성 썸네일 url',
    `created_by`                       VARCHAR(50)      NULL        COMMENT '생성자',
    `created_date`                     DATETIME         NULL        DEFAULT now() COMMENT '생성일',
    `last_modified_by`                 VARCHAR(50)      NULL        COMMENT '수정자',
    `last_modified_date`               DATETIME         NULL        COMMENT '수정일',
    CONSTRAINT PK_AR_EVENT_LOGICAL_TYPE PRIMARY KEY (ar_event_object_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE AR_EVENT_OBJECT COMMENT 'AR 구동 정보 테이블(기본형, 브릿지형, 미션클리어판)';

CREATE TABLE AR_EVENT_ATTEND_TIME
(
    `ar_event_attend_time_id`  INT            NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `ar_event_id`              INT            NULL        COMMENT 'AR 이벤트 아이디',
    `attend_start_hour`        INT            NULL        COMMENT '참여시간 설정(시작)',
    `attend_end_hour`          INT            NULL        COMMENT '참여시간 설정(종료)',
    `created_by`               VARCHAR(50)    NULL        COMMENT '생성자',
    `created_date`             DATETIME       NULL        COMMENT '생성일',
    `last_modified_by`         VARCHAR(50)    NULL        COMMENT '수정자',
    `last_modified_date`       DATETIME       NULL        COMMENT '수정일',
    PRIMARY KEY (ar_event_attend_time_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE AR_EVENT_ATTEND_TIME COMMENT '이벤트 설정(공통) 참여 시간 테이블';

CREATE TABLE AR_EVENT_LOGICAL
(
    `ar_event_logical_id`            INT             NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `ar_event_id`                    INT             NULL        COMMENT '이벤트 아이디',
    `pan_position_type`              VARCHAR(10)     NULL        COMMENT '판 설정  값(판 위치 셀렉트박스)',
    `pan_mission_number`             INT             NULL        COMMENT '판 미션 수',
    `bridge_type`                    VARCHAR(10)     NULL        COMMENT '브릿지 타입 값',
    `bridge_url`                     VARCHAR(200)    NULL        COMMENT '브릿지 url',
    `bridge_exposure_time_type`      VARCHAR(10)     NULL        COMMENT '브릿지 노출 시간 여부 값(설정 라디오버튼)',
    `bridge_exposure_time_second`    INT             NULL        COMMENT '브릿지 노출 시간 값',
    `bridge_display_direction_type`  VARCHAR(10)     NULL        COMMENT '브릿지 화면 방향  값(화면 방향 라디오 코드 값)',
    `created_by`                     VARCHAR(50)     NULL        COMMENT '생성자',
    `created_date`                   DATETIME        NULL        COMMENT '생성일',
    `last_modified_by`               VARCHAR(50)     NULL        COMMENT '수정자',
    `last_modified_date`             DATETIME        NULL        COMMENT '수정일',
    PRIMARY KEY (ar_event_logical_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE AR_EVENT_LOGICAL COMMENT 'AR 구동정보 공통 테이블';

CREATE TABLE AR_EVENT_SCANNING_IMAGE
(
    `ar_event_scanning_image_id`  INT             NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `ar_event_id`                 INT             NULL        COMMENT '이미지스캐닝 정보 아이디',
    `scanning_image_number`       INT             NULL        COMMENT '이미지 설정 넘버',
    `scanning_image_url`          VARCHAR(100)    NULL        COMMENT '스캐닝 이미지 url',
    `scanning_sound_type`         VARCHAR(10)     NULL        COMMENT '스캐닝 사운드 선택 타입 값',
    `scanning_sound_data`         VARCHAR(100)    NULL        COMMENT '스캐닝 사운드 데이터',
    `active_thumbnail_url`        VARCHAR(100)    NULL        COMMENT '활성화 썸네일',
    `inactive_thumbnail_url`      VARCHAR(100)    NULL        COMMENT '비활성화 썸네일',
    `created_by`                  VARCHAR(50)     NULL        COMMENT '생성자',
    `created_date`                DATETIME        NULL        DEFAULT now() COMMENT '생성일',
    `last_modified_by`            VARCHAR(50)     NULL        COMMENT '수정자',
    `last_modified_date`          DATETIME        NULL        COMMENT '수정일',
    PRIMARY KEY (ar_event_scanning_image_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE AR_EVENT_SCANNING_IMAGE COMMENT '이미지스캐닝 스캐닝 이미지 정보 리스트';

CREATE TABLE AR_EVENT_BUTTON
(
    `ar_event_button_id`                INT             NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `ar_event_id`                       INT             NOT NULL    COMMENT '이벤트 아이디',
    `ar_button_bg_color_assign_type`    VARCHAR(10)     NULL        COMMENT '버튼 배경색 지정 여부 값(AR_EVENT_CATEGORY)',
    `ar_button_bg_color_red`            INT(3)          NULL        COMMENT '버튼 배경색 rgb 값',
    `ar_button_bg_color_green`          INT(3)          NULL        COMMENT '버튼 배경색 rgb 값',
    `ar_button_bg_color_blue`           INT(3)          NULL        COMMENT '버튼 배경색 rgb 값',
    `ar_button_bg_color_hex`            VARCHAR(10)     NULL        COMMENT '버튼 배경색 hex 값',
    `ar_button_color_assign_type`       VARCHAR(10)     NULL        COMMENT '버튼색 지정 여부 값',
    `ar_button_color_red`               INT(3)          NULL        COMMENT '버튼색 rgb 값',
    `ar_button_color_green`             INT(3)          NULL        COMMENT '버튼색 rgb 값',
    `ar_button_color_blue`              INT(3)          NULL        COMMENT '버튼색 rgb 값',
    `ar_button_color_hex`               VARCHAR(10)     NULL        COMMENT '버튼색 hex',
    `ar_button_text_color_assign_type`  VARCHAR(10)     NULL        COMMENT '버튼 text 색 지정 여부 값',
    `ar_button_text_color_red`          INT(3)          NULL        COMMENT '버튼 text 색 rgb값',
    `ar_button_text_color_green`        INT(3)          NULL        COMMENT '버튼 text 색 rgb값',
    `ar_button_text_color_blue`         INT(3)          NULL        COMMENT '버튼 text 색 rgb값',
    `ar_button_text_color_hex`          VARCHAR(10)     NULL        COMMENT '버튼 text 색 hext값',
    `ar_button_text`                    VARCHAR(50)     NULL        COMMENT '버튼 text 문구 지정',
    `ar_bg_image`                       VARCHAR(200)    NULL        COMMENT 'AR BG 이미지',
    `ar_skin_image`                     VARCHAR(200)    NULL        COMMENT 'AR 스킨 이미지',
    `created_by`                        VARCHAR(50)     NULL        COMMENT '생성자',
    `created_date`                      DATETIME        NULL        DEFAULT now() COMMENT '생성일',
    `last_modified_by`                  VARCHAR(50)     NULL        COMMENT '수정자',
    `last_modified_date`                DATETIME        NULL        COMMENT '수정일',
    CONSTRAINT PK_AR_EVENT_MAIN_BUTTON PRIMARY KEY (ar_event_button_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE AR_EVENT_BUTTON COMMENT '이벤트 설정 버튼 정보';

CREATE TABLE AR_EVENT_WINNING
(
    `ar_event_winning_id`     INT             NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `ar_event_id`             INT             NULL        COMMENT '이벤트 아이디',
    `event_winning_number`    INT             NULL        COMMENT '당첨자 정보 설정 넘버',
    `object_mapping_type`     VARCHAR(10)     NULL        COMMENT '오브젝트 매핑 선택 타입 값',
    `object_mapping_number`   INT             NULL        COMMENT '매핑정보 설정 넘버',
    `winning_type`            VARCHAR(10)     NULL        COMMENT '당첨 타입  값(기프티콘, 기타, 꽝)',
    `gift_card_product_code`  VARCHAR(100)    NULL        COMMENT '기프티콘 상품 코드 값',
    `gift_card_campaign_id`   VARCHAR(100)    NULL        COMMENT '가프티콘 캠패인 ID 값',
    `winning_time_type`       VARCHAR(10)     NULL        COMMENT '당첨시간설정 여부  값',
    `start_winning_time`      INT             NULL        COMMENT '당첨 시작 시간(0 ~ 23)',
    `end_winning_time`        INT             NULL        COMMENT '당첨 종료 시간(1 ~ 24)',
    `total_winning_number`    INT             NULL        COMMENT '전체 당첨 수량',
    `day_winning_number`      INT             NULL        COMMENT '일 당첨 수량',
    `hour_winning_number`     INT             NULL        COMMENT '시간당 당첨 수량',
    `winning_percent`         VARCHAR(4)      NULL        COMMENT '당첨률',
    `winning_image_url`       VARCHAR(100)    NULL        COMMENT '당첨 이미지 url',
    `product_name`            VARCHAR(50)     NULL        COMMENT '당첨 상품명',
    `created_by`              VARCHAR(50)     NULL        COMMENT '생성자',
    `created_date`            DATETIME        NULL        COMMENT '생성일',
    `last_modified_by`        VARCHAR(50)     NULL        COMMENT '수정자',
    `last_modified_date`      DATETIME        NULL        COMMENT '수정일',
    PRIMARY KEY (ar_event_winning_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE AR_EVENT_WINNING COMMENT '이벤트 당첨자 정보 설정';

CREATE TABLE AR_EVENT_WINNING_BUTTON
(
    `id`                        INT             NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `ar_event_winning_id`       INT             NULL        COMMENT 'AR_EVENT_WINNING.id',
    `button_action_type`        VARCHAR(10)     NULL        COMMENT '버튼 액션 타입',
    `button_text`               VARCHAR(50)     NULL        COMMENT '버튼 문구',
    `button_link_url`           VARCHAR(100)    NULL        COMMENT '버튼 링크 url',
    `button_sort_number`        INT             NULL        COMMENT '순서',
    `delivery_name_yn`          TINYINT(1)      NULL        DEFAULT 1 COMMENT '버튼 액션 타입이 경품배송일때 성명 사용여부',
    `delivery_phone_number_yn`  TINYINT(1)      NULL        DEFAULT 1 COMMENT '버튼 액션 타입이 경품배송일때 전화번호 사용여부',
    `delivery_address_yn`       TINYINT(1)      NULL        DEFAULT 0 COMMENT '버튼 액션 타입이 경품배송일때 배송주소 사용여부',
    `created_by`                VARCHAR(50)     NULL        COMMENT '생성자',
    `created_date`              DATETIME        NULL        DEFAULT now() COMMENT '생성일',
    `last_modified_by`          VARCHAR(50)     NULL        COMMENT '수정자',
    `last_modified_date`        DATETIME        NULL        COMMENT '수정일',
    PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE AR_EVENT_WINNING_BUTTON COMMENT '이벤트 당첨자 버튼 정보';

CREATE TABLE AR_EVENT_GATE_CODE
(
    `id`                  INT            NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `event_id`            VARCHAR(5)     NOT NULL    COMMENT '이벤트 아이디',
    `attend_code`         VARCHAR(20)    NOT NULL    COMMENT '참여번호 값',
    `use_yn`              TINYINT(1)     NULL        DEFAULT 0 COMMENT '사용여부',
    `created_by`          VARCHAR(50)    NULL        COMMENT '생성자',
    `created_date`        DATETIME       NULL        COMMENT '생성일',
    `last_modified_by`    VARCHAR(50)    NULL        COMMENT '수정자',
    `last_modified_date`  DATETIME       NULL        COMMENT '수정일',
    PRIMARY KEY (id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE AR_EVENT_GATE_CODE COMMENT '이벤트 코드 테이블(엑셀 업로드)';

CREATE TABLE AR_EVENT_HTML
(
    `event_html_id`                     INT             NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `event_id`                          INT             NULL        COMMENT '이벤트 아이디',
    `html_type`                         INT             NULL        COMMENT 'html 정보 타입(1:이미지, 2:버튼, 3:공유하기)',
    `html_image_url`                    VARCHAR(100)    NULL        COMMENT '이미지 url',
    `html_button_type`                  VARCHAR(10)     NULL        COMMENT '버튼 유형',
    `html_button_bg_color_assign_type`  INT             NULL        COMMENT '버튼 배경색 지정여부',
    `html_button_bg_color_reg`          INT(3)          NULL        COMMENT '버튼 배경색 rgb 값',
    `html_button_bg_color_green`        INT(3)          NULL        COMMENT '버튼 배경색 rgb 값',
    `html_button_bg_color_blue`         INT(3)          NULL        COMMENT '버튼 배경색 rgb 값',
    `html_button_bg_color_hex`          VARCHAR(10)     NULL        COMMENT '버튼 배경색 hex 값',
    `html_button_text`                  VARCHAR(20)     NULL        COMMENT '버튼 text',
    `html_button_target_url`            VARCHAR(100)    NULL        COMMENT '버튼 target url',
    `html_share_button_use_yn`          TINYINT(1)      NULL        COMMENT '공유하기 버튼 사용 여부',
    `html_type_sort_number`             INT             NULL        COMMENT '순서',
    `created_by`                        VARCHAR(50)     NULL        COMMENT '생성자',
    `created_date`                      DATETIME        NULL        DEFAULT now() COMMENT '생성일',
    `last_modified_by`                  VARCHAR(50)     NULL        COMMENT '수정자',
    `last_modified_date`                DATETIME        NULL        COMMENT '수정일',
    PRIMARY KEY (event_html_id)
)ENGINE = InnoDB DEFAULT CHARSET = utf8;

ALTER TABLE AR_EVENT_HTML COMMENT '이벤트 이미지, 버튼, 공유하기 정보';

