package com.dwproject.controller;

import com.dwproject.entity.Quiz;
import com.dwproject.entity.QuizResult;
import com.dwproject.repository.QuizRepository;
import com.dwproject.repository.QuizResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/quizzes")
@CrossOrigin(origins = "*")
public class QuizController {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizResultRepository quizResultRepository;

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        return quizRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Quiz createQuiz(@RequestBody Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz quizDetails) {
        return quizRepository.findById(id)
                .map(quiz -> {
                    quiz.setTitle(quizDetails.getTitle());
                    quiz.setQuestionCount(quizDetails.getQuestionCount());
                    quiz.setFullScore(quizDetails.getFullScore());
                    quiz.setTimeLimit(quizDetails.getTimeLimit());
                    return ResponseEntity.ok(quizRepository.save(quiz));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteQuiz(@PathVariable Long id) {
        return quizRepository.findById(id)
                .map(quiz -> {
                    quizRepository.delete(quiz);
                    Map<String, Boolean> response = new HashMap<>();
                    response.put("deleted", true);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/results")
    public List<QuizResult> getAllQuizResults() {
        return quizResultRepository.findAll();
    }

    @GetMapping("/results/student/{studentId}")
    public List<QuizResult> getQuizResultsByStudentId(@PathVariable Long studentId) {
        return quizResultRepository.findByStudentId(studentId);
    }

    @PostMapping("/results")
    public QuizResult submitQuizResult(@RequestBody QuizResult quizResult) {
        return quizResultRepository.save(quizResult);
    }
}
