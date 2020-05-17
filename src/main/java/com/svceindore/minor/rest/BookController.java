package com.svceindore.minor.rest;

import com.svceindore.minor.model.BookDetail;
import com.svceindore.minor.repositories.BookRepository;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping()
    public List<BookDetail> getBooks(Authentication authentication) {
        System.out.println(authentication+" ================= ");
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<BookDetail> getBook(@PathVariable String id) {
        return ResponseEntity.accepted().body(bookRepository.findById(id).get());
    }

    @PostMapping()

    public @ResponseBody
    ResponseEntity<BookDetail> addBook(@RequestBody BookDetail book) {
        System.out.println(book.toString());
        bookRepository.insert(book);
        return ResponseEntity.accepted().build();
    }

    @PutMapping()
    public void updateBook(@RequestBody BookDetail book) {
        bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<String>
    deleteBook(@PathVariable String id) {
        bookRepository.deleteById(id);
        return ResponseEntity.status(403).build();
    }

}
