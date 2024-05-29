ALTER TABLE video_metadata_genre
    ADD CONSTRAINT FK_VIDEO_METADATA_GENRE_ON_VIDEO_METADATA_ENTITY FOREIGN KEY (video_metadata_entity_id) REFERENCES video_metadata (id);
