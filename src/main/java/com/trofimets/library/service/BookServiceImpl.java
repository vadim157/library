package com.trofimets.library.service;

import com.trofimets.library.entity.Book;
import com.trofimets.library.entity.Status;
import com.trofimets.library.entity.User;
import com.trofimets.library.exception_handling.NoSuchBookException;
import com.trofimets.library.exception_handling.NoSuchUserException;
import com.trofimets.library.repository.BookRepository;
import com.trofimets.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final int REFUND_PERIOD = 7;
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book take(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId)
                .filter(el -> el.getStatus().equals(Status.INSTOCK))
                .orElseThrow(() -> new NoSuchBookException("There is no book with ID " + bookId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchUserException("There is no user with ID " + userId));
        user.add(book);
        userRepository.save(user);
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book back(Long bookId, Long userId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NoSuchBookException("There is no book with ID " + bookId + " in Database"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchUserException("There is no user with ID " + userId + " in Database"));
        user.getBooks().remove(book);
        book.setStatus(Status.INSTOCK);
        book.setUser(null);
        book.setDateOfIssue(null);
        bookRepository.save(book);
        return book;
    }

    @Scheduled(fixedDelay = 86_000_000)
    public void checkingTheReturnDate() {
        List<Book> books = bookRepository.findAllByStatus(Status.ISSUED);
        books.stream()
                .filter(book -> Period.between(book.getDateOfIssue(), LocalDate.now()).getDays() > REFUND_PERIOD)
                .forEach(book -> notification(book.getUser().getEmail()));
    }

    public void notification(String email) {
        System.out.println("Send to " + email);
    }
}
