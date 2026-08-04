<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="model.bean.Customer" %>


    <main class="container">
        <!-- ユーザー情報 -->
        <section class="user-summary">
            <h1 id="welcome-title">ようこそ、${customer.login}さん</h1>
            <p>現在のポイント：${customer.totalPoint}</p>
        </section>
        <!-- ランキング -->
        <section class="ranking-info">
            <h2 id="ranking-title">ポイントランキング</h2>
            <ol>
<%
	List<Customer> ranking = (List<Customer>)request.getAttribute("ranking");

	for(Customer c : ranking) {
%>
                <li>位：<%= c.getLogin() %> : <%= c.getTotalPoint()%></li>
<%
	}
%>
            </ol>
            <p class="ranking-note">※1問正解すると10ポイント獲得</p>
        </section>
        <!-- スタートボタン -->
        <section class="action-area">
            <form action="QuizStart.action" method="post">
                <button class="startButton" type="submit">クイズに挑戦</button>
            </form>
        </section>
    </main>

<%@include file="footer.jsp" %>
