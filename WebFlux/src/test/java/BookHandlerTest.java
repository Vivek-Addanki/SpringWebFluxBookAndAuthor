//import com.example.WebFlux.model.Book;
//import com.example.WebFlux.repository.BookRepository;
//import com.example.WebFlux.service.BookService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class BookServiceTest {
//
//    @Mock
//    private BookRepository bookRepository;
//
//    @InjectMocks
//    private BookService bookService;
//
//    @Test
//    public void testGetAllBooks() {
//        Book book1 = new Book();
//        book1.setCopiesAvailable(5);
//        book1.setAuthorId("authorId1");
//        book1.setGenre("Science Fiction");
//
//        Book book2 = new Book();
//        book2.setCopiesAvailable(3);
//        book2.setAuthorId("authorId2");
//        book2.setGenre("Mystery");
//
//        List<Book> expectedBooks = Arrays.asList(book1, book2);
//
//        Mockito.when(bookRepository.findAll()).thenReturn(expectedBooks);
//
//        List<Book> actualBooks = bookService.getAllBooks();
//
//        assertEquals(expectedBooks, actualBooks);
//    }
//
//}
import com.example.WebFlux.Handler.AuthorHandler;
import com.example.WebFlux.Handler.BookHandler;
import com.example.WebFlux.dto.BookDTO;
import com.example.WebFlux.model.Book;
import com.example.WebFlux.repository.AuthorRepository;
import com.example.WebFlux.repository.BookRepository;
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

@SpringBootTest(classes = {BookHandler.class, BookRepository.class})
public class BookHandlerTest {
    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookHandler bookHandler;
    @Test
    public void testGetAllBooks() {
        BookDTO book1 = new BookDTO();
        book1.setCopiesAvailable(5);
        book1.setAuthorId("authorId1");
        book1.setGenre("Science Fiction");

        BookDTO book2 = new BookDTO();
        book2.setCopiesAvailable(3);
        book2.setAuthorId("authorId2");
        book2.setGenre("Mystery");

        List<BookDTO> expectedBooks = Arrays.asList(book1, book2);

        Mockito.when(bookRepository.findAll()).thenReturn(Flux.fromIterable(expectedBooks));
        ServerRequest request = Mockito.mock(ServerRequest.class);

        Flux<BookDTO> actualBooksFlux = bookHandler.getAllBooks(request);

        StepVerifier.create(actualBooksFlux)
                .expectNextSequence(expectedBooks)
                .verifyComplete();
    }
}

