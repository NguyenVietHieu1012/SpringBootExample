package com.example.example.specification;
import org.springframework.data.jpa.domain.Specification;
import com.example.example.entity.Student;

public class StudentSpecification {
    public static Specification<Student> hasName(String name) {
        return (root, query, cb) -> name == null ? null:
                cb.like(cb.lower(root.get("name")),
                        "%" + name.toLowerCase() + "%");
    }

    public static Specification<Student> ageGreaterThan(Integer age){
        return (root, query, cb) -> age == null ? null:
                cb.greaterThan(root.get("age"), age);
    }
}
