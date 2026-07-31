<%@page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<%
    String error = (String)request.getAttribute("error");
%>
        <h1>４択クイズ</h1>
        <p>４択クイズへようこそ。<br>あなたはどれだけ知ってますか？</p>
        <p>※問題は10問です。<br></p>

<%
	if (error != null) {
%>
		<div id="error"><%= error %></div>
<%
	}
%>

		<form action="Login.action" method="post">
			<p>ログイン名<input type="text" name="login"></p>
			<p>パスワード<input type="password" name="password"></p>
			<p><input type="submit" value="送信"></p>
		</form>
		
<%@include file="footer.jsp" %>