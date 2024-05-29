package com.eeze.video.service;

import com.eeze.video.dto.VideoDTO;
import com.eeze.video.repository.VideoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    VideoDTO toDTO(VideoEntity entity);
}
