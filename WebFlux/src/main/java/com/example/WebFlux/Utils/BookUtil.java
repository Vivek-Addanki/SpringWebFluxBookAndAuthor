package com.example.WebFlux.Utils;

import com.example.WebFlux.dto.AuthorDTO;
import com.example.WebFlux.dto.BookDTO;
import com.example.WebFlux.model.Author;
import com.example.WebFlux.model.Book;
import org.springframework.beans.BeanUtils;

public class BookUtil {
    public static BookDTO entityToDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        BeanUtils.copyProperties(book,bookDTO);
        return bookDTO;
    }
    public static Book DTOtoEntity(BookDTO dto) {
        Book book = new Book();
        BeanUtils.copyProperties(dto, book);
        return book;
    }
}