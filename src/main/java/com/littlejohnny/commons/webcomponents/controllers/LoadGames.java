package com.littlejohnny.commons.webcomponents.controllers;

import com.littlejohnny.commons.database.dataObjects.Game;
import com.littlejohnny.commons.database.jdbc.DAO.DAOImpls.GameDAOImpl;
import com.littlejohnny.commons.database.jdbc.connection.TransactionManagerImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@WebServlet(name="loadGamesServlet", urlPatterns = "/loadGames")
public class LoadGames extends HttpServlet {

    private static final GameDAOImpl gameDAO = new GameDAOImpl();
    private static final TransactionManagerImpl txManager = new TransactionManagerImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Game> result = null;
        try {
            result = txManager.doInTransaction(gameDAO::selectAll);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("gamesList", result);
        request.getRequestDispatcher("/webapps/views/games.jsp").forward(request, response);
    }
}
