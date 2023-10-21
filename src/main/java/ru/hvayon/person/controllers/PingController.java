package ru.hvayon.person.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/")
public class PingController {
    @GetMapping("/ping")
    public String getPersonById() {
        return "pong";
    }
}
