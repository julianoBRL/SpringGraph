package io.github.julianobrl.SpringGraph.application.service;

import io.github.julianobrl.SpringGraph.application.port.BookServicePort;
import io.github.julianobrl.SpringGraph.domain.Book;
import io.github.julianobrl.SpringGraph.domain.BookPage;
import io.github.julianobrl.SpringGraph.infrastructure.repository.BookRepository;
import io.github.julianobrl.SpringGraph.infrastructure.subscription.BookSubscriptionSink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.util.List;
import java.util.UUID;

// Adapter: Service
@Service
public class BookService implements BookServicePort {

    private final BookRepository repository;
    private final BookSubscriptionSink subscriptionSink;

    public BookService(BookRepository repository, BookSubscriptionSink subscriptionSink) {
        this.repository = repository;
        this.subscriptionSink = subscriptionSink;
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public BookPage getAllBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage = repository.findAll(pageable);

        List<Book> books = bookPage.getContent();
        return new BookPage(
                books,
                bookPage.getTotalElements(),
                bookPage.getTotalPages(),
                bookPage.getNumber(),
                size
        );
    }

    @Override
    public Book createBook(String title, String author) {
        Book book = new Book(title, author);
        Book savedBook = repository.save(book);
        subscriptionSink.publish(savedBook);
        return savedBook;
    }

    @Override
    public Flux<Book> subscribeToBooks() {
        return subscriptionSink.getSink();
    }

    @Override
    public Book getById(UUID id) {
        return repository.getReferenceById(id);
    }
}
