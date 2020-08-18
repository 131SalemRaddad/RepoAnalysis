package com.proj2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj2.demo.model.Job;

@Repository
public interface JobRepo extends JpaRepository<Job, String> {

}