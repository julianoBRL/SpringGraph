package io.github.julianobrl.SpringGraph.presentation;

import io.github.julianobrl.SpringGraph.application.port.BookServicePort;
import io.github.julianobrl.SpringGraph.domain.Book;
import io.github.julianobrl.SpringGraph.domain.BookPage;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Controller
public class BookGraphQLController {

    private final BookServicePort bookService;

    public BookGraphQLController(BookServicePort bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public Book bookById(@Argument String id) {
        return bookService.getById(UUID.fromString(id));
    }

    @QueryMapping
    public BookPage allBooks(@Argument int page, @Argument int size) {
        return bookService.getAllBooks(page,size);
    }

    @MutationMapping
    public Book createBook(@Argument Book book) {
        return bookService.createBook(book.getTitle(), book.getAuthor());
    }

    @SubscriptionMapping
    public Flux<Book> bookCreated() {
        return bookService.subscribeToBooks();
    }

    @SubscriptionMapping
    public Flux<Book> bookCreatedFilteredTitle(@Argument String title) {
        return bookService.subscribeToBooks()
                .filter(book -> book.getTitle().equalsIgnoreCase(title));
    }

}
