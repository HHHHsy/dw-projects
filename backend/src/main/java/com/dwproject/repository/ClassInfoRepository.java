package com.dwproject.repository;

import com.dwproject.entity.ClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassInfoRepository extends JpaRepository<ClassInfo, Long> {
    List<ClassInfo> findByTeacherId(Long teacherId);
}
