package com.svceindore.minor.rest;

import com.svceindore.minor.model.BookDetail;
import com.svceindore.minor.repositories.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.svceindore.minor.utils.Constants.ROLE_ADMIN;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping()
    public List<BookDetail> getBooks() {
        return bookRepository.findAll();
    }


    @GetMapping("/id/{id}")
    public @ResponseBody
    ResponseEntity<BookDetail> getBook(@PathVariable String id) {
        return ResponseEntity.accepted().body(bookRepository.findById(id).get());
    }

    @GetMapping("/{query}")
    public @ResponseBody
    ResponseEntity<List<BookDetail>> getSearchBook(@PathVariable String query) {
        List<BookDetail> bookDetails = bookRepository.findAll();
        String finalQuery = query.toLowerCase();
        List<BookDetail> collect = bookDetails.parallelStream().filter(
                bookDetail ->
                        bookDetail.getTitle().toLowerCase().contains(finalQuery) || bookDetail.getAuthors().toLowerCase().contains(finalQuery)
        ).collect(Collectors.toList());
        return ResponseEntity.accepted().body(collect);
    }

    @Secured(ROLE_ADMIN)
    @PostMapping()
    public ResponseEntity<String> addBook(@RequestBody BookDetail book) {
        System.out.println(book.toString());
        bookRepository.insert(book);
        return ResponseEntity.ok().build();
    }

    @Secured(ROLE_ADMIN)
    @PutMapping()
    public void updateBook(@RequestBody BookDetail book) {
        bookRepository.save(book);
    }

    @Secured(ROLE_ADMIN)
    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<String>
    deleteBook(@PathVariable String id) {
        bookRepository.deleteById(id);
        return ResponseEntity.status(403).build();
    }

    private void counter(String id,boolean increment){
        Optional<BookDetail> byId = bookRepository.findById(id);
        if (byId.isPresent()){
            BookDetail bookDetail = byId.get();
            bookDetail.setQuantity(bookDetail.getQuantity()+(increment?1:-1));
            bookRepository.save(bookDetail);
        }
    }
}
