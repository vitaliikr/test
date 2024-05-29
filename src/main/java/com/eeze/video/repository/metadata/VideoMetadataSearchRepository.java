package com.eeze.video.repository.metadata;

import com.eeze.video.dto.SearchVideoMetadataDTO;

import java.util.List;

public interface VideoMetadataSearchRepository {

    List<VideoMetadataEntity> search(SearchVideoMetadataDTO searchDTO);
}
