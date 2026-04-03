package com.dwproject.controller;

import com.dwproject.entity.ClassInfo;
import com.dwproject.repository.ClassInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "*")
public class ClassInfoController {

    @Autowired
    private ClassInfoRepository classInfoRepository;

    @GetMapping
    public List<ClassInfo> getAllClasses() {
        return classInfoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassInfo> getClassById(@PathVariable Long id) {
        return classInfoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/teacher/{teacherId}")
    public List<ClassInfo> getClassesByTeacherId(@PathVariable Long teacherId) {
        return classInfoRepository.findByTeacherId(teacherId);
    }

    @PostMapping
    public ClassInfo createClass(@RequestBody ClassInfo classInfo) {
        return classInfoRepository.save(classInfo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassInfo> updateClass(@PathVariable Long id, @RequestBody ClassInfo classInfoDetails) {
        return classInfoRepository.findById(id)
                .map(classInfo -> {
                    classInfo.setName(classInfoDetails.getName());
                    classInfo.setStudentCount(classInfoDetails.getStudentCount());
                    return ResponseEntity.ok(classInfoRepository.save(classInfo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClass(@PathVariable Long id) {
        return classInfoRepository.findById(id)
                .map(classInfo -> {
                    classInfoRepository.delete(classInfo);
                    Map<String, Boolean> response = new HashMap<>();
                    response.put("deleted", true);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
