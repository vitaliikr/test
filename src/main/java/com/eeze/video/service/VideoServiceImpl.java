package com.eeze.video.service;

import com.eeze.video.dto.VideoDTO;
import com.eeze.video.exception.InvalidRequestException;
import com.eeze.video.exception.ResourceNotFoundException;
import com.eeze.video.repository.VideoEntity;
import com.eeze.video.repository.VideoRepository;
import com.eeze.video.service.metadata.VideoMetadataService;
import com.eeze.video.service.storage.VideoStorageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {

    private final VideoStorageService videoStorageService;

    private final VideoRepository videoRepository;

    private final VideoMapper videoMapper;

    private final VideoMetadataService videoMetadataService;

    @Override
    public VideoDTO uploadVideo(MultipartFile video) {
        if (video == null || video.isEmpty()) {
            throw new InvalidRequestException("Missing video");
        }

        var id = UUID.randomUUID();

        var link = videoStorageService.uploadVideoAndGetLink(id, video);

        var entity = new VideoEntity();
        entity.setId(id);
        entity.setOriginalName(video.getOriginalFilename());
        entity.setLink(link);
        entity.setCreatedDate(LocalDateTime.now());
        entity = videoRepository.save(entity);

        return videoMapper.toDTO(entity);
    }

    @Override
    public String downloadVideo(UUID videoId) {
        var videoEntity = videoRepository.findByIdAndDeactivatedDateIsNull(videoId)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found"));

        return videoStorageService.downloadVideo(videoEntity.getLink());
    }

    @Override
    @Transactional
    public void deleteById(UUID videoId) {
        var videoEntityOpt = videoRepository.findByIdAndDeactivatedDateIsNull(videoId);

        if (videoEntityOpt.isPresent()) {
            var videoEntity = videoEntityOpt.get();
            videoEntity.setDeactivatedDate(LocalDateTime.now());
            videoRepository.save(videoEntity);

            videoMetadataService.deleteByVideoId(videoId);
        }
    }
}
