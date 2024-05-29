package com.eeze.video.service.storage;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface VideoStorageService {

    String uploadVideoAndGetLink(UUID id, MultipartFile video);

    String downloadVideo(String link);
}
