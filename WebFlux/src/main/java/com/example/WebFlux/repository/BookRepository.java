package com.example.WebFlux.repository;

import com.example.WebFlux.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;


public interface BookRepository extends ReactiveMongoRepository<Book, String> {
    @Query("{'genre' : ?0}")
    Flux<Book> findByGenre(String genre);
    @Query("{'genre' : ?0, 'copiesAvailable' : {$gte : ?1} }")
    Flux<Book> findByGenreAndCopiesAvailableGreaterThan(String genre, int copiesAvailable);
    @Query("{'authorId' : ?0}")
    Flux<Book> findByAuthorName(List<String> authorId);
}
