package com.trofimets.library.service;

import com.trofimets.library.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();
    Book take(Long bookId, Long userId);
    Book back(Long bookId, Long userId);
}
