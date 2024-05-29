package com.eeze.video.service.metadata;

import com.eeze.video.dto.SearchVideoMetadataDTO;
import com.eeze.video.dto.VideoMetadataDTO;

import java.util.List;
import java.util.UUID;

public interface VideoMetadataService {

    VideoMetadataDTO create(VideoMetadataDTO videoMetadataDTO);

    VideoMetadataDTO update(VideoMetadataDTO videoMetadataDTO);

    VideoMetadataDTO getByVideoId(UUID videoId);

    List<VideoMetadataDTO> getAll();

    void deleteByVideoId(UUID metadataId);

    List<VideoMetadataDTO> search(SearchVideoMetadataDTO searchDTO);
}
