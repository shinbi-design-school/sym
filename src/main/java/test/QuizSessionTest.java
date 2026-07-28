package com.design_shinbi.quiz.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.design_shinbi.quiz.model.QuizSession;

import bean.Quiz;

class QuizSessionTest {

	private List<Quiz> mockQuizList;

	@BeforeEach
	void setUp() {
		mockQuizList = new ArrayList<>();
		Quiz q1 = new Quiz(1, "問題1です", "選択肢1", "選択肢2", "選択肢3", "選択肢4", 1, null, null);
		Quiz q2 = new Quiz(2, "問題2です", "選択肢1", "選択肢2", "選択肢3", "選択肢4", 3, null, null);
		mockQuizList.add(q1);
		mockQuizList.add(q2);
	}

	@Test
	void testQuizFlow() {
		QuizSession session = new QuizSession(mockQuizList);
		assertFalse(session.isFinished());
		assertEquals(1, session.getQuestionNumber());
		assertEquals(2, session.getTotalQuestions());
		assertEquals(0, session.getScore());

		// 1問目の解答テスト
		Quiz current1 = session.getCurrentQuiz();
		assertEquals("問題1です", current1.getQuestion());
		
		boolean result1 = session.checkAnswer(1); 
		assertTrue(result1, "正解なのでtrueが返ること");
		assertEquals(10, session.getScore());
		
		// ★オリジナル仕様：手動で次の問題に進める
		session.nextQuestion(); 
		
		assertFalse(session.isFinished());
		assertEquals(2, session.getQuestionNumber(), "手動で進めたので2問目になること");

		// 2問目の解答テスト
		Quiz current2 = session.getCurrentQuiz();
		assertEquals("問題2です", current2.getQuestion());

		boolean result2 = session.checkAnswer(2); 
		assertFalse(result2, "不正解なのでfalseが返ること");
		assertEquals(10, session.getScore());
		
		// ★オリジナル仕様：手動で次の問題に進める（これで全問終了状態になる）
		session.nextQuestion(); 

		assertTrue(session.isFinished(), "全問解き終わったので終了状態になること");

		// 終了後の挙動テスト
		assertNull(session.getCurrentQuiz());
	}
}
