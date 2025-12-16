package com.example.example.controller;

import com.example.example.specification.StudentSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.example.entity.Student;
import com.example.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    // Hiển thị danh sách
    @GetMapping
    public String list(Model model) {
        model.addAttribute("students",
                studentRepository.findAll());
        return "student-list";
    }

    // Form thêm mới
    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // Lưu (thêm / sửa)
    @PostMapping("/save")
    public String save(Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/ping")
    @ResponseBody
    public String ping() {
        return "OK";
    }

    //--------------------- Demo Query Methods -----------------------

    //find by name
    @GetMapping("/search/name/{name}")
    @ResponseBody
    public List<Student> findByName(@PathVariable String name){
        return studentRepository.findByName(name);
    }

    //findByAgeGreaterThan
    @GetMapping("/search/age/greater/{age}")
    @ResponseBody
    public List<Student> findByAgeGreaterThan(@PathVariable int age){
        return studentRepository.findByAgeGreaterThan(age);
    }

    // findByNameContainingIgnoreCase
    @GetMapping("/search/contains/{keyword}")
    @ResponseBody
    public List<Student> findByNameContaining(@PathVariable String keyword) {
        return studentRepository.findByNameContainingIgnoreCase(keyword);
    }

    // findTop3ByOrderByAgeDesc
    @GetMapping("/search/top3/oldest")
    @ResponseBody
    public List<Student> top3Oldest() {
        return studentRepository.findTop3ByOrderByAgeDesc();
    }

    // existsByName
    @GetMapping("/search/exists/{name}")
    @ResponseBody
    public boolean existsByName(@PathVariable String name){
        return studentRepository.existsByName(name);
    }

    // countByAgeGreaterThanEqual
    @GetMapping("/search/count/age/{age}")
    @ResponseBody
    public long countByAgeGreaterOrEqual(@PathVariable int age){
        return studentRepository.countByAgeGreaterThanEqual(age);
    }

    // findByAgeBetween? min=18 & max=25
    @GetMapping("/search/between")
    @ResponseBody
    public List<Student> findByAgeBetween(@RequestParam int min, @RequestParam int max){
        return studentRepository.findByAgeBetween(min, max);
    }

    // findByAgeOrderByNameAsc
    @GetMapping("/search/age/sort")
    @ResponseBody
    public List<Student> findByAgeOrderByNameAsc(@RequestParam int age){
        return studentRepository.findByAgeOrderByNameAsc(age);
    }

    // findByNameStartingWith
    @GetMapping("/search/startwith/{prefix}")
    @ResponseBody
    public List<Student> findByNameStartingWith(@PathVariable String prefix) {
        return studentRepository.findByNameStartingWith(prefix);
    }

    // findByAgeLessThanEqual
    @GetMapping("/search/age/lessorequal/{age}")
    @ResponseBody
    public List<Student> findByAgeLessThanEqual(@PathVariable int age){
        return studentRepository.findByAgeLessThanEqual(age);
    }

    //--------------------- Demo JPQL và Native query -----------------------
    @GetMapping("/jpql/age/{age}")
    @ResponseBody
    public List<Student> jpqlAge(@PathVariable int age) {
        return studentRepository.findOlderThan(age);
    }

    @GetMapping("/native/top3")
    @ResponseBody
    public List<Student> nativeTop3() {
        return studentRepository.findTop3Oldest();
    }

    //------------------------------------Demo Pagination-----------------------------------------
    @GetMapping("/page")
    @ResponseBody
    public Page<Student> paging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    //------------------------------------Demo Specification-----------------------------------------
    @GetMapping("/search")
    @ResponseBody
    public Page<Student> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer age,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Specification<Student> spec =
                Specification.where(StudentSpecification.hasName(name))
                        .and(StudentSpecification.ageGreaterThan(age));

        Pageable pageable = PageRequest.of(page, size);

        return studentRepository.findAll(spec, pageable);
    }

    //------------------------------------HTTP Status Codes-----------------------------------------
    @PostMapping("/students")
    public ResponseEntity<Student> create(@RequestBody Student student) {
        Student saved = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

}



