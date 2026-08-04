<%@page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

    <main class="container">
        <!-- リザルト画面 -->
         <section class="result-area">
            <!-- 評価 -->
            <div class="result-evaluation-area">
                <h1>クイズの結果は...</h1>
                <h2><span id="correct">${point} 問</span> / 10問</h2>
                <h3>${result.rank}</h3>
            </div>
            <!-- ポイント -->
            <div class="result-point-area">
<!--                <p>累計ポイント : 220 <span id="addPoint"> + ${point}</span></p>-->
				<p>獲得ポイント : ${point * 10}</p>
                <p>現在累計ポイント : ${customer.totalPoint}</p>
            </div>
         </section>
        
        <!-- トップへ戻る -->
        <section class="action-area">
            <form action="Result.action" method="post">
                <button type="submit">トップへ戻る</button>
            </form>
        </section>

    </main>

 <%@include file="footer.jsp" %>