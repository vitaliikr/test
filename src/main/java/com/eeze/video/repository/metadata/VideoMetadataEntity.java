package com.eeze.video.repository.metadata;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "video_metadata")
public class VideoMetadataEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "id_video")
    private UUID videoId;

    @ElementCollection
    @CollectionTable(name = "video_metadata_genre")
    @Column(name = "video_metadata")
    private Set<VideoMetadataGenre> genres = new HashSet<>();

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "director")
    private String director;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "running_time")
    private Double runningTime;

    @Column(name = "views")
    private Integer views;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "deactivated_date", updatable = false)
    private LocalDateTime deactivatedDate;
}


