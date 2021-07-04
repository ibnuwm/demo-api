package com.domain.controllers;

import com.domain.models.entities.UserDetailEncrypt;
import com.domain.models.repos.UserDetailEncryptRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/userdetailencrypts")
public class UserDetailEncryptController {

    @Autowired
    private UserDetailEncryptRepo repo;

    @PostMapping
    public UserDetailEncrypt createOne(@RequestBody UserDetailEncrypt userDetailEncrypt) {
        return repo.save(userDetailEncrypt);
    }

    @GetMapping
    public Iterable<UserDetailEncrypt> findAll() {
        return repo.findAll();
    }

}
