package com.example.WebFlux.Handler;

import com.example.WebFlux.Utils.BookUtil;
import com.example.WebFlux.dto.BookDTO;
import com.example.WebFlux.model.Book;
import com.example.WebFlux.repository.AuthorRepository;
import com.example.WebFlux.repository.BookRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookHandler {

    @Getter
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public Mono<ServerResponse> getAllBooks(ServerRequest request) {
        return ServerResponse.ok().body(bookRepository.findAll(), BookDTO.class);
    }
    public Mono<ServerResponse> getBooksByGenre(ServerRequest request) {
        String genre = request.queryParam("genre").orElse("");
        return ServerResponse.ok().body(bookRepository.findByGenre(genre), BookDTO.class);
    }
    public Mono<ServerResponse> getBooksByGenreAndCopiesAvailable(ServerRequest request) {
        String genre = request.queryParam("genre").orElse("");
        int copiesAvailable = Integer.parseInt(request.pathVariable("copiesAvailable"));
        return ServerResponse.ok().body(bookRepository.findByGenreAndCopiesAvailableGreaterThan(genre, copiesAvailable), BookDTO.class);
    }
    public Mono<ServerResponse> saveBook(ServerRequest request) {
        Mono<BookDTO> bookMono = request.bodyToMono(BookDTO.class);
        return bookMono.flatMap(book -> {
            Book bookEntity = BookUtil.DTOtoEntity(book);
            return ServerResponse.ok().body(bookRepository.save(bookEntity), BookDTO.class);
        });
    }
    public Mono<ServerResponse> getBooksByAuthorNames(ServerRequest request) {
        List<String> authorNames = request.queryParams().get("authorNames");

        Flux<BookDTO> books = Flux.fromIterable(authorNames)
                .flatMap(authorName -> authorRepository.findByNameRegex(authorName))
                .map(author -> author.getId())
                .collectList()
                .flatMapMany(authorIdList -> bookRepository.findByAuthorName(authorIdList))
                .map(BookUtil::entityToDTO);

        return ServerResponse.ok().body(books, BookDTO.class);
    }
}

