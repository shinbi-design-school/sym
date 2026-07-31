package model.bean;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private int totalQuestions; // 総問題数
    private int score;          // 獲得スコア
    private int maxScore;       // 満点（総問題数 × 10点）

    // コンストラクタ
    public Result(int totalQuestions, int score) {
        this.totalQuestions = totalQuestions;
        this.score = score;
        this.maxScore = totalQuestions * 10;
    }

    // 正解率（％）を計算するメソッド
    public int getAccuracyRate() {
        if (totalQuestions == 0) return 0;
        int correctAnswers = score / 10; 
        return (correctAnswers * 100) / totalQuestions;
    }

    // 点数に応じた評価を返すメソッド
    public String getRank() {
        int rate = getAccuracyRate();
        if (rate == 100) return "全問正解です。！";
        if (rate >= 80)  return "もう一息です。";
        if (rate >= 50)  return "悪くないですね。";
        return "頑張りましょう。";
    }

    // ゲッター
    public int getTotalQuestions() { return totalQuestions; }
    public int getScore() { return score; }
    public int getMaxScore() { return maxScore; }
    
}

