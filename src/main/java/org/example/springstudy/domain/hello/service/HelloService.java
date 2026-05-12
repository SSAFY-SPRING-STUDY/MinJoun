package org.example.springstudy.domain.hello.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hi() {
        return "hello";
    }
}
