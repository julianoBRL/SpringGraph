package io.github.julianobrl.SpringGraph.infrastructure.subscription;

import io.github.julianobrl.SpringGraph.domain.Book;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Component
public class BookSubscriptionSink {
    private final Sinks.Many<Book> sink = Sinks.many().multicast().onBackpressureBuffer();

    public void publish(Book bookRecord) {
        sink.tryEmitNext(bookRecord);
    }

    public Flux<Book> getSink() {
        return sink.asFlux();
    }
}
