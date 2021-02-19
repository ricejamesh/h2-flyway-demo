package com.example.h2flywaydemo.repositories;

import com.example.h2flywaydemo.Constants;
import com.example.h2flywaydemo.entities.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@org.springframework.stereotype.Repository
public interface JobRepository extends JpaRepository<Job, Long> {


    List<Job> findByName(String name);

    List<Job> findAll();

    // JPQL
    @Query("select j from Job j order by j.createdOn desc")
    List<Job> getLastByCount();

    // Native SQL
    @Query(value = "select * from " + Constants.SCHEMA + ".job j order by j.created_on desc limit ?1",
    nativeQuery = true)
    List<Job> getLastByCountNative(int count);

    // JPQL with Pageable and Sorting
    default Page<Job> findLastByCountPageable(int count) {
        Pageable lastN = PageRequest.of(0, count, Sort.by("CreatedOn").descending().and(Sort.by("Description").descending()));
        return findAll(lastN);
    }

}