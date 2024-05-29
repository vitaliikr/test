package com.eeze.video.repository.metadata;

import com.eeze.video.dto.SearchVideoMetadataDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class VideoMetadataSearchRepositoryImpl implements VideoMetadataSearchRepository {

    private final EntityManager entityManager;

    @Override
    public List<VideoMetadataEntity> search(SearchVideoMetadataDTO searchDTO) {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(VideoMetadataEntity.class);

        var videoMetadata = cq.from(VideoMetadataEntity.class);
        List<Predicate> predicates = new ArrayList<>();

        if (searchDTO.getDirector() != null) {
            var predicate = cb.like(videoMetadata.get("director"), "%" + searchDTO.getDirector() + "%");
            predicates.add(predicate);
        }

        if (searchDTO.getTitle() != null) {
            predicates.add(cb.like(videoMetadata.get("title"), "%" + searchDTO.getTitle() + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(cq).getResultList();
    }
}
