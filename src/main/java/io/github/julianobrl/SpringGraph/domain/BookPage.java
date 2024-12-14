package io.github.julianobrl.SpringGraph.domain;

import java.util.List;

public record BookPage(
        List<Book> books,
        long totalElements,
        int totalPages,
        int currentPage,
        int pageSize
) {}
