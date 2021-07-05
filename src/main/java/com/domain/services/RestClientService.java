package com.domain.services;

import java.util.Arrays;

import com.domain.models.entities.RestUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClientService {

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<String> getUserString(int id) {
        ResponseEntity<String> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users/" + id,
                String.class);
        return response;
    }

    public ResponseEntity<RestUser> getUserObject(int id) {
        RestUser user = restTemplate.getForObject("https://jsonplaceholder.typicode.com/users/" + id, RestUser.class);
        // proses get user
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<RestUser[]> getAll() {
        ResponseEntity<RestUser[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users",
                RestUser[].class);
        return response;
    }

    public ResponseEntity<RestUser> postUserObject(RestUser restUser) {
        // cara 1
        RestUser user = restTemplate.postForObject("https://jsonplaceholder.typicode.com/users", restUser,
                RestUser.class);

        // cara 2
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<RestUser> entity = new HttpEntity<RestUser>(restUser, headers);
        ResponseEntity<RestUser> user2 = restTemplate.exchange("https://jsonplaceholder.typicode.com/users",
                HttpMethod.POST, entity, RestUser.class);

        // return user2;
        return ResponseEntity.ok(user);
    }
}
