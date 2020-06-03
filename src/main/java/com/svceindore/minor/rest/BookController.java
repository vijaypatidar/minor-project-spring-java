package com.svceindore.minor.rest;

import com.svceindore.minor.fcm.Notification;
import com.svceindore.minor.fcm.NotificationSender;
import com.svceindore.minor.model.Book;
import com.svceindore.minor.model.BookDetail;
import com.svceindore.minor.model.Token;
import com.svceindore.minor.repositories.BookDetailRepository;
import com.svceindore.minor.repositories.BookRepository;
import com.svceindore.minor.repositories.TokenRepository;
import com.svceindore.minor.utils.Constants;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/book_manage")
public class BookController {
    private final BookRepository bookRepository;
    private final TokenRepository tokenRepository;
    private final BookDetailRepository bookDetailRepository;

    public BookController(BookRepository bookRepository, TokenRepository tokenRepository, BookDetailRepository bookDetailRepository) {
        this.bookRepository = bookRepository;
        this.tokenRepository = tokenRepository;
        this.bookDetailRepository = bookDetailRepository;
    }

    @PostMapping(value = {"/issue/{bookId}"})
    public @ResponseBody
    ResponseEntity<String> issueBook(@PathVariable String bookId) {

        SecurityContext context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        Optional<Book> optional = bookRepository.findById(bookId);
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Book book = optional.get();
        JSONObject jsonObject = new JSONObject();
        if (book.getIssuedTo() == null || book.getIssuedTo().isEmpty()) {
            book.setIssuedTo(email);
            book.setIssuedOn(new Date());
            book.setSubmittedOn(book.getIssuedOn());
            bookRepository.save(book);
            counter(book.getBookId(), false);

            Notification notification = new Notification();
            notification.setTitle("Book issued alert!");
            notification.setContentDetailInfo(book.getTitle() + " book is issued to your account " + email + " on " + new Date().toString());
            notifyUser(notification, email);

            jsonObject.put("status", "done");
            jsonObject.put("message", book.getTitle() + " book issued successfully");
        } else {
            jsonObject.put("status", "failed");
            jsonObject.put("message", "book already issued");
        }
        return ResponseEntity.ok().body(jsonObject.toString());

    }

    @Secured(value = {Constants.ROLE_ADMIN})
    @PostMapping(value = {"/submit/{bookId}"})
    public @ResponseBody
    ResponseEntity<String> submitBook(@PathVariable String bookId) {
        //todo implementation
        return null;
    }

    @PostMapping(value = {"/book/{bookId}"})
    public @ResponseBody
    ResponseEntity<BookDetail> bookDetail(@PathVariable String bookId) {
        Optional<Book> optional = bookRepository.findById(bookId);
        Optional<BookDetail> optionalBookDetail = bookDetailRepository.findById(optional.get().getBookId());
        return ResponseEntity.ok().body(optionalBookDetail.get());
    }

    private void notifyUser(Notification notification, String email) {
        new Thread(() -> {
            try {
                Token token = tokenRepository.findByEmail(email);
                if (token != null) {
                    NotificationSender notificationSender = new NotificationSender();
                    notificationSender.send(notification.getMessageData()).toToken(token.getToken());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void counter(String id, boolean increment) {
        new Thread(() -> {
            Optional<BookDetail> byId = bookDetailRepository.findById(id);
            if (byId.isPresent()) {
                BookDetail bookDetail = byId.get();
                bookDetail.setAvailable(bookDetail.getAvailable() + (increment ? 1 : -1));
                bookDetailRepository.save(bookDetail);
            }
        }).start();
    }
}
