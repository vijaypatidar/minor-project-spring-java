package com.svceindore.minor.rest;

import com.svceindore.minor.model.BookDetail;
import com.svceindore.minor.repositories.BookDetailRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.svceindore.minor.utils.Constants.ROLE_ADMIN;

@RestController
@RequestMapping("/api/book")
public class BookDetailController {
    private final BookDetailRepository bookDetailRepository;

    public BookDetailController(BookDetailRepository bookDetailRepository) {
        this.bookDetailRepository = bookDetailRepository;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody List<BookDetail> getBooks() {
        return bookDetailRepository.findAll();
    }


    @GetMapping(value = "/id/{id}",produces = {"application/json"})
    public @ResponseBody
    ResponseEntity<BookDetail> getBook(@PathVariable String id) {
        return ResponseEntity.accepted().body(bookDetailRepository.findById(id).get());
    }

    @GetMapping("/{query}")
    public @ResponseBody
    ResponseEntity<List<BookDetail>> getSearchBook(@PathVariable String query) {
        List<BookDetail> bookDetails = bookDetailRepository.findAll();
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
        bookDetailRepository.insert(book);
        return ResponseEntity.ok().build();
    }

    @Secured(ROLE_ADMIN)
    @PutMapping()
    public void updateBook(@RequestBody BookDetail book) {
        bookDetailRepository.save(book);
    }

    @Secured(ROLE_ADMIN)
    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<String>
    deleteBook(@PathVariable String id) {
        bookDetailRepository.deleteById(id);
        return ResponseEntity.status(403).build();
    }

}
