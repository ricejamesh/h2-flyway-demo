package com.example.h2flywaydemo.repositories;

import com.example.h2flywaydemo.H2FlywayDemoApplication;
import com.example.h2flywaydemo.entities.Job;
import com.example.h2flywaydemo.services.JobService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


@EnableConfigurationProperties
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes= H2FlywayDemoApplication.class)
class JobRepositoryTest {

    @Autowired
    JobService jobService;

    @Autowired
    JobRepository jobRepository;

    @BeforeAll
    void addJobs() {
        for (int i = 0; i < 10; i++) {
            jobService.newJob("test run", "test " + i);
        }
    }

    @Test
    void getLastByCount() {

        List<Job> jobs = jobRepository.getLastByCount();

        jobs.forEach(job -> {
            System.out.println(job.toString());
        });
    }

    @Test
    void getLastByCountNative() {

        List<Job> jobs = jobRepository.getLastByCountNative(3);

        jobs.forEach(job -> {
            System.out.println(job.toString());
        });
    }

    @Test
    void getLastByFindAll() {

        Pageable lastThree = PageRequest.of(0, 3, Sort.by("CreatedOn").descending());
        Page<Job> jobs = jobRepository.findAll(lastThree);

        jobs.forEach(job -> {
            System.out.println(job.toString());
        });

    }

    @Test
    void getLastByPageable() {

        Page<Job> jobs = jobRepository.findLastByCountPageable(4);

        jobs.forEach(job -> {
            System.out.println(job.toString());
        });

    }

    @Test
    void getByPage() {

        Pageable twoEach = PageRequest.of(0, 2, Sort.by("CreatedOn").descending().and(Sort.by("Id").descending()));

        Page<Job> jobs = jobRepository.findAll(twoEach);

        while (jobs.hasContent()) {
            jobs.forEach(job -> {
                System.out.println(job.toString());
            });

            if (jobs.hasNext()) {
                jobs = jobRepository.findAll(jobs.nextPageable());
            } else
                break;
        }

    }

}