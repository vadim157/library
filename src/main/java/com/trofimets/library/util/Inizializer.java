package com.trofimets.library.util;

import com.trofimets.library.entity.Book;
import com.trofimets.library.entity.Status;
import com.trofimets.library.entity.User;
import com.trofimets.library.repository.BookRepository;
import com.trofimets.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class Inizializer implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        Book book1 = Book.builder()
                .author("Сергей Есенин")
                .title("Исповедь Хулигана")
                .dateOfPublication(LocalDate.of(2018, 12, 30))
                .status(Status.INSTOCK)
                .build();

        Book book2 = Book.builder()
                .author("Сергей Есенин")
                .title("Анна Снегина")
                .dateOfPublication(LocalDate.of(2010, 12, 30))
                .status(Status.INSTOCK)
                .build();

        Book book3 = Book.builder()
                .author("Александр Пушкин")
                .title("Евгений Онегин")
                .dateOfPublication(LocalDate.of(2018, 12, 30))
                .status(Status.INSTOCK)
                .build();

        Book book4 = Book.builder()
                .author("Федор Достоевский")
                .title("Братья Карамазовы")
                .dateOfPublication(LocalDate.of(2021, 12, 30))
                .status(Status.INSTOCK)
                .build();

        User user1 = User.builder()
                .email("trofimets@inbox.ru")
                .lastname("Trofimes")
                .firstname("Vadim").build();

        User user2 = User.builder()
                .email("ivanov@inbox.ru")
                .lastname("Ivanov")
                .firstname("Ivan").build();

        User user3 = User.builder()
                .email("petrov@mail.ru")
                .lastname("Petrov")
                .firstname("Petr").build();

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        bookRepository.save(book4);


    }
}
