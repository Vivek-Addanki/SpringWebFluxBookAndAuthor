package com.example.WebFlux.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "Author")
@Data
public class Author {
    @Setter
    @Id @NotNull
    private String id;
    @Setter @NotBlank
    private String name;
    @Setter
    @NotBlank
    private Address address;
}