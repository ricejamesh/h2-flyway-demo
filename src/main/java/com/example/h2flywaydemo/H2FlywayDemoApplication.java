package com.example.h2flywaydemo;

import com.example.h2flywaydemo.services.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class H2FlywayDemoApplication  implements CommandLineRunner, ExitCodeGenerator {

    private int exitCode = 0;

    JobService jobService;

    public H2FlywayDemoApplication(JobService jobService) {
        this.jobService = jobService;
    }

    public static void main(String[] args) {
        System.exit(
                SpringApplication.exit(
                        SpringApplication.run(
                                H2FlywayDemoApplication.class, args)));
    }

    @Override
    public void run(String... args) {
        log.info("Do something interesting...");
        try {
            jobService.newJob("process", "Ran the jar.");
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
            exitCode = 1;
        }
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }
}
