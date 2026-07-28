package com.design_shinbi.quiz.model.tool;

import java.io.Serializable;
import java.util.List;

import com.design_shinbi.quiz.model.bean.Quiz;

public class QuizSession implements Serializable {
	private static final long serialVersionUID = 1L;

	private final List<Quiz> quizList; // 全問題リスト
	private int index = 0; // 現在のインデックス（0番から開始）
	private int score = 0; // 正解数
	
	private int lastUserAnswer = 0;   // ユーザーが最後に選んだ番号 (1〜4)
	private boolean lastResult = false; // 最後に解いた問題の正誤 (true / false)
	private boolean isAnswered = false; // 今の問題に対して回答済みかどうかのフラグ

	// 新しいゲームを開始するときに問題をセット
	public QuizSession(List<Quiz> quizList) {
		this.quizList = quizList;
	}

	// 現在の問題を取り出す（全問終了ならnull、まだあるなら現在の問題を返す）
	public Quiz getCurrentQuiz() {
		return isFinished() ? null : quizList.get(index);
	}

	// 答え合わせをして、次の問題に進む
	public boolean checkAnswer(int userAnswer) {
		if (isFinished())
			return false;
		
		this.lastUserAnswer = userAnswer; // ユーザーの選んだ番号を記憶
		this.isAnswered = true;           // 回答済みにする
		
		boolean isCorrect = (userAnswer == getCurrentQuiz().getCorrectAnswer());
		this.lastResult = isCorrect; 

		if (isCorrect) {
			score+=10; // 正解ならスコアを増やす（＋１０）
		}
		return isCorrect; // 正解ならtrue、不正解（✖）ならfalseを返す
	}
	
	// 次の問題へ進む処理
	public void nextQuestion() {
		if (!isFinished()) {
			index++; // ここで初めて次の問題に進む
			
			this.isAnswered = false; 
			this.lastUserAnswer = 0;
			this.lastResult = false;
		}
	}

	// すべての問題を解き終わったか判定（インデックスが総数に達したら終了）
	public boolean isFinished() {
		return index >= quizList.size();
	}

	// 画面表示用の問題番号（全問終了なら総問題数、解いている最中なら index + 1）
	public int getQuestionNumber() {
		return isFinished() ? quizList.size() : index + 1;
	}

	// 総問題数を取得
	public int getTotalQuestions() {
		return quizList.size();
	}

	// 現在のスコア（正解数）を取得
	public int getScore() {
		return score;
	}
	
	//JSP（画面）側から色や文字を判定するために使うゲッター
	
	public int getLastUserAnswer() {
		return lastUserAnswer;
	}

	public boolean isLastResult() {
		return lastResult;
	}

	public boolean isAnswered() {
		return isAnswered;
	}
}
