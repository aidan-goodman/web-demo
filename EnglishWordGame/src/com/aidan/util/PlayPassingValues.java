package com.aidan.util;

import com.aidan.pojo.User;
import com.aidan.pojo.Word;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PlayPassingValues {
    public void playPassingValues(HttpServletRequest request, HttpServletResponse response, int difficulty, List<Word> listtrue, List<Word> listall, User u, int sum, String Difficulty) throws ServletException, IOException {
        request.setAttribute("difficulty", difficulty);
        request.setAttribute("Difficulty", Difficulty);
        request.setAttribute("listtrue", listtrue);
        request.setAttribute("listall", listall);
        request.setAttribute("user", u);
        request.setAttribute("sum", sum);
        request.getRequestDispatcher("play-index.jsp").forward(request, response);
    }
}
