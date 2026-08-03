<%@page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

	<h1>４択クイズの結果</h1>
	<p>${customer.login} さんの４択クイズの結果は</p>
	<p id=result>${result.rank}</p>
	<p>${point} / 100</p>
	<p>累計ポイント : ${customer.totalPoint}</p>
	
	<form action="Result.action" method="post">
		<button type="submit">トップへ戻る</button>
	</form>

 <%@include file="footer.jsp" %>