package com.trofimets.library.controller;

import com.trofimets.library.entity.Book;
import com.trofimets.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Book> books = bookService.findAll();
        return books.isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PatchMapping("/{bookId}/take/{userId}")
    public ResponseEntity<?> takeBook(@PathVariable Long bookId, @PathVariable Long userId) {
        return ResponseEntity.ok(bookService.take(bookId, userId));
    }

    @PatchMapping("/{bookId}/back/{userId}")
    public ResponseEntity<?> backBook(@PathVariable Long bookId, @PathVariable Long userId) {
        return ResponseEntity.ok(bookService.back(bookId, userId));
    }
}



