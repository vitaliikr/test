package com.eeze.video.service;

import com.eeze.video.dto.VideoDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface VideoService {

    VideoDTO uploadVideo(MultipartFile file);

    String downloadVideo(UUID videoId);

    void deleteById(UUID videoId);
}
