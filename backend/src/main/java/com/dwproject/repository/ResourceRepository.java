package com.dwproject.repository;

import com.dwproject.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    List<Resource> findByType(String type);
    List<Resource> findByOrderByUploadedAtDesc();
}
