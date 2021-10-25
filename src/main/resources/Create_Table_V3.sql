create table ar_event
(
    ar_event_id                             int auto_increment comment 'AR 이벤트 아이디'
        primary key,
    event_id                                varchar(5)                         null comment '이벤트 기본 테이블 아이디',
    event_logical_type                      varchar(10)                        null comment 'AR 구동 정보(기본형 ~ 이미지스캐닝형)',
    location_setting_yn                     tinyint(1)                         null comment '페이지 접속 팝업(위치설정조건)',
    ar_attend_condition_all_yn              tinyint(1)                         null comment 'AR 참여조건(전체)',
    ar_attend_condition_special_position_yn tinyint(1)                         null comment 'AR 참여조건(특정위치)',
    ar_attend_condition_hourly_yn           tinyint(1)                         null comment 'AR 참여조건(시간별)',
    ar_attend_condition_code_yn             tinyint(1)                         null comment 'AR 참여조건(참여번호)',
    ar_attend_term_type                     varchar(10)                        null comment '기간참여조건 타입(제한없음, 기간제한)',
    ar_attend_term_limit_type               varchar(10)                        null comment '기간참여조건 종류(1일, 이벤트기간내)',
    ar_attend_term_limit_count              int                                null comment '기간참여조건 회수',
    pid                                     varchar(50)                        null comment 'pid',
    position_message_attend                 varchar(100)                       null comment '위치메세지 등록(위치 참여시)',
    position_message_not_attend             varchar(100)                       null comment '위치메세지 등록(위치 미 참여시)',
    attend_hour_message                     varchar(100)                       null comment '시간참여 불가시',
    attend_code_mis_match_message           varchar(100)                       null comment '참여번호 미 매칭시',
    ar_bg_image                             varchar(200)                       null comment 'AR BG 이미지',
    ar_skin_image                           varchar(200)                       null comment 'AR 스킨 이미지',
    duplicate_winning_type                  varchar(10)                        null comment '당첨정보(공통)설정 > 중복당첨수 제한 타입',
    duplicate_winning_limit_type            int                                null comment '중복당첨 당첨제한 (전체 : 0, 1일 : 1)',
    duplicate_winning_count                 int                                null comment '중복 당첨 당첨제한 회수',
    created_by                              varchar(50)                        null comment '생성자',
    created_date                            datetime default CURRENT_TIMESTAMP null comment '생성일',
    last_modified_by                        varchar(50)                        null comment '수정자',
    last_modified_date                      datetime                           null comment '수정일'
)
    comment '이벤트 설정(공통) 테이블' charset = utf8;

create table ar_event_attend_time
(
    ar_event_attend_time_id int auto_increment comment '아이디'
        primary key,
    ar_event_id             int         null comment 'AR 이벤트 아이디',
    attend_start_hour       int         null comment '참여시간 설정(시작)',
    attend_end_hour         int         null comment '참여시간 설정(종료)',
    created_by              varchar(50) null comment '생성자',
    created_date            datetime    null comment '생성일',
    last_modified_by        varchar(50) null comment '수정자',
    last_modified_date      datetime    null comment '수정일'
)
    comment '이벤트 설정(공통) 참여 시간 테이블' charset = utf8;

create table ar_event_button
(
    ar_event_button_id               int auto_increment comment '아이디'
        primary key,
    ar_event_id                      int                                not null comment '이벤트 아이디',
    ar_button_bg_color_assign_type   varchar(10)                        null comment '버튼 배경색 지정 여부 값(AR_EVENT_CATEGORY)',
    ar_button_bg_color_input_type    varchar(10)                        null comment '버튼 배경색 지정일떄 RGB, HEX 여부',
    ar_button_bg_color_red           int(3)                             null comment '버튼 배경색 rgb 값',
    ar_button_bg_color_green         int(3)                             null comment '버튼 배경색 rgb 값',
    ar_button_bg_color_blue          int(3)                             null comment '버튼 배경색 rgb 값',
    ar_button_bg_color_hex           varchar(10)                        null comment '버튼 배경색 hex 값',
    ar_button_color_assign_type      varchar(10)                        null comment '버튼색 지정 여부 값',
    ar_button_color_input_type       varchar(10)                        null comment '버튼색 지정일떄 RGB, HEX 여부',
    ar_button_color_red              int(3)                             null comment '버튼색 rgb 값',
    ar_button_color_green            int(3)                             null comment '버튼색 rgb 값',
    ar_button_color_blue             int(3)                             null comment '버튼색 rgb 값',
    ar_button_color_hex              varchar(10)                        null comment '버튼색 hex',
    ar_button_text_color_assign_type varchar(10)                        null comment '버튼 text 색 지정 여부 값',
    ar_button_text_color_input_type  varchar(10)                        null comment '버튼 text 색 지정일떄 RGB, HEX 여부',
    ar_button_text_color_red         int(3)                             null comment '버튼 text 색 rgb값',
    ar_button_text_color_green       int(3)                             null comment '버튼 text 색 rgb값',
    ar_button_text_color_blue        int(3)                             null comment '버튼 text 색 rgb값',
    ar_button_text_color_hex         varchar(10)                        null comment '버튼 text 색 hext값',
    ar_button_text                   varchar(50)                        null comment '버튼 text 문구 지정',
    created_by                       varchar(50)                        null comment '생성자',
    created_date                     datetime default CURRENT_TIMESTAMP null comment '생성일',
    last_modified_by                 varchar(50)                        null comment '수정자',
    last_modified_date               datetime                           null comment '수정일'
)
    comment '이벤트 설정 버튼 정보' charset = utf8;

create table ar_event_category
(
    id             int auto_increment comment '아이디'
        primary key,
    category_code  varchar(10) null comment '카테고리 코드 값',
    category_name  varchar(45) null comment '카테고리 이름',
    category_value varchar(30) null comment '카테고리 값',
    category_type  varchar(45) null comment '카테고리 타입',
    parent_code    varchar(10) null comment '부모 카테고리 코드',
    category_depth int         null comment '카테고리 뎁스'
)
    comment '카테고리 정보';

create table ar_event_gate_code
(
    id                 bigint auto_increment comment '아이디'
        primary key,
    event_id           varchar(5)           not null comment '이벤트 아이디',
    attend_code        varchar(20)          not null comment '참여번호 값',
    use_yn             tinyint(1) default 0 null comment '사용여부',
    created_by         varchar(50)          null comment '생성자',
    created_date       datetime             null comment '생성일',
    last_modified_by   varchar(50)          null comment '수정자',
    last_modified_date datetime             null comment '수정일'
)
    comment '이벤트 코드 테이블(엑셀 업로드)' charset = utf8;

create table ar_event_html
(
    event_html_id                      int auto_increment comment '아이디'
        primary key,
    ar_event_id                        int                                null comment '이벤트 아이디',
    html_type                          int                                null comment 'html 정보 타입(1:이미지, 2:버튼, 3:공유하기)',
    html_image_url                     varchar(100)                       null comment '이미지 url',
    html_button_type                   varchar(10)                        null comment '버튼 유형',
    html_button_bg_color_assign_type   varchar(10)                        null comment '버튼 배경색 지정여부',
    html_button_bg_color_input_type    varchar(10)                        null comment '버튼 배경색 지정일떄 RGB, HEX 여부)',
    html_button_bg_color_reg           int(3)                             null comment '버튼 배경색 rgb 값',
    html_button_bg_color_green         int(3)                             null comment '버튼 배경색 rgb 값',
    html_button_bg_color_blue          int(3)                             null comment '버튼 배경색 rgb 값',
    html_button_bg_color_hex           varchar(10)                        null comment '버튼 배경색 hex 값',
    html_button_text                   varchar(20)                        null comment '버튼 text',
    html_button_target_url             varchar(100)                       null comment '버튼 target url',
    html_type_sort_number              int                                null comment '순서',
    html_share_button_use_yn           tinyint(1)                         null comment '공유하기 버튼 사용 여부',
    html_button_color_assign_type      varchar(10)                        null comment '버튼색 지정여부',
    html_button_color_input_type       varchar(10)                        null comment '버튼색 지정일떄 RGB, HEX 여부)',
    html_button_color_red              int(3)                             null comment '버튼색 rgb 값',
    html_button_color_green            int(3)                             null comment '버튼색 rgb 값',
    html_button_color_blue             int(3)                             null comment '버튼색 rgb 값',
    html_button_color_hex              varchar(10)                        null comment '버튼색 hex 값',
    html_button_text_color_assign_type varchar(10)                        null comment '버튼 텍스트색 지정여부',
    html_button_text_color_input_type  varchar(10)                        null comment '버튼 테스트색 지정일떄 RGB, HEX 여부)',
    html_button_text_color_red         int(3)                             null comment '버튼 테스트색 rgb값',
    html_button_text_color_green       int(3)                             null comment '버튼 테스트색 rgb값',
    html_button_text_color_blue        int(3)                             null comment '버튼 테스트색 rgb값',
    html_button_text_color_hex         varchar(10)                        null comment '버튼 테스트색 rgb값',
    created_by                         varchar(50)                        null comment '생성자',
    created_date                       datetime default CURRENT_TIMESTAMP null comment '생성일',
    last_modified_by                   varchar(50)                        null comment '수정자',
    last_modified_date                 datetime                           null comment '수정일'
)
    comment '이벤트 이미지, 버튼, 공유하기 정보' charset = utf8;

create table ar_event_logical
(
    ar_event_logical_id           int auto_increment comment '아이디'
        primary key,
    ar_event_id                   int          null comment '이벤트 아이디',
    pan_position_type             varchar(10)  null comment '판 설정  값(판 위치 셀렉트박스)',
    pan_mission_number            int          null comment '판 미션 수',
    bridge_type                   varchar(10)  null comment '브릿지 타입 값',
    bridge_url                    varchar(200) null comment '브릿지 url',
    bridge_exposure_time_type     varchar(10)  null comment '브릿지 노출 시간 여부 값(설정 라디오버튼)',
    bridge_exposure_time_second   int          null comment '브릿지 노출 시간 값',
    bridge_display_direction_type varchar(10)  null comment '브릿지 화면 방향  값(화면 방향 라디오 코드 값)',
    created_by                    varchar(50)  null comment '생성자',
    created_date                  datetime     null comment '생성일',
    last_modified_by              varchar(50)  null comment '수정자',
    last_modified_date            datetime     null comment '수정일'
)
    comment 'AR 구동정보 공통 테이블' charset = utf8;

create table ar_event_object
(
    ar_event_object_id              int auto_increment comment '아이디'
        primary key,
    ar_event_id                     int                                   null comment '이벤트 아이디',
    object_sort                     int                                   null comment '오브젝트 순서',
    object_setting_type             varchar(10)                           null comment '오브젝트 설정 값',
    object_setting_url              varchar(100)                          null comment '오브젝트 설정 파일 URL',
    object_size_x                   decimal(3, 1)                         null comment '오브젝트 크기(x)',
    object_size_y                   decimal(3, 1)                         null comment '오브젝트 크기(y)',
    object_size_z                   decimal(3, 1)                         null comment '오브젝트 크기(z)',
    video_play_repeat_type          varchar(10) default '1'               null comment '동영상 재생반복 여부 값',
    object_position_assign_type     varchar(10)                           null comment '오브젝트 위치지정 값',
    object_location_x               decimal(3, 1)                         null comment '오브젝트 위치 지정(x)',
    object_location_y               decimal(3, 1)                         null comment '오브젝트 위치 지정(y)',
    object_location_z               decimal(3, 1)                         null comment '오브젝트 위치 지정(z)',
    stay_effect_type                varchar(10)                           null comment 'STAY EFFECT 설정  값',
    click_event_type                varchar(10)                           null comment '클릭 이벤트 설정  값',
    object_change_setting_type      varchar(10)                           null comment '오브젝트 change 설정 값',
    object_change_setting_video_url varchar(100)                          null comment '오브젝트 change 설정 파일 URL',
    object_change_size_x            decimal(3, 1)                         null comment '오브젝트 change 크기(x)',
    object_change_size_y            decimal(3, 1)                         null comment '오브젝트 change 크기(y)',
    object_change_size_z            decimal(3, 1)                         null comment '오브젝트 change 크기(z)',
    catch_sound_type                varchar(10)                           null comment '캐치 사운드 설정 값',
    catch_sound_file                varchar(100)                          null comment '캐치 사운드  값(URL, Library)',
    exposure_control_type           varchar(10)                           null comment '노출제어 값',
    location_exposure_control_type  varchar(10)                           null comment '위치 노출제어 값',
    location_exposure_control_pid   varchar(45)                           null comment '위치 노출제어',
    max_exposure_type               varchar(10)                           null comment '최대 노출 여부 값',
    max_exposure_count              int                                   null comment '최대 노출 수',
    day_exposure_type               varchar(10)                           null comment '일 노출 여부  값',
    day_exposure_count              int                                   null comment '일 노출 수',
    hour_exposure_type              varchar(10)                           null comment '시간당 노출 여부 값',
    hour_exposure_count             int                                   null comment '시간당 노출 수',
    attend_code_exposure_type       varchar(10)                           null comment '참여번호당 노출수 타입 값',
    attend_code_limit_type          int                                   null comment '참여번호당 노출수 지정시 타입(0:전체기한내, 1일)',
    attend_code_exposure_count      int                                   null comment '참여번호당 노출수',
    exposure_percent_type           varchar(10)                           null comment '노출 확률 여부 값',
    exposure_percent                varchar(6)                            null comment '노출 확률 %(0.01 ~ 100)',
    bridge_type                     varchar(10)                           null comment '브릿지 타입 값',
    bridge_url                      varchar(100)                          null comment '브릿지 파일 url',
    bridge_exposure_time_second     int                                   null comment '브릿지 노출 시간 값',
    bridge_display_direction_type   varchar(10)                           null comment '브릿지 화면 방향  값(화면 방향 라디오 코드 값)',
    mission_inactive_thumbnail_url  varchar(100)                          null comment '미션클리어형 비활성 썸네일 url',
    mission_active_thumbnail_url    varchar(100)                          null comment '미션클리어형 활성 썸네일 url',
    created_by                      varchar(50)                           null comment '생성자',
    created_date                    datetime    default CURRENT_TIMESTAMP null comment '생성일',
    last_modified_by                varchar(50)                           null comment '수정자',
    last_modified_date              datetime                              null comment '수정일'
)
    comment 'AR 구동 정보 테이블(기본형, 브릿지형, 미션클리어판)' charset = utf8;

create table ar_event_scanning_image
(
    ar_event_scanning_image_id int auto_increment comment '아이디'
        primary key,
    ar_event_id                int                                null comment '이미지스캐닝 정보 아이디',
    scanning_image_number      int                                null comment '이미지 설정 넘버',
    scanning_image_url         varchar(100)                       null comment '스캐닝 이미지 url',
    scanning_sound_type        varchar(10)                        null comment '스캐닝 사운드 선택 타입 값',
    scanning_sound_data        varchar(100)                       null comment '스캐닝 사운드 데이터',
    active_thumbnail_url       varchar(100)                       null comment '활성화 썸네일',
    inactive_thumbnail_url     varchar(100)                       null comment '비활성화 썸네일',
    created_by                 varchar(50)                        null comment '생성자',
    created_date               datetime default CURRENT_TIMESTAMP null comment '생성일',
    last_modified_by           varchar(50)                        null comment '수정자',
    last_modified_date         datetime                           null comment '수정일'
)
    comment '이미지스캐닝 스캐닝 이미지 정보 리스트' charset = utf8;

create table ar_event_winning
(
    ar_event_winning_id       int auto_increment comment '아이디'
        primary key,
    ar_event_id               int          null comment '이벤트 아이디',
    event_winning_number      int          null comment '당첨자 정보 설정 넘버',
    object_mapping_type       varchar(10)  null comment '오브젝트 매핑 선택 타입 값',
    object_mapping_number     int          null comment '매핑정보 설정 넘버',
    winning_type              varchar(10)  null comment '당첨 타입  값(기프티콘, 기타, 꽝)',
    gift_card_product_code    varchar(100) null comment '기프티콘 상품 코드 값',
    gift_card_campaign_id     varchar(100) null comment '가프티콘 캠패인 ID 값',
    winning_time_type         varchar(10)  null comment '당첨시간설정 여부  값',
    start_winning_time        int          null comment '당첨 시작 시간(0 ~ 23)',
    end_winning_time          int          null comment '당첨 종료 시간(1 ~ 24)',
    total_winning_number      int          null comment '전체 당첨 수량',
    day_winning_number        int          null comment '일 당첨 수량',
    hour_winning_number       int          null comment '시간당 당첨 수량',
    winning_percent           varchar(6)   null comment '당첨률',
    winning_image_url         varchar(100) null comment '당첨 이미지 url',
    product_name              varchar(50)  null comment '당첨 상품명',
    attend_code_winning_type  varchar(45)  null comment '참여번호당 당첨제한 타입 값',
    attend_code_limit_type    int          null comment '참여번호당 당첨제한 (전체 : 0, 1일 : 1)',
    attend_code_winning_count int          null comment '참여번호당 당첨제한 회수',
    created_by                varchar(50)  null comment '생성자',
    created_date              datetime     null comment '생성일',
    last_modified_by          varchar(50)  null comment '수정자',
    last_modified_date        datetime     null comment '수정일'
)
    comment '이벤트 당첨자 정보 설정' charset = utf8;

create table ar_event_winning_button
(
    ar_event_winning_button_id int auto_increment comment '아이디'
        primary key,
    ar_event_winning_id        int                                  null comment 'AR_EVENT_WINNING.id',
    button_action_type         varchar(10)                          null comment '버튼 액션 타입',
    button_text                varchar(50)                          null comment '버튼 문구',
    button_link_url            varchar(100)                         null comment '버튼 링크 url',
    button_sort_number         int                                  null comment '순서',
    delivery_name_yn           tinyint(1) default 1                 null comment '버튼 액션 타입이 경품배송일때 성명 사용여부',
    delivery_phone_number_yn   tinyint(1) default 1                 null comment '버튼 액션 타입이 경품배송일때 전화번호 사용여부',
    delivery_address_yn        tinyint(1) default 0                 null comment '버튼 액션 타입이 경품배송일때 배송주소 사용여부',
    created_by                 varchar(50)                          null comment '생성자',
    created_date               datetime   default CURRENT_TIMESTAMP null comment '생성일',
    last_modified_by           varchar(50)                          null comment '수정자',
    last_modified_date         datetime                             null comment '수정일'
)
    comment '이벤트 당첨자 버튼 정보' charset = utf8;

create table web_event_base
(
    id                 int auto_increment comment '인덱스'
        primary key,
    event_id           varchar(5)   not null comment '이벤트 아이디(5자리부터 시작)',
    event_title        varchar(45)  null comment '이벤트 타이틀',
    marketing_id       varchar(45)  null comment '계약 인덱스 값',
    contract_status    varchar(10)  null comment '계약상태 값',
    event_type         varchar(10)  null comment '이벤트 종류 타입(AR, ROLLET)',
    event_start_date   date         null comment '서비스 시작일',
    event_end_date     date         null comment '서비스 종료일',
    qr_code_url        varchar(200) null comment 'QR코드 이미지 URL',
    created_by         varchar(50)  null comment '생성자',
    created_date       datetime     null comment '생성일',
    last_modified_by   varchar(50)  null comment '수정자',
    last_modified_date datetime     null comment '수정일'
)
    comment '이벤트 기본 테이블' charset = utf8;



insert into WEVT.AR_EVENT_CATEGORY (id, category_code, category_name, category_value, category_type, parent_code, category_depth)
values  (1, 'PCD_001', '색 지정 타입 라디오버튼', '', 'RADIO', null, 1),
        (2, 'CCD_001', '기본색 사용', 'BASIC', 'RADIO', 'PCD_001', 2),
        (3, 'CCD_002', '색지정', 'ASSIGN', 'RADIO', 'PCD_001', 2),
        (4, 'PCD_002', '색지정 종류 셀렉트박스', null, 'SELECT', null, 1),
        (5, 'CCD_003', 'RGB로 입력', 'RGB', 'SELECT', 'PCD_002', 2),
        (6, 'CCD_004', 'HEX로 입력', 'HEX', 'SELECT', 'PCD_002', 2),
        (7, 'PCD_003', 'AR구동정보 타입 라디오버튼', null, 'RADIO', null, 1),
        (8, 'CCD_005', '기본형', 'BASIC', 'RADIO', 'PCD_003', 2),
        (9, 'CCD_006', '브릿지형', 'BIRDGE', 'RADIO', 'PCD_003', 2),
        (10, 'CCD_007', '미션클리어형', 'MISSION', 'RADIO', 'PCD_003', 2),
        (11, 'CCD_008', '이미지스캐닝형', 'SCANNING', 'RADIO', 'PCD_003', 2),
        (12, 'PCD_004', '오브젝트 설정 타입 라디오버튼', null, 'RADIO', null, 1),
        (13, 'CCD_009', 'IMAGE(2D)', 'IMAGE', 'RADIO', 'PCD_004', 2),
        (14, 'CCD_010', 'GIF(2D)', 'GIF', 'RADIO', 'PCD_004', 2),
        (15, 'CCD_011', 'VIDEO(2D)', 'VIDEO', 'RADIO', 'PCD_004', 2),
        (16, 'CCD_012', 'CUBE(3D)', 'CUBE', 'RADIO', 'PCD_004', 2),
        (17, 'CCD_013', 'CYLINDER(3D)', 'CYLINDER', 'RADIO', 'PCD_004', 2),
        (18, 'CCD_014', 'SPHERE(3D)', 'SPHERE', 'RADIO', 'PCD_004', 2),
        (19, 'CCD_015', '3D', '3D', 'RADIO', 'PCD_004', 2),
        (20, 'PCD_005', '위치지정 셀렉트박스', null, 'SELECT', null, 1),
        (21, 'CCD_016', '랜덤', 'RANDOM', 'SELECT', 'PCD_005', 2),
        (22, 'CCD_017', '직접지정', 'DIRECT', 'SELECT', 'PCD_005', 2),
        (23, 'PCD_006', 'STAY EFFECT 설정 타입 라디오버튼', null, 'RADIO', null, 1),
        (24, 'CCD_018', 'NONE', 'NONE', 'RADIO', 'PCD_006', 2),
        (25, 'CCD_019', 'Rotation', 'ROTATION', 'RADIO', 'PCD_006', 2),
        (26, 'CCD_020', 'Heartbeat', 'HEARTBEAT', 'RADIO', 'PCD_006', 2),
        (27, 'PCD_007', '클릭 이벤트 설정 타입 라디오버튼', null, 'RADIO', null, 1),
        (28, 'CCD_021', 'NONE', 'NONE', 'RADIO', 'PCD_007', 2),
        (29, 'CCD_022', 'Fade-out', 'FADEOUT', 'RADIO', 'PCD_007', 2),
        (30, 'CCD_023', 'Object change', 'OBJCHANGE', 'RADIO', 'PCD_007', 2),
        (31, 'PCD_008', '캐치 사운드 타입 라디오버튼', null, 'RADIO', null, 1),
        (32, 'CCD_024', '선택안함', 'N', 'RADIO', 'PCD_008', 2),
        (33, 'CCD_025', 'URL입력', 'URL', 'RADIO', 'PCD_008', 2),
        (34, 'CCD_026', 'Library선택', 'LIBRARY', 'RADIO', 'PCD_008', 2),
        (35, 'PCD_009', '선택함/선택안함 라디오버튼', null, 'RADIO', null, 1),
        (36, 'CCD_027', '선택안함', 'N', 'RADIO', 'PCD_009', 2),
        (37, 'CCD_028', '선택함', 'Y', 'RADIO', 'PCD_009', 2),
        (38, 'PCD_010', '노출수 제한여부 라디오버튼', null, 'RADIO', null, 1),
        (39, 'CCD_029', '제한없음', 'N', 'RADIO', 'PCD_010', 2),
        (40, 'CCD_030', '노출수 지정', 'Y', 'RADIO', 'PCD_010', 2),
        (41, 'PCD_011', '노출확률 여부 라디오버튼', null, 'RADIO', null, 1),
        (42, 'CCD_031', '전체노출', 'N', 'RADIO', 'PCD_011', 2),
        (43, 'CCD_032', '확률지정', 'Y', 'RADIO', 'PCD_011', 2),
        (44, 'PCD_012', '브릿지타입 라디오버튼', null, 'RADIO', null, 1),
        (45, 'CCD_033', '이미지', 'IMAGE', 'RADIO', 'PCD_012', 2),
        (46, 'CCD_034', 'GIF', 'GIF', 'RADIO', 'PCD_012', 2),
        (47, 'CCD_035', 'VIDEO', 'VIDEO', 'RADIO', 'PCD_012', 2),
        (48, 'CCD_036', '선택안함', 'NONE', 'RADIO', 'PCD_012', 2),
        (49, 'PCD_013', '브릿지 노출시간 라디오버튼', null, 'RADIO', null, 1),
        (50, 'CCD_037', '설정안함', 'N', 'RADIO', 'PCD_013', 2),
        (51, 'CCD_038', '설정', 'Y', 'RADIO', 'PCD_013', 2),
        (52, 'PCD_014', '브릿지 화면방향 라디오버튼', null, 'RADIO', null, 1),
        (53, 'CCD_039', '가로', 'WIDTH', 'RADIO', 'PCD_014', 2),
        (54, 'CCD_040', '세로', 'HEIGHT', 'RADIO', 'PCD_014', 2),
        (55, 'PCD_015', '중복당첨수 제한 라디오버튼', null, 'RADIO', null, 1),
        (56, 'CCD_041', '제한없음', 'N', 'RADIO', 'PCD_015', 2),
        (57, 'CCD_042', '기간제한', 'Y', 'RADIO', 'PCD_015', 2),
        (58, 'PCD_016', '오브젝트 매핑 라디오버튼', null, 'RADIO', null, 1),
        (59, 'CCD_043', '선택안함', 'N', 'RADIO', 'PCD_016', 2),
        (60, 'CCD_044', '매핑정보 설정', 'Y', 'RADIO', 'PCD_016', 2),
        (61, 'PCD_017', '당첨타입 라디오버튼', null, 'RADIO', null, 1),
        (62, 'CCD_045', '기타(배송)', 'ETC', 'RADIO', 'PCD_017', 2),
        (63, 'CCD_046', '기프티콘', 'GIFT', 'RADIO', 'PCD_017', 2),
        (64, 'CCD_047', '꽝', 'FAIL', 'RADIO', 'PCD_017', 2),
        (65, 'PCD_018', '당첨시간설정 라디오버튼', null, 'RADIO', null, 1),
        (66, 'CCD_048', '선택안함', 'N', 'RADIO', 'PCD_018', 2),
        (67, 'CCD_049', '시간설정', 'Y', 'RADIO', 'PCD_018', 2),
        (68, 'PCD_019', '참여번호당 당첨제한 라디오버튼', null, 'RADIO', null, 1),
        (69, 'CCD_050', '선택안함', 'N', 'RADIO', 'PCD_019', 2),
        (70, 'CCD_051', '기간제한', 'Y', 'RADIO', 'PCD_019', 2),
        (71, 'PCD_020', '참여번호당 당첨제한 기간선택 셀렉트박스', null, 'SELECT', null, 1),
        (72, 'CCD_052', '전체기간내', '0', 'SELECT', 'PCD_020', 2),
        (73, 'CCD_053', '1일', '1', 'SELECT', 'PCD_020', 2),
        (74, 'PCD_021', '당첨버튼 종류선택 셀렉트박스', null, 'SELECT', null, 1),
        (75, 'CCD_054', '경품배송입력', 'DELIVERY', 'SELECT', 'PCD_021', 2),
        (76, 'CCD_055', '계속하기(닫기)', 'CLOSE', 'SELECT', 'PCD_021', 2),
        (77, 'CCD_056', 'URL접속', 'URL', 'SELECT', 'PCD_021', 2);