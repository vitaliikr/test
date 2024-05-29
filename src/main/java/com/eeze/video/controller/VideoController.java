package com.eeze.video.controller;

import com.eeze.video.dto.VideoDTO;
import com.eeze.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping(value = "/videos/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<VideoDTO> uploadVideo(@RequestPart("video") MultipartFile video) {

        return new ResponseEntity<>(videoService.uploadVideo(video), HttpStatus.CREATED);
    }

    @GetMapping(value = "/videos/{videoId}/download")
    public ResponseEntity<String> downloadVideo(@PathVariable("videoId") UUID videoId) {

        return new ResponseEntity<>(videoService.downloadVideo(videoId), HttpStatus.OK);
    }

    @DeleteMapping("/videos/{videoId}")
    public ResponseEntity<Void> deleteVideo(@PathVariable("videoId") UUID videoId) {
        videoService.deleteById(videoId);

        return ResponseEntity.noContent().build();
    }
}
