<%@page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<%
    String error = (String)request.getAttribute("error");
%>

    <main class="container">
        <!-- ヒーローエリア -->
        <section class="hero-area" aria-labelledby="quiz-title">
            <h1 id="quiz-title">歴史４択クイズ</h1>
            <p>あなたはどれだけ知っていますか？</p>
        </section>

        <!-- フォームエリア -->
        <section class="login-area" aria-labelledby="login-form-title">
            <form id="login-form" action="Login.action" method="post">
<%
	if (error != null) {
%>
                <p id="error">${error}</p>
<%
	}
%>
                <input type="text" name="login" placeholder="Login ID">
                <input type="password" name="password" placeholder="Password">
                <button type="submit">ログイン</button>
            </form>
        </section>
        
    </main>

		
<%@include file="footer.jsp" %>