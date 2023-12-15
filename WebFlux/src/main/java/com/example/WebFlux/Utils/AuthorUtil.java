package com.example.WebFlux.Utils;

import com.example.WebFlux.dto.AuthorDTO;
import com.example.WebFlux.model.Author;
import org.springframework.beans.BeanUtils;

public class AuthorUtil {
    public static AuthorDTO entityToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        BeanUtils.copyProperties(author,authorDTO);
        return authorDTO;
    }
    public static Author DTOtoEntity(AuthorDTO dto) {
        Author author = new Author();
        BeanUtils.copyProperties(dto, author);
        return author;
    }
}