package servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.DAO.CustomerDAO;
import model.bean.Customer;
import model.bean.Result;
import model.tool.Action;
import model.tool.QuizSession;

public class QuizAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		QuizSession quizSession = (QuizSession)session.getAttribute("quizSession");
		CustomerDAO dao = new CustomerDAO();
		Customer customer = (Customer)session.getAttribute("customer");
		
//		ラジオボタンの回答を取得
		String answer = request.getParameter("answer");
		Integer userAnswer = Integer.parseInt(answer);
		
//		これが答え合わせとスコア加算をしてる
		boolean correct = quizSession.checkAnswer(userAnswer);
//		H2DBにアクセスし正解ならスコア加算
		if(correct) {
//			customerのトータルポイントを加算
			customer.addPoint();
			// DBへ反映
			dao.updatePoint(customer);
		}
		
//		次の問題にカウントを進める
		quizSession.nextQuestion();
		
//		すべての問題が終わったか判定
		if(quizSession.isFinished()) {
//			Resultオブジェクトを生成し、リクエスト属性に保存
			Result result = new Result(quizSession.getTotalQuestions(), quizSession.getScore());
			request.setAttribute("result", result);
			
//			リクエスト属性にpointを保存
			int point = result.getScore() / 10;
			request.setAttribute("point", point);
			
			
			return "/WEB-INF/jsp/result.jsp";
		}
		
		return "/WEB-INF/jsp/quiz.jsp";
	}

}
