package com.example.MC1.controllers;

import com.example.MC1.services.dto.MessageDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface MessagePort
{

    @GetMapping(path = "/start")
    ResponseEntity<String> start();


    @GetMapping(path = "/stop")
    ResponseEntity<String> stop();

    @PostMapping(value = "/message", consumes = MediaType.APPLICATION_JSON_VALUE)
    String saveMessage( @RequestBody MessageDTO messageDTO );
}
