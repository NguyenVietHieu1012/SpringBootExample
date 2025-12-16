package com.example.example.repository;

import com.example.example.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {

    //---------------------------------Query methods------------------------------------------------

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


    //-----------------------------------------JPQL--------------------------------------------------

    // Lấy tất cả student
    @Query("SELECT s FROM Student s")
    List<Student> findAllStudents();

    // Tìm theo name
    @Query("SELECT s FROM Student s WHERE s.name = :name")
    List<Student> findByNameJPQL(@Param("name") String name);

    // age > ?
    @Query("SELECT s FROM Student s WHERE s.age > :age")
    List<Student> findOlderThan(@Param("age") int age);

    // LIKE
    @Query("SELECT s FROM Student s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Student> searchByName(@Param("keyword") String keyword);

    //--------------------------------------Native Query-----------------------------------------

    // Lấy tất cả
    @Query(value = "SELECT * FROM student", nativeQuery = true)
    List<Student> findAllNative();

    // tìm theo name
    @Query(value = "SELECT * FROM student WHERE name = :name", nativeQuery = true)
    List<Student> findByNameNative(@Param("name") String name);

    // age >= ?
    @Query(value = "SELECT * FROM student WHERE age >= :age", nativeQuery = true)
    List<Student> findAgeGreaterOrEqual(@Param("age") int age);

    // LIMIT
    @Query(value = "SELECT * FROM student ORDER BY age DESC LIMIT 3", nativeQuery = true)
    List<Student> findTop3Oldest();
}
