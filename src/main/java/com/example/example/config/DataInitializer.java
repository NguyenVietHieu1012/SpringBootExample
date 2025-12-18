package com.example.example.config;

import com.example.example.entity.Student;
import com.example.example.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initStudents(StudentRepository studentRepository) {
        return args -> {
            if (studentRepository.count() == 0) {

                studentRepository.save(create("Nguyễn Văn An", 20));
                studentRepository.save(create("Trần Thị Bích", 21));
                studentRepository.save(create("Lê Minh Châu", 22));
                studentRepository.save(create("Phạm Quốc Dũng", 19));
                studentRepository.save(create("Hoàng Thị Hạnh", 20));
                studentRepository.save(create("Vũ Đức Long", 23));
                studentRepository.save(create("Đặng Thị Mai", 21));
                studentRepository.save(create("Bùi Thanh Nam", 22));
                studentRepository.save(create("Ngô Phương Oanh", 20));
                studentRepository.save(create("Đỗ Văn Phúc", 24));
                studentRepository.save(create("Lý Thị Quỳnh", 19));
                studentRepository.save(create("Cao Minh Sơn", 21));
                studentRepository.save(create("Huỳnh Thị Trang", 22));
                studentRepository.save(create("Phan Văn Tuấn", 23));
                studentRepository.save(create("Trương Thị Yến", 20));
            }
        };
    }

    private Student create(String name, int age) {
        Student student = new Student();
        student.setName(name);
        student.setAge(age);
        return student;
    }
}
