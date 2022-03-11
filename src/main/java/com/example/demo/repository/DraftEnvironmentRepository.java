package com.example.demo.repository;

import com.example.demo.entity.draft.DraftEnvironment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DraftEnvironmentRepository extends JpaRepository<DraftEnvironment, Long> {
}
