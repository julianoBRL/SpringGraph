package io.github.julianobrl.SpringGraph.application.port;

import io.github.julianobrl.SpringGraph.domain.BookPage;
import reactor.core.publisher.Flux;

import io.github.julianobrl.SpringGraph.domain.Book;
import java.util.List;
import java.util.UUID;

// Port interface for the application
public interface BookServicePort {
    List<Book> getAllBooks();
    Book createBook(String title, String author);
    Flux<Book> subscribeToBooks();
    Book getById(UUID id);
    BookPage getAllBooks(int page, int size);
}
