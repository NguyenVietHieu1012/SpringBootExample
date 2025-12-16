package com.example.example.repository;

import com.example.example.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {

    //----------Query methods------------

    // Tìm theo exact name
    List<Student> findByName(String name);

    // Tìm những student có age > age
    List<Student> findByAgeGreaterThan(int age);

    // Tìm theo chứa keyword không phân biệt hoa thường
    List<Student> findByNameContainingIgnoreCase(String keyword);

    // Lấy top 3 theo tuổi giảm dần (3 student lớn tuổi nhất)
    List<Student> findTop3ByOrderByAgeDesc();

    // Kiểm tra tồn tại theo name
    boolean existsByName(String name);

    // Đếm số student có age >= given
    long countByAgeGreaterThanEqual(int age);

    // Tìm bằng danh sách id
    List<Student> findByAgeBetween(int min, int max);

    // Kết hợp AND
    List<Student> findByNameAndAge(String name, int age);

    // Kết hợp OR
    List<Student> findByNameOrAge(String name, int age);

    // Sắp xếp theo name tăng dần, với điều kiện age
    List<Student> findByAgeOrderByNameAsc(int age);

    // Bắt đầu bằng
    List<Student> findByNameStartingWith(String prefix);

    // <=
    List<Student> findByAgeLessThanEqual(int age);

}
