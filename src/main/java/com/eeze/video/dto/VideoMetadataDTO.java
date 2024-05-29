package com.eeze.video.dto;

import com.eeze.video.repository.metadata.VideoMetadataGenre;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class VideoMetadataDTO {

    private UUID id;

    private UUID videoId;

    private List<VideoMetadataGenre> genres = new ArrayList<>();

    private String title;

    private String description;

    private String director;

    private LocalDate releaseDate;

    private Double runningTime;
}
