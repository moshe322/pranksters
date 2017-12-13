package com.littlejohnny.commons.webcomponents.controllers;

import com.littlejohnny.commons.database.dataObjects.User;
import com.littlejohnny.commons.database.jdbc.DAO.DAOImpls.UserDAOImpl;
import com.littlejohnny.commons.database.jdbc.connection.TransactionManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet(name="registrationServlet", urlPatterns = "/registration")
public class RegistrationController extends HttpServlet{

    private static final UserDAOImpl userDAO = new UserDAOImpl();
    private static final TransactionManagerImpl txManager = new TransactionManagerImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            txManager.doInTransaction(() -> {
                User user = userDAO.selectByEmail(request.getParameter("email"));
                if(user == null) {
                    if(request.getParameter("password").equals(request.getParameter("password2"))) {
                        userDAO.create(new User(request.getParameter("email"), request.getParameter("password"), request.getParameter("username"), request.getParameter("surname"), Date.valueOf(request.getParameter("birthday")), request.getParameter("country"),request.getParameter("sity")));
                        request.setAttribute("status", "You are succesfuly registered!");
                        request.getRequestDispatcher("/index.jsp").forward(request, response);
                    } else {
                        request.setAttribute("status", "Passwords doesn`t match!");
                        request.getRequestDispatcher("/webapps/views/registration.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("status", "This e-mail alredy in use");
                    request.getRequestDispatcher("/webapps/views/registration.jsp").forward(request, response);
                }
                return true;
            });
        } catch (Exception e) {
            request.setAttribute("status", "Can`t register you now, please try later.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
}
