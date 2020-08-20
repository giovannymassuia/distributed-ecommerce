package com.giovannymassuia.catalogservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration
@Profile("!test")
public class SchedulingConfig {
}
