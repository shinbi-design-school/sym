<%@page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<%@ page import="com.design_shinbi.quiz.model.Result" %>

<%
    String name = (String)request.getAttribute("name");
    Result result = (Result)request.getAttribute("result");
%>

        <h1>４択クイズの結果</h1>
        <p><%= name %> さんの４択クイズの結果は</p>
        <p><span id="result"><%= result.getName() %></span> です。</p>
        <p><%= result.getDescription() %></p>
        [<a href="top">戻る</a>]

 <%@include file="footer.jsp" %>