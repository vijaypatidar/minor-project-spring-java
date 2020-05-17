package com.svceindore.minor.servlet;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/otp"})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("text/json");
        PrintWriter out = resp.getWriter();
        JSONObject jsonObject = new JSONObject();
            jsonObject.put("opt","1234");
        System.out.println("====================================== "+req.getParameter("number"));
        out.println(jsonObject.toString());
        out.flush();
    }
}
