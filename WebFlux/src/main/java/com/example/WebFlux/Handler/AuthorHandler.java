package com.example.WebFlux.Handler;

import com.example.WebFlux.Utils.AuthorUtil;
import com.example.WebFlux.dto.AuthorDTO;
import com.example.WebFlux.model.Author;
import com.example.WebFlux.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class AuthorHandler {

    private final AuthorRepository authorRepository;

    public AuthorHandler(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Mono<ServerResponse> saveAuthor(ServerRequest request) {
        Mono<AuthorDTO> authorMono = request.bodyToMono(AuthorDTO.class);

        return authorMono.flatMap(authorDTO -> {
            Author authorEntity = AuthorUtil.DTOtoEntity(authorDTO);
            return ServerResponse.ok().body(authorRepository.save(authorEntity), AuthorDTO.class);
        });
    }

    public Mono<ServerResponse> getAuthorsByNameRegex(ServerRequest request) {
        String nameRegex = request.queryParam("nameRegex").orElse("");
        return ServerResponse.ok().body(authorRepository.findByNameRegex(nameRegex), AuthorDTO.class);
    }
}
