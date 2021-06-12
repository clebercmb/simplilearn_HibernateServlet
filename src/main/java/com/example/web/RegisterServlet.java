package com.example.web;

import com.example.dao.UserDao;
import com.example.models.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {

    private UserDao userDao = new UserDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("You are trying to register new user");

        User user = new User();
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setUsername(request.getParameter("userName"));
        user.setPassword(request.getParameter("password"));

        System.out.println(">>> user="+user);

        userDao.save(user);

        response.sendRedirect("dashboard.jsp");

    }
}
