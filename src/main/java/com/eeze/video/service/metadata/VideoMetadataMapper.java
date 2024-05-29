package com.eeze.video.service.metadata;

import com.eeze.video.dto.VideoMetadataDTO;
import com.eeze.video.repository.metadata.VideoMetadataEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoMetadataMapper {

    VideoMetadataDTO toDTO(VideoMetadataEntity entity);

    VideoMetadataEntity toEntity(VideoMetadataDTO dto);
}
