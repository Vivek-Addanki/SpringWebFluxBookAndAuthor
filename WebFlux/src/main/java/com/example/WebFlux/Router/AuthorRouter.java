package com.example.WebFlux.Router;

import com.example.WebFlux.Handler.AuthorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AuthorRouter {

    @Bean
    public RouterFunction<ServerResponse> authorRoutes(AuthorHandler authorHandler) {
        return RouterFunctions.route()
                .POST("/authors", authorHandler::saveAuthor)
                .GET("/authors/byNameRegex", authorHandler::getAuthorsByNameRegex)
                .build();
    }
}
