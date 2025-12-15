package com.example.example.controller;
import com.example.example.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired //@Autowired = Spring tự động truyền HelloService vào
    private HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        return helloService.sayHello();
    }
}
