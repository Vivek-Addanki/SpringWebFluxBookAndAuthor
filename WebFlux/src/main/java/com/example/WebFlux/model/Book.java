package com.example.WebFlux.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "Book")
@Data
public class Book {
    @Id @NotNull
    @Setter
    private String id;
    @Setter @Min(value = 0)
    private int copiesAvailable;
    @Setter @NotNull
    private String authorId;
    @Setter @NotBlank
    private String genre;
}
