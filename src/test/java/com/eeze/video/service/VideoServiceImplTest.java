package com.eeze.video.service;

import com.eeze.video.exception.InvalidRequestException;
import com.eeze.video.exception.ResourceNotFoundException;
import com.eeze.video.repository.VideoEntity;
import com.eeze.video.repository.VideoRepository;
import com.eeze.video.service.metadata.VideoMetadataService;
import com.eeze.video.service.storage.VideoStorageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VideoServiceImplTest {

    @Mock
    private VideoStorageService videoStorageService;

    @Mock
    private VideoRepository videoRepository;

    @Spy
    private VideoMapper videoMapper = Mappers.getMapper(VideoMapper.class);

    @Mock
    private VideoMetadataService videoMetadataService;

    @InjectMocks
    private VideoServiceImpl videoServiceImpl;

    @Test
    void shouldThrowExceptionWhenMissingVideo() {
        // given
        MultipartFile video = null;

        // when then
        assertThrows(InvalidRequestException.class, () -> videoServiceImpl.uploadVideo(video));
    }

    @Test
    void shouldUploadVideo() {
        // given
        var video = getMultipartFile();
        var mockLink = "link";

        when(videoStorageService.uploadVideoAndGetLink(any(UUID.class), eq(video)))
                .thenReturn(mockLink);

        when(videoRepository.save(any(VideoEntity.class)))
                .then(invocation -> invocation.getArgument(0));

        // when
        var result = videoServiceImpl.uploadVideo(video);

        // then
        assertNotNull(result.getId());
        assertEquals(video.getOriginalFilename(), result.getOriginalName());

        verify(videoMapper).toDTO(any(VideoEntity.class));
    }

    @Test
    void throwExceptionWhenVideoNotFound() {
        // given
        var videoId = UUID.randomUUID();

        when(videoRepository.findByIdAndDeactivatedDateIsNull(videoId))
                .thenReturn(java.util.Optional.empty());

        // when then
        assertThrows(ResourceNotFoundException.class, () -> videoServiceImpl.downloadVideo(videoId));
    }

    @Test
    void shouldDownloadVideo() {
        // given
        var videoId = UUID.randomUUID();
        var videoEntity = new VideoEntity();
        var link = "link";
        videoEntity.setLink(link);

        when(videoRepository.findByIdAndDeactivatedDateIsNull(videoId))
                .thenReturn(Optional.of(videoEntity));

        when(videoStorageService.downloadVideo(link))
                .thenReturn("video");

        // when
        var result = videoServiceImpl.downloadVideo(videoId);

        // then
        assertEquals("video", result);
    }

    @Test
    void shouldDeleteVideo() {
        // given
        var videoId = UUID.randomUUID();
        var videoEntity = new VideoEntity();

        when(videoRepository.findByIdAndDeactivatedDateIsNull(videoId))
                .thenReturn(Optional.of(videoEntity));

        // when
        videoServiceImpl.deleteById(videoId);

        // then
        verify(videoRepository).save(videoEntity);
        verify(videoMetadataService).deleteByVideoId(videoId);
    }

    private MockMultipartFile getMultipartFile() {
        return new MockMultipartFile("video", "video.mp4", "video/mp4", new byte[]{1});
    }
}
