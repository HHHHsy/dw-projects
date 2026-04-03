package com.dwproject.repository;

import com.dwproject.entity.QuizResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizResultRepository extends JpaRepository<QuizResult, Long> {
    List<QuizResult> findByStudentId(Long studentId);
    List<QuizResult> findByQuizId(Long quizId);
}
