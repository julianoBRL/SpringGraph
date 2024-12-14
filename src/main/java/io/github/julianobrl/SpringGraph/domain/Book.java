package io.github.julianobrl.SpringGraph.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String title;
    private String author;

    public Book() {}

    public Book(String title) {this.title = title;}

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Book(String title, String author, UUID id) {
        this.title = title;
        this.author = author;
        this.id = id;
    }

    public UUID getId() {return id;}
    public String getTitle() {return title;}
    public String getAuthor() {return author;}

    public void setAuthor(String author) {this.author = author;}
    public void setId(UUID id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}

}
