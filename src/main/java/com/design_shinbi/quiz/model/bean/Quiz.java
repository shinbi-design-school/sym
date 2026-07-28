package com.design_shinbi.quiz.model.bean;

import java.io.Serializable;
import java.util.List;

public class Quiz implements Serializable {
	// クラスのバージョン識別子（任意の数値を指定可能）
	private static final long serialVersionUID = 1L;
	//フィールド定義
	private int id; // id
	private String question; // 問題文
	private String option1; // 選択１
	private String option2; // 選択２
	private String option3; // 選択３
	private String option4; // 選択４
	private int correctAnswer; // 答え（正解の番号: 1〜4など）
	private String keyword1; // キーワード１
	private String keyword2; // キーワード２

	// デフォルトコンストラクタ（引数なし）
	public Quiz() {
	}

	// 全フィールドを初期化するコンストラクタ
	public Quiz(int id, String question, String option1, String option2,
			String option3, String option4, int correctAnswer,
			String keyword1, String keyword2) {
		this.id = id;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.correctAnswer = correctAnswer;
		this.keyword1 = keyword1;
		this.keyword2 = keyword2;

	}

	//フィールドの値を取得（ゲッタ）
	public int getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public String getOption1() {
		return option1;
	}

	public String getOption2() {
		return option2;
	}

	public String getOption3() {
		return option3;
	}

	public String getOption4() {
		return option4;
	}

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public String getKeyword1() {
		return keyword1;
	}

	public String getKeyword2() {
		return keyword2;
	}

	//フィールドの値を設定（セッタ）
	public void setId(int id) {
		this.id = id;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;

	}

	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}


	//option1～4を1つのリストにしてまとめて返す
	public List<String> getOptions() {
		return List.of(option1, option2, option3, option4);
	}
	
}
