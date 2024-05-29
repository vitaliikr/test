package com.eeze.video.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "video")
public class VideoEntity {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "original_name")
    private String originalName;

    @Column(name = "link")
    private String link;

    @Column(name = "created_date", updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "deactivated_date", updatable = false)
    private LocalDateTime deactivatedDate;
}
