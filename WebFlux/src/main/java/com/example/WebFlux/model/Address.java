package com.example.WebFlux.model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Address {
    @NotBlank
    private String houseNo;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
}