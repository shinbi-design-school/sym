package servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.DAO.QuizDAO;
import model.bean.Quiz;
import model.tool.Action;
import model.tool.QuizSession;

public class QuizStartAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		QuizDAO quizDAO = new QuizDAO();
		
//		クイズを１０問取得　数字を変更することで問題数を変更可能
		List<Quiz> quizList = quizDAO.getRandomQuizzes(10);
		
		if(quizList == null) {
			request.setAttribute("error", "クイズデータの読み込みに失敗しました。");
			return "/WEB-INF/jsp/login-out.jsp";
		}
		
//		セッション属性にquizSessionを保存
		QuizSession quizSession = new QuizSession(quizList);
		session.setAttribute("quizSession", quizSession);
		
		return "/WEB-INF/jsp/quiz.jsp";
	}
	
}
