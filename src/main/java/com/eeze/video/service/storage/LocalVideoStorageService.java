package com.eeze.video.service.storage;

import com.eeze.video.exception.InvalidRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class LocalVideoStorageService implements VideoStorageService {

    private final String saveDirectoryPath;

    @Override
    public String uploadVideoAndGetLink(UUID id, MultipartFile video) {
        var uniqueName = String.format("%s-%s", id, video.getOriginalFilename());
        var link = String.format("%s/%s", saveDirectoryPath, uniqueName);

        try {
            File fileToSave = new File(link);

            video.transferTo(fileToSave);
        } catch (IOException ioException) {
            log.error("Can't save video file", ioException);

            throw new InvalidRequestException("Can't save video file");
        }

        return link;
    }


    @Override
    // return as plain string
    public String downloadVideo(String link) {
        try {
            var bytes = Files.readAllBytes(Path.of(link));

            return new String(bytes);
        } catch (IOException e) {
            log.error("Can't read video file", e);

            throw new InvalidRequestException("Can't read video file");
        }
    }
}
