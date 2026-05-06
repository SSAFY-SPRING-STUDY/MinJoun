package org.example.springstudy.hello.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hi() {
        return "hello";
    }
}
