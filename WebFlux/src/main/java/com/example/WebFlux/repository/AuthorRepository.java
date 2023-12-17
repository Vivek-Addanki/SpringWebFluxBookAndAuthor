package com.example.WebFlux.repository;

import com.example.WebFlux.model.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
    @Query("{name:{$regex:?0}}")
    Flux<Author> findByNameRegex(String nameRegex);
}
