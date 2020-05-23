package com.svceindore.minor.view;

import com.svceindore.minor.utils.Constants;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
    @RequestMapping(value = {"/", "/index.html"})
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public void method(HttpServletResponse httpServletResponse) {
        SecurityContext context = SecurityContextHolder.getContext();
        GrantedAuthority o = (GrantedAuthority) context.getAuthentication().getAuthorities().toArray()[0];
        String to;
        if (o.getAuthority().equalsIgnoreCase(Constants.ROLE_ADMIN)) {
            to = "admin";
        } else if (o.getAuthority().equalsIgnoreCase(Constants.ROLE_USER)) {
            to = "user";
        } else {
            to = "login";
        }
        httpServletResponse.setHeader("Location", "http://minor.com:8080/" + to);
        httpServletResponse.setStatus(302);
    }

    @Secured(Constants.ROLE_USER)
    @RequestMapping(value = {"/user"})
    public String user() {
        return "user";
    }

    @Secured(Constants.ROLE_ADMIN)
    @RequestMapping(value = {"/admin"})
    public String admin() {
        return "admin";
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