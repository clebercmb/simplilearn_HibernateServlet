package com.example.web;

import com.example.dao.UserDao;
import com.example.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {

    private UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("You clicked me");
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");

        PrintWriter out = response.getWriter();

        if(userDao.validate(userName, passWord)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login-success.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("login-success.jsp");
            dispatcher.include(request, response);
            out.println("<h3 style='color:red'>Login Failed.. Tray Again</h3>");
        }

        //User user = new User();
        //user.setFirstName("Tom");
        //user.setLastName("Jerry");
        //user.setPassword("123");
        //user.setUsername("tom");

        //userDao.save(user);

    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
    }
}
