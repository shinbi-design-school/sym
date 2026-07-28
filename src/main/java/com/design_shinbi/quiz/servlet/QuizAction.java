package com.design_shinbi.quiz.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.design_shinbi.quiz.model.bean.Customer;
import com.design_shinbi.quiz.model.bean.Quiz;
import com.design_shinbi.quiz.model.tool.QuizSession;

@WebServlet("/servlet/QuizAction") // JSPのボタンなどのリンク先になります

public class QuizAction extends  HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        

        Customer customer = (Customer) session.getAttribute("customer");
        
        if (customer == null) {

            customer = new Customer(); 
            session.setAttribute("customer", customer);
        }

        List<Quiz> quizList = (List<Quiz>) session.getAttribute("allQuizList"); 
        

        QuizSession quizSession = new QuizSession(quizList, customer);
        
        session.setAttribute("quizSession", quizSession);
        

        request.getRequestDispatcher("/quiz.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
