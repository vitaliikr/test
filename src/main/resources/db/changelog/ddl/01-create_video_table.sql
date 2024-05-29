CREATE TABLE video
(
    id               UUID                        NOT NULL,
    original_name    VARCHAR(255)                NOT NULL,
    link             VARCHAR(255),
    created_date     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    deactivated_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_video PRIMARY KEY (id)
);
