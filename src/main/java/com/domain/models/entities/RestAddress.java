package com.domain.models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RestAddress {

    private String street;
    private String suite;
    private String city;
    private String zipcode;

}
