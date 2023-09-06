package com.project.students.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.students.model.Student;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<Student,Long> {
    
    Student findByStudentId(String StudentId);
    Student deleteByStudentId(String StudentId);
    Long removeByStudentId(String StudentId);
}
