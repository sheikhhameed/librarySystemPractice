package org.example.service;

import org.example.entity.Book;
import org.example.entity.Borrower;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LibraryService {
    Borrower saveBorrower(Borrower borrower);

    Book saveBook(Book book);

    List<Book> findAll();

    ResponseEntity<String> borrowBook(Long borrowerId, Long bookId);

    ResponseEntity<String> returnBook(Long borrowerId, Long bookId);
}
