package com.trofimets.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private LocalDate dateOfPublication;
    private LocalDate dateOfIssue;
    @Enumerated(EnumType.STRING)
    private Status status;
    @JoinColumn(name = "user_id")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private void addUser(User user) {
        this.setUser(user);
        user.add(this);
    }
}
