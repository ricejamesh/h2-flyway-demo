package com.example.h2flywaydemo.services;

import com.example.h2flywaydemo.entities.Job;
import com.example.h2flywaydemo.repositories.JobRepository;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final UserService userService;

    public JobService(JobRepository jobRepository, UserService userService) {
        this.jobRepository = jobRepository;
        this.userService = userService;
    }

    public void newJob(@NotNull final String name, String description, String user) {
        Job job = new Job();
        job.setName(name);

        if (!Strings.isNullOrEmpty(description))
            job.setDescription(description);

        if (!Strings.isNullOrEmpty(user))
            job.setCreatedBy(user);

        jobRepository.save(job);
    }

    public void newJob(@NotNull final String name, String description) {
        String user = userService.user(); // <-- Populate the user name via a service.
        newJob(name, description, user);
    }
}
