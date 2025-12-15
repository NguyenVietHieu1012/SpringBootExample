package com.example.example.controller;

import org.springframework.ui.Model;
import com.example.example.entity.Student;
import com.example.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
}



