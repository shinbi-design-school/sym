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
		
//		ラジオボタンの回答を取得し答え合わせ、スコア加算
		String answer = request.getParameter("answer");
		Integer userAnswer = Integer.parseInt(answer);
		
//		これが答え合わせとスコア加算をしてる
		quizSession.checkAnswer(userAnswer);
		
//		次の問題にカウントを進める
		quizSession.nextQuestion();
		
//		すべての問題が終わったか判定
		if(quizSession.isFinished()) {
//			Resultオブジェクトを生成し、リクエスト属性に保存
			Result result = new Result(quizSession.getTotalQuestions(), quizSession.getScore());
			request.setAttribute("result", result);
			
//			customerをセッション属性から取得しポイントを加算
			Customer customer = (Customer)session.getAttribute("customer");
			customer.addPoint(quizSession.getScore());
			// DBへ反映
			CustomerDAO dao = new CustomerDAO();
			dao.updatePoint(customer);
			
//			リクエスト属性にpointを保存
			request.setAttribute("point", quizSession.getScore());
			
			return "/WEB-INF/jsp/result.jsp";
		}
		
		return "/WEB-INF/jsp/quiz.jsp";
	}

}
