package com.example.WebFlux.dto;


import com.example.WebFlux.model.Book;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Setter;

@Data
public class BookDTO {
    private int copiesAvailable;

    private String authorId;

    private String genre;
}
