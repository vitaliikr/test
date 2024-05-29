package com.eeze.video.repository.metadata;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VideoMetadataRepository extends JpaRepository<VideoMetadataEntity, UUID> {

    boolean existsByIdAndVideoIdAndDeactivatedDateIsNull(UUID id, UUID videoId);

    boolean existsByVideoIdAndDeactivatedDateIsNull(UUID videoId);

    Optional<VideoMetadataEntity> findByVideoIdAndDeactivatedDateIsNull(UUID videoId);

    List<VideoMetadataEntity> findAllByDeactivatedDateIsNull();

}
