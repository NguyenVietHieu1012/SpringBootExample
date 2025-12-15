package com.example.example.repository;

import com.example.example.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<Student, Long> {

}
