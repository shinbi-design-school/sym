<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@ page import="model.bean.Quiz" %>
<%@ page import="model.tool.QuizSession" %>

<%
    String error = (String)request.getAttribute("error");
    
    // セッションからクイズ進行オブジェクトを取得
    QuizSession quizSession = (QuizSession)session.getAttribute("quizSession");
    Quiz quiz = null;
    
    if (quizSession != null) {
        quiz = quizSession.getCurrentQuiz(); 
    }
%>	
        <h1>４択クイズ</h1>
        
        <%-- 【修正完了】Java側の定義に合わせて getQuestionNumber() で「第◯問」を表示 --%>
        <% if (quizSession != null) { %>
            <h3>第 <%= quizSession.getQuestionNumber() %> 問</h3>
        <% } %>

        <div id="error"><%= error != null ? error : "" %></div>

        <% if (quiz != null) { %>
            <%-- クイズの問題文を表示 --%>
            <p class="question"><strong>問題：<%= quiz.getQuestion() %></strong></p>

            <%-- 送信先を Quiz.action に指定して doPost へ回答を送る --%>
            <form action="Quiz.action" method="post">
                <button type="submit" name="answer" value="1">① <%= quiz.getOption1() %></button>
                <button type="submit" name="answer" value="2">② <%= quiz.getOption2() %></button>
                <button type="submit" name="answer" value="3">③ <%= quiz.getOption3() %></button>
                <button type="submit" name="answer" value="4">④ <%= quiz.getOption4() %></button>
            </form>
		<%
			} else { 
		%>
            <p>クイズデータが見つかりません。最初からやり直してください。</p>
			<form action="QuizStart.action" method="post">
				<button type="submit"></button>
			</form>
        <%
			} 
		%>
		
<%@include file="footer.jsp" %>
