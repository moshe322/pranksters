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

@WebServlet(name="loginServlet", urlPatterns = "/login")
public class LoginController extends HttpServlet {

    private static final UserDAOImpl userDAO = new UserDAOImpl();
    private static final TransactionManagerImpl txManager = new TransactionManagerImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        try {
            txManager.doInTransaction(() -> {
                User user = userDAO.selectByEmail(request.getParameter("email"));
                if(user != null && user.getPassword().equals(request.getParameter("password"))) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                } else if (user != null){
                    request.setAttribute("status", "Entered password is incorect !");
                    request.getRequestDispatcher("/webapps/views/login.jsp").forward(request, response);
                }
                else {
                    request.setAttribute("status", "Entered email is incorect !");
                    request.getRequestDispatcher("/webapps/views/login.jsp").forward(request, response);
                }
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
