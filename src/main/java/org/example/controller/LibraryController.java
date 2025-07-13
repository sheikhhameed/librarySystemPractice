package org.example.controller;

import org.example.entity.Book;
import org.example.entity.Borrower;
import org.example.repository.BookRepository;
import org.example.repository.BorrowerRepository;
import org.example.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LibraryController {

    public static final Logger LOGGER = LoggerFactory.getLogger(LibraryController.class);
    @Autowired
    LibraryService libraryService;
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    public LibraryController(BookRepository bookRepository, BorrowerRepository borrowerRepository) {
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @PostMapping(value = "/api/borrowers")
    public Borrower registerBorrower(@RequestBody Borrower borrower) {
        Borrower borrowerResp = libraryService.saveBorrower(borrower);
        return borrowerResp;
    }

    @PostMapping("/api/books")
    public Book registerBook(@RequestBody Book book) {
        Book bookResp = libraryService.saveBook(book);
        return bookResp;
    }

    @GetMapping("/api/books")
    public List<Book> getAllBooks() {
        List<Book> bookList = libraryService.findAll();
        return bookList;
    }

    @PostMapping("/api/borrow/{borrowerId}/book/{bookId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {

        ResponseEntity<String> responseEntity = libraryService.borrowBook(borrowerId, bookId);
       return responseEntity;
    }

    @PostMapping("/api/return/{borrowerId}/book/{bookId}")
    public ResponseEntity<String> returnBook(@PathVariable Long borrowerId, @PathVariable Long bookId) {

        ResponseEntity<String> responseEntity =libraryService.returnBook(borrowerId, bookId);
       return responseEntity;
    }
}

