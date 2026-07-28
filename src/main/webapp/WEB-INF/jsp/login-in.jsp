<%@page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@ page import="com.design_shinbi.shindan.model.Result" %>
<%@ page import="com.design_shinbi.shindan.model.Quiz" %>
<%@ page import="com.design_shinbi.shindan.model.Item" %>

<%
    String error = (String)request.getAttribute("error");
    Quiz quiz = (Quiz)request.getAttribute("quiz");
%>	
        <h1>４択クイズ</h1>
        <p>４択クイズへようこそ。<br>あなたはどれだけ知ってますか？</p>
        <p>※問題は10問です。<br></p>

        <div id="error"><%= error %></div>

		<form action="Login.action" method="post">
			<p>ログイン名<input type="text" name="login"></p>
			<p>パスワード<input type="password" name="password"></p>
			<p><input type="submit" value="送信"></p>
		</form>
		
<%@include file="footer.jsp" %>