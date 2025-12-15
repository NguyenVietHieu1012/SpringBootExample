package com.example.example.service;
import org.springframework.stereotype.Component;

@Component
// Spring sẽ tự tạo Bean HelloService
public class HelloService {
    public String sayHello() {
        return "Hello from spring Bean!!";
    }
}
