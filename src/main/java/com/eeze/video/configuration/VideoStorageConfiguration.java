package com.eeze.video.configuration;

import com.eeze.video.service.storage.LocalVideoStorageService;
import com.eeze.video.service.storage.VideoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "file.storage", havingValue = "local", matchIfMissing = true)
public class VideoStorageConfiguration {

    @Bean
    public VideoStorageService videoStorageService(@Value("${video.storage.local.folder}") String saveDirectoryPath) {

        return new LocalVideoStorageService(saveDirectoryPath);
    }
}
