package com.example.h2flywaydemo;

import com.example.h2flywaydemo.services.JobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@EnableConfigurationProperties
@SpringBootTest(classes=H2FlywayDemoApplication.class)
class H2FlywayDemoApplicationTests {

    @Autowired
    JobService jobService;

    @Test
    void contextLoads() {
    }

    @Test
    void createNewJob() {
        for (int i = 0; i < 10; i++) {
            jobService.newJob("test run", "test " + i);
        }
    }
}
