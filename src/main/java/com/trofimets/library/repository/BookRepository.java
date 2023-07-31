package com.trofimets.library.repository;

import com.trofimets.library.entity.Book;
import com.trofimets.library.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("FROM Book b WHERE b.status = :status")
    List<Book> findAllByStatus(Status status);
}
