package com.svceindore.minor.view;

import com.svceindore.minor.fcm.NotificationSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/index.html"})
    public String index() {
        return "index";
    }

    @GetMapping(value = {"/logout.html", "/logout"})
    public String logout() {
        return "logout";
    }

    @RequestMapping(value = {"/login.html", "/login"})
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/add-book.html"})
    public String addBook() {
        return "add-book";
    }

    @RequestMapping(value = {"/explore-book.html"})
    public String exploreBook() {
        return "explore-book";
    }

    @RequestMapping(value = {"/issue-book.html"})
    public String issueBook() {
        return "issue-book";
    }

}