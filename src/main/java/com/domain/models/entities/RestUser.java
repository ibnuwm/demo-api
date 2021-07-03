package com.domain.models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestUser {

    private int id;
    private String name;
    private String username;
    private String email;
    private RestAddress address;
    private String phone;
    private String website;
    private RestCompany company;
}
