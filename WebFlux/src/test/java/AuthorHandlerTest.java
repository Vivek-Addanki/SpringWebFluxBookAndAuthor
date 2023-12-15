import com.example.WebFlux.Handler.AuthorHandler;
import com.example.WebFlux.Utils.AuthorUtil;
import com.example.WebFlux.dto.AuthorDTO;
import com.example.WebFlux.model.Author;
import com.example.WebFlux.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
@SpringBootTest(classes = {AuthorHandler.class, AuthorRepository.class})


public class AuthorHandlerTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorHandler authorHandler;

    @Test
    public void testGetAuthorsByName() {
        AuthorDTO author1 = new AuthorDTO();
        author1.setName("Vivek");

        AuthorDTO author2 = new AuthorDTO();
        author2.setName("Addanki");

        List<AuthorDTO> expectedAuthors = Arrays.asList(author1, author2);
        Mockito.when(authorRepository.findByNameRegex("Addanki").thenReturn(Flux.fromIterable(expectedAuthors)));

        ServerRequest request = Mockito.mock(ServerRequest.class);

        Flux<AuthorDTO> actualAuthorsFlux = authorHandler.getAuthorsByNameRegex((request));

        StepVerifier.create(actualAuthorsFlux)
                .expectNextSequence(expectedAuthors)
                .verifyComplete();
    }
}
