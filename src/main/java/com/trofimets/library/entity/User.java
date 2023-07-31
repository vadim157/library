package com.trofimets.library.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Builder
@ToString(exclude = "books")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    @Builder.Default
    @JsonManagedReference
    @OneToMany(mappedBy = "user")
    private List<Book> books = new ArrayList<>();

    public void add(Book book) {
        book.setUser(this);
        book.setStatus(Status.ISSUED);
        book.setDateOfIssue(LocalDate.now());
        books.add(book);
    }

}
