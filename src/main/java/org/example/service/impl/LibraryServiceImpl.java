package org.example.service.impl;

import org.example.entity.Book;
import org.example.entity.Borrower;
import org.example.repository.BookRepository;
import org.example.repository.BorrowerRepository;
import org.example.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {

    public static final Logger log = LoggerFactory.getLogger(LibraryServiceImpl.class);
    @Autowired
    BookRepository bookRepository;

    @Autowired
    BorrowerRepository borrowerRepository;
    @Override
    public Borrower saveBorrower(Borrower borrower) {
        if(borrower!=null){
            return  borrowerRepository.save(borrower);
        }else{
            log.debug("borrower is null");
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Borrower object is empty");

        }
    }

    @Override
    public Book saveBook(Book book) {
        if(book!=null){
            return bookRepository.save(book);

        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Book object is empty");

        }
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public ResponseEntity<String> borrowBook(Long borrowerId, Long bookId) {


        var borrower = Optional.ofNullable(borrowerRepository.findById(borrowerId));
        var book =  Optional.ofNullable(bookRepository.findById(bookId));

        if (borrower.isEmpty()) return ResponseEntity.badRequest().body("Borrower not found");
        if (book.isEmpty()) return ResponseEntity.badRequest().body("Book not found");
        if (book.get().get().isBorrowed()) return ResponseEntity.badRequest().body("Book already borrowed");

        Book b = book.get().get();
        b.setBorrowed(true);
        bookRepository.save(b);
        log.debug("Book saved successfully");
        return ResponseEntity.ok("Book borrowed successfully.");
    }

    @Override
    public ResponseEntity<String> returnBook(Long borrowerId, Long bookId) {
        var borrower = Optional.ofNullable(borrowerRepository.findById(borrowerId));
        var book =  Optional.ofNullable(bookRepository.findById(bookId));

        if (borrower.isEmpty()) return ResponseEntity.badRequest().body("Borrower not found");
        if (book.isEmpty()) return ResponseEntity.badRequest().body("Book not found");

        Optional<Book> b = book.get();
        if (!b.get().isBorrowed()) return ResponseEntity.badRequest().body("Book was not borrowed");

        b.get().setBorrowed(false);
        bookRepository.save(b.get());
        log.debug("Book saved successfully");
        return ResponseEntity.ok("Book returned successfully.");
    }
}
