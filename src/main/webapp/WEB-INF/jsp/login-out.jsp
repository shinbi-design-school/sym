<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

		<p>ようこそ、${customer.login}さん。</p>
		<p>※１問正解すると10ポイント獲得</p>
		<p>現在のあなたのポイント：${customer.totalPoint}</p>
		<p>現在のポイント順位</p>
		<p>　1位：</p>
		<p>　2位：</p>
		<p>　3位：</p>
		
		<form action="QuizStart.action" method="post">
			<button type="submit">スタート</button>
		</form>

<%@include file="footer.jsp" %>
