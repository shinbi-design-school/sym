package com.design_shinbi.quiz.model.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.design_shinbi.quiz.model.bean.Quiz;
import com.design_shinbi.quiz.model.tool.QuizSession;

public class QuizModelTest {
	public static void main(String[] args) {
		System.out.println("====== 【モデル部】単体動作確認テスト ======");
		
		// 1. データベースの代わりに、テスト用のクイズ（9個のフィールド保持）を3問手動で作る
		List<Quiz> mockQuizzes = new ArrayList<>();
		mockQuizzes.add(new Quiz(1, "1600年に起こった、天下分け目の戦いは何？", "桶狭間の戦い", "関ヶ原の戦い", "川中島の戦い", "本能寺の変", 2, "江戸", "徳川"));
		mockQuizzes.add(new Quiz(2, "鎌倉幕府を開いた人物は誰？", "源頼朝", "源義経", "足利尊氏", "織田信長", 1, "鎌倉", "将軍"));
		mockQuizzes.add(new Quiz(3, "江戸幕府の最後の将軍（第15代）は誰？", "徳川家康", "徳川吉宗", "徳川家光", "徳川慶喜", 4, "幕末", "大政奉還"));

		System.out.println("[確認] テスト用クイズを " + mockQuizzes.size() + " 問用意しました。");
		System.out.println("[確認] QuizSession（確定版）を初期化します。\n");

		// 2. 確定したQuizSessionクラスにリストを渡してゲーム開始
		QuizSession quizSession = new QuizSession(mockQuizzes);
		Scanner scanner = new Scanner(System.in);
		
		// クイズが終了（isFinishedがtrue）になるまでコンソールで回答を繰り返すシミュレーション
		while (!quizSession.isFinished()) {
			Quiz current = quizSession.getCurrentQuiz();
			
			System.out.println("----------------------------------------------");
			System.out.println("★画面表示用問題番号: " + quizSession.getQuestionNumber() + "問目 / 全" + quizSession.getTotalQuestions() + "問");
			System.out.println("問題: " + current.getQuestion());
			System.out.println(" 1. " + current.getOption1());
			System.out.println(" 2. " + current.getOption2());
			System.out.println(" 3. " + current.getOption3());
			System.out.println(" 4. " + current.getOption4());
			
			// 未定のキーワード1, 2が正しくQuizの中に保持されているかも同時にデバッグ確認
			System.out.println("(保持確認キーワード: " + current.getKeyword1() + " / " + current.getKeyword2() + ")");
			
			// 回答入力の受け付け
			System.out.print("あなたの回答（1〜4の番号）を入力: ");
			int answer = scanner.nextInt();
			
			// 確定したcheckAnswerを実行（正誤判定しつつ自動で内部インデックスが進む）
			boolean isCorrect = quizSession.checkAnswer(answer);
			System.out.println("判定結果: " + (isCorrect ? "【正解！】" : "【不正解...】"));
			System.out.println("現在の獲得点数: " + quizSession.getScore() + "点");
		}
		
		// 3. 全問終了後のバグ対策チェック
		System.out.println("==============================================");
		System.out.println("ゲーム終了処理に入ります（isFinished == true）");
		System.out.println("バグ対策チェック（全問終了時の画面表示用番号）: " + quizSession.getQuestionNumber() + "問目判定");
		System.out.println("最終スコア: 全 " + quizSession.getTotalQuestions() + " 問中、 " + quizSession.getScore() + " 問正解！");
		System.out.println("==============================================");
		
		scanner.close();
	}
}

