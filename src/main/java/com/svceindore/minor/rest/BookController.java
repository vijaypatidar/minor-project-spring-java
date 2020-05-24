package com.svceindore.minor.rest;

import com.svceindore.minor.fcm.Notification;
import com.svceindore.minor.fcm.NotificationSender;
import com.svceindore.minor.model.Book;
import com.svceindore.minor.model.BookDetail;
import com.svceindore.minor.model.Token;
import com.svceindore.minor.repositories.BookDetailRepository;
import com.svceindore.minor.repositories.BookRepository;
import com.svceindore.minor.repositories.TokenRepository;
import org.springframework.http.ResponseEntity;
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
        System.out.println(book.getIssuedTo());
        if (book.getIssuedTo().isEmpty()||book.getIssuedTo()==null){
            book.setIssuedTo(email);
            book.setIssuedOn(new Date());
            book.setSubmittedOn(book.getIssuedOn());
            bookRepository.save(book);
            counter(book.getBookId(),false);

            Notification notification = new Notification();
            notification.setTitle("Book issued!");
            notification.setContentDetailInfo("New book(" + book.getTitle() + ") is issued to your account " + email + " on " + new Date().toString());
            notifyUser(notification, email);
            return ResponseEntity.ok().body("issued ");
        }else {
            return ResponseEntity.ok().body("Book already issued");
        }

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

    private void counter(String id,boolean increment){
        new Thread(()->{
            Optional<BookDetail> byId = bookDetailRepository.findById(id);
            if (byId.isPresent()){
                BookDetail bookDetail = byId.get();
                bookDetail.setAvailable(bookDetail.getAvailable()+(increment?1:-1));
                bookDetailRepository.save(bookDetail);
            }
        }).start();
    }
}
