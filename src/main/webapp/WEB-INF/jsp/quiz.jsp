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
	<main class="container">
        
        <%-- 【修正完了】Java側の定義に合わせて getQuestionNumber() で「第◯問」を表示 --%>
        <!-- 問題文 -->
        <section class="question-area">
<% 
	if (quizSession != null) { 
%>
            <h1>第 <%= quizSession.getQuestionNumber() %> 問</h1>
<% 
	} 
%>
<% 
	if (quiz != null) { 
%>
            <%-- クイズの問題文を表示 --%>
            <p><%= quiz.getQuestion() %></p>
        </section>
        
        <!-- 選択肢 -->
        <section class="select-area">
            <form id ="quiz-form" action="Quiz.action" method="post">
                <ol id="quiz-select">
                    <li>
                        <button type="submit" name="answer" value="1"><%= quiz.getOption1() %></button>
                    </li>
                    <li>
                        <button type="submit" name="answer" value="2"><%= quiz.getOption2() %></button>
                    </li>
                    <li>
                        <button type="submit" name="answer" value="3"><%= quiz.getOption3() %></button>
                    </li>
                    <li>
                        <button type="submit" name="answer" value="4"><%= quiz.getOption4() %></button>
                    </li>
                </ol>
            </form>
        </section>
<%
	} else { 
%>
        <!-- エラー -->
        <section id="error">
            <p>クイズデータが見つかりません。最初からやり直してください。</p>
            <form action="QuizStart.action" method="post">
                <button type="submit">トップへ戻る</button>
            </form>
        </section>
<%
	} 
%>
    </main>
		
<%@include file="footer.jsp" %>
