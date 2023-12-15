package com.example.WebFlux.Router;

import com.example.WebFlux.Handler.BookHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class BookRouter {

    @Bean
    public RouterFunction<ServerResponse> bookRoutes(BookHandler bookHandler) {
        return RouterFunctions.route()
                .GET("/books", bookHandler::getAllBooks)
                .GET("/books/byGenre", bookHandler::getBooksByGenre)
                .GET("/books/byGenreAndCopiesAvailable/{copiesAvailable}", bookHandler::getBooksByGenreAndCopiesAvailable)
                .POST("/books", bookHandler::saveBook)
                .GET("/books/byAuthors", bookHandler::getBooksByAuthorNames)
                .build();
    }
}