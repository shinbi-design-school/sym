<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

		<p>ようこそ、${customer.login}さん。</p>
		<p>※１問正解すると10ポイント獲得</p>
		<p>現在のあなたのポイント：</p>
		<p>現在のポイント順位</p>
		<p>　1位：</p>
		<p>　2位：</p>
		<p>　3位：</p>
		
		<p><a href="Quiz.action">スタート</a></p>

<%@include file="footer.jsp" %>
