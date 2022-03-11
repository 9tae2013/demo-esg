package com.example.demo.repository;

import com.example.demo.entity.draft.DraftReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DraftReportRepository extends JpaRepository<DraftReport, Long> {
}
