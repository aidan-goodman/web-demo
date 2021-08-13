package com.aidan.util;

import com.aidan.pojo.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ScorePassingValues {
    public void scorePassingValues(HttpServletRequest request, HttpServletResponse response, int countPage, List<User> list, User u, String Difficulty) throws ServletException, IOException {
        request.setAttribute("countPage", countPage);
        request.setAttribute("list", list);
        request.setAttribute("user", u);
        request.setAttribute("Difficulty", Difficulty);
        request.getRequestDispatcher("score-index.jsp").forward(request, response);
    }
}
