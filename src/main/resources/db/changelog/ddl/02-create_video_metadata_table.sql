CREATE TABLE video_metadata
(
    id               UUID                        NOT NULL,
    id_video         UUID                        NOT NULL,
    title            VARCHAR(255),
    description      VARCHAR(255),
    director         VARCHAR(255),
    release_date     date,
    running_time     DOUBLE PRECISION,
    views            INTEGER,
    created_date     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_date     TIMESTAMP WITHOUT TIME ZONE,
    deactivated_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_video_metadata PRIMARY KEY (id)
);
