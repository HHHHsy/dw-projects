package com.dwproject.repository;

import com.dwproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByRole(String role);
    List<User> findByClassInfoId(Long classId);
    boolean existsByUsername(String username);
}
