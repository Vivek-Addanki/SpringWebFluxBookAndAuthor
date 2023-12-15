package com.example.WebFlux.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressDTO {
    @NotBlank
    private String houseNo;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
}
