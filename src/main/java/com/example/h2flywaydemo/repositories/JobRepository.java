package com.example.h2flywaydemo.repositories;

import com.example.h2flywaydemo.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
}