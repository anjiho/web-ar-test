CREATE TABLE AR_EVENT_CATEGORY
(
    `id`              INT            NOT NULL    AUTO_INCREMENT COMMENT '아이디',
    `category_code`   VARCHAR(10)    NULL        COMMENT '카테고리 코드 값',
    `category_name`   VARCHAR(45)    NULL        COMMENT '카테고리 이름',
    `category_value`  VARCHAR(30)    NULL        COMMENT '카테고리 값',
    `category_type`   VARCHAR(45)    NULL        COMMENT '카테고리 타입',
    `parent_code`     VARCHAR(10)    NULL        COMMENT '부모 카테고리 코드',
    `category_depth`  INT            NULL        COMMENT '카테고리 뎁스',
    PRIMARY KEY (id)
);

ALTER TABLE AR_EVENT_CATEGORY COMMENT '카테고리 정보';