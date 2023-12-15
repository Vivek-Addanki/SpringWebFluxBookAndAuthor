package com.example.WebFlux.dto;

import com.example.WebFlux.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class AuthorDTO {
    private String id;
    private String name;

    private Address address;
}