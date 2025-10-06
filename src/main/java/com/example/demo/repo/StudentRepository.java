package com.example.demo.repo;

import com.example.demo.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
  boolean existsByEmail(String email);
}
