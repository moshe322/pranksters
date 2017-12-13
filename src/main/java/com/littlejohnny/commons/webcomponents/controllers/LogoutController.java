package com.littlejohnny.commons.webcomponents.controllers;

import com.littlejohnny.commons.database.dataObjects.User;
import com.littlejohnny.commons.database.jdbc.DAO.DAOImpls.UserDAOImpl;
import com.littlejohnny.commons.database.jdbc.connection.TransactionManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name="logoutServlet", urlPatterns = "/logout")
public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("/index.jsp").forward(request, response);

    }
}
