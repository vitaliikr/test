package com.eeze.video.controller;

import com.eeze.video.dto.SearchVideoMetadataDTO;
import com.eeze.video.dto.VideoMetadataDTO;
import com.eeze.video.service.metadata.VideoMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class VideoMetadataController {

    private final VideoMetadataService videoMetadataService;

    @PostMapping("/videos/{videoId}/metadatas")
    public ResponseEntity<VideoMetadataDTO> createVideoMetadata(@PathVariable("videoId") UUID videoId,
                                                                @RequestBody VideoMetadataDTO dto) {
        dto.setVideoId(videoId);

        return new ResponseEntity<>(videoMetadataService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping("/videos/{videoId}/metadatas")
    public ResponseEntity<VideoMetadataDTO> getVideoMetadata(@PathVariable("videoId") UUID videoId) {

        return ResponseEntity.ok(videoMetadataService.getByVideoId(videoId));
    }

    @PutMapping("/videos/{videoId}/metadatas/{videoMetadataId}")
    public ResponseEntity<VideoMetadataDTO> updateVideoMetadata(@PathVariable("videoId") UUID videoId,
                                                                @PathVariable("videoMetadataId") UUID videoMetadataId,
                                                                @RequestBody VideoMetadataDTO dto) {
        dto.setId(videoMetadataId);
        dto.setVideoId(videoId);

        return ResponseEntity.ok(videoMetadataService.update(dto));
    }

    @GetMapping("/videos/metadatas")
    public ResponseEntity<List<VideoMetadataDTO>> getAllVideosMetadata() {

        return ResponseEntity.ok(videoMetadataService.getAll());
    }

    @PostMapping("/videos/metadatas/search")
    public ResponseEntity<List<VideoMetadataDTO>> searchVideosMetadata(@RequestBody SearchVideoMetadataDTO searchDTO) {

        return ResponseEntity.ok(videoMetadataService.search(searchDTO));
    }
}
