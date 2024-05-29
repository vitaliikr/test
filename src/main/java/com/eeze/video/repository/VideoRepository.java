package com.eeze.video.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, UUID> {

    Optional<VideoEntity> findByIdAndDeactivatedDateIsNull(UUID id);
}
