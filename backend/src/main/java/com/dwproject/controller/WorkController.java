package com.dwproject.controller;

import com.dwproject.entity.Work;
import com.dwproject.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/works")
@CrossOrigin(origins = "*")
public class WorkController {

    @Autowired
    private WorkRepository workRepository;

    @GetMapping
    public List<Work> getAllWorks() {
        return workRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Work> getWorkById(@PathVariable Long id) {
        return workRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public List<Work> getWorksByStudentId(@PathVariable Long studentId) {
        return workRepository.findByStudentId(studentId);
    }

    @GetMapping("/class/{classId}")
    public List<Work> getWorksByClassId(@PathVariable Long classId) {
        return workRepository.findByStudentClassInfoId(classId);
    }

    @GetMapping("/excellent")
    public List<Work> getExcellentWorks() {
        return workRepository.findByIsExcellentTrue();
    }

    @GetMapping("/ungraded")
    public List<Work> getUngradedWorks() {
        return workRepository.findByScoreIsNull();
    }

    @PostMapping
    public Work createWork(@RequestBody Work work) {
        return workRepository.save(work);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Work> updateWork(@PathVariable Long id, @RequestBody Work workDetails) {
        return workRepository.findById(id)
                .map(work -> {
                    work.setTitle(workDetails.getTitle());
                    work.setDescription(workDetails.getDescription());
                    work.setFilePath(workDetails.getFilePath());
                    work.setFileName(workDetails.getFileName());
                    return ResponseEntity.ok(workRepository.save(work));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/grade")
    public ResponseEntity<Work> gradeWork(@PathVariable Long id, @RequestBody Map<String, Object> gradeData) {
        return workRepository.findById(id)
                .map(work -> {
                    work.setScore((Integer) gradeData.get("score"));
                    work.setComment((String) gradeData.get("comment"));
                    work.setIsExcellent((Boolean) gradeData.getOrDefault("isExcellent", false));
                    work.setGradedAt(LocalDateTime.now());
                    return ResponseEntity.ok(workRepository.save(work));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteWork(@PathVariable Long id) {
        return workRepository.findById(id)
                .map(work -> {
                    workRepository.delete(work);
                    Map<String, Boolean> response = new HashMap<>();
                    response.put("deleted", true);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
