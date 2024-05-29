package com.eeze.video.service.metadata;

import com.eeze.video.dto.SearchVideoMetadataDTO;
import com.eeze.video.dto.VideoMetadataDTO;
import com.eeze.video.exception.InvalidRequestException;
import com.eeze.video.exception.ResourceNotFoundException;
import com.eeze.video.repository.metadata.VideoMetadataRepository;
import com.eeze.video.repository.metadata.VideoMetadataSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VideoMetadataServiceImpl implements VideoMetadataService {

    private final VideoMetadataRepository videoMetadataRepository;

    private final VideoMetadataMapper videoMetadataMapper;

    private final VideoMetadataSearchRepository videoMetadataSearchRepository;

    @Override
    public VideoMetadataDTO create(VideoMetadataDTO dto) {
        if (videoMetadataRepository.existsByVideoIdAndDeactivatedDateIsNull(dto.getVideoId())){
            throw new InvalidRequestException("Metadata already exists");
        }

        var entity = videoMetadataMapper.toEntity(dto);
        entity.setId(UUID.randomUUID());
        entity.setCreatedDate(LocalDateTime.now());

        return videoMetadataMapper.toDTO(videoMetadataRepository.save(entity));
    }

    @Override
    public VideoMetadataDTO update(VideoMetadataDTO dto) {
        if (!videoMetadataRepository.existsByIdAndVideoIdAndDeactivatedDateIsNull(dto.getId(), dto.getVideoId())) {
            throw new ResourceNotFoundException("Video Metadata not found");
        }

        var entity = videoMetadataMapper.toEntity(dto);
        entity.setUpdatedDate(LocalDateTime.now());

        return videoMetadataMapper.toDTO(videoMetadataRepository.save(entity));
    }

    @Override
    public VideoMetadataDTO getByVideoId(UUID videoId) {

        return videoMetadataRepository.findByVideoIdAndDeactivatedDateIsNull(videoId)
                .map(videoMetadataMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Video Metadata not found"));
    }

    @Override
    public List<VideoMetadataDTO> getAll() {

        return videoMetadataRepository.findAllByDeactivatedDateIsNull().stream()
                .map(videoMetadataMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VideoMetadataDTO> search(SearchVideoMetadataDTO searchDTO) {
        if (searchDTO == null) {
            return List.of();
        }

        return videoMetadataSearchRepository.search(searchDTO).stream()
                .map(videoMetadataMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteByVideoId(UUID videoId) {
        var metadataOpt = videoMetadataRepository.findByVideoIdAndDeactivatedDateIsNull(videoId);

        if (metadataOpt.isPresent()) {
            var metadata = metadataOpt.get();
            metadata.setDeactivatedDate(LocalDateTime.now());

            videoMetadataRepository.save(metadata);
        }
    }
}
