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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        RequestDispatcher dispatcher = null;

        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");

        if(userDao.validate(userName, passWord)) {
            System.out.println(">>>> Login validated!!");
            HttpSession session = request.getSession();
            session.setAttribute("username", userName);

            dispatcher = request.getRequestDispatcher("dashboard.jsp");
            dispatcher.forward(request, response);
        } else {
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.include(request, response);
            out.println("<h3 style='color:red'>Login Failed.. Tray Again</h3>");
        }



    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.service(request, response);
    }
}
