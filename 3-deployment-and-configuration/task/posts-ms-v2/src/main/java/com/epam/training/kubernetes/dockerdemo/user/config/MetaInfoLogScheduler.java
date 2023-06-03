package com.epam.training.kubernetes.dockerdemo.user.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@EnableScheduling
@PropertySource("classpath:metadata.properties")
public class MetaInfoLogScheduler {

    @Value("${app.metadata.name}")
    private String appName;

    @Value("${app.metadata.version}")
    private String appVersion;


    @Scheduled(fixedDelayString = "${app.info.log.fixedDelay.seconds}000")
    public void logMetaInfoTask() {
        log.info("Running {} with version {}", appName, appVersion);
    }
}
