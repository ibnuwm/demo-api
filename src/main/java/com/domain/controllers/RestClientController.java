package com.domain.controllers;

import com.domain.models.entities.RestUser;
import com.domain.services.RestClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/restuser")
public class RestClientController {

    @Autowired
    private RestClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<String> findUserStringById(@PathVariable("id") int id) {
        return clientService.getUserString(id);
    }

    @GetMapping("/object/{id}")
    public ResponseEntity<RestUser> findUserObjectById(@PathVariable("id") int id) {
        return clientService.getUserObject(id);
    }

    @GetMapping
    public ResponseEntity<?> findAllUser() {
        return clientService.getAll();
    }

    @PostMapping
    public ResponseEntity<RestUser> postUser(@RequestBody RestUser restUser) {
        return clientService.postUserObject(restUser);
    }
}
