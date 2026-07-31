package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bean.Quiz;

public class QuizDAO extends DAO {

	/**				
	 * データベースからランダムにクイズを取得する				
	 * @param limit 取得したい問題数（例：10問）				
	 */
	public List<Quiz> getRandomQuizzes(int limit) throws Exception {
		
		// クイズの一覧を格納するための空のリスト
		List<Quiz> quizzes = new ArrayList<>();  
		
		if (limit <= 0) {  
			return quizzes;
		}
		// データをランダムに並び替えて、指定された件数だけ取得する
		String sql = "SELECT * FROM QUIZ ORDER BY RANDOM() LIMIT ?";

		try (Connection conn = getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setInt(1, limit); // 1番目の「?」に引数のlimitを設定

			try (ResultSet rs = pstmt.executeQuery()) { // SQLを実行し、ResultSetを取得

				while (rs.next()) {
					Quiz quiz = new Quiz();
					// データベースから値を取り出して、Quizオブジェクトにセット
					quiz.setId(rs.getInt("ID"));                // クイズのID
					quiz.setQuestion(rs.getString("QUESTION")); // 問題文
					quiz.setOption1(rs.getString("OPTION1"));   // 選択肢1
					quiz.setOption2(rs.getString("OPTION2"));   // 選択肢2
					quiz.setOption3(rs.getString("OPTION3"));   // 選択肢3
					quiz.setOption4(rs.getString("OPTION4"));   // 選択肢4

					quiz.setCorrectAnswer(rs.getInt("CORRECT_ANSWER"));  // 正解の番号（例: 1〜4）
					quiz.setKeyword1(rs.getString("KEYWORD1")); // 解説用などのキーワード1
					quiz.setKeyword2(rs.getString("KEYWORD2")); // 解説用などのキーワード2

					quizzes.add(quiz);
				}
			}
		} // ここで自動的にrs, pstmt, connを閉じる
		return quizzes;
	}
}

