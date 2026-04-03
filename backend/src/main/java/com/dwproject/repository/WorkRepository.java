package com.dwproject.repository;

import com.dwproject.entity.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findByStudentId(Long studentId);
    List<Work> findByStudentClassInfoId(Long classId);
    List<Work> findByIsExcellentTrue();
    List<Work> findByScoreIsNull();
}
