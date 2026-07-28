package com.design_shinbi.quiz.model.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.design_shinbi.quiz.model.DAO.QuizDAO;
import com.design_shinbi.quiz.model.bean.Quiz;

class QuizDAOTest {

    private QuizDAO quizDAO;

    @BeforeEach
    void setUp() {
        // テストごとにDAOのインスタンスを初期化
        quizDAO = new QuizDAO();
    }

    @Test
    @DisplayName("指定した件数のクイズが取得できること")
    void testGetRandomQuizzes_ReturnsCorrectLimit() throws Exception {
        int limit = 5;
        List<Quiz> result = quizDAO.getRandomQuizzes(limit);

        assertNotNull(result, "結果のリストがnullであってはならない");
        assertEquals(limit, result.size(), "指定した件数と同じ数が取得できること");
        
        // 1件目のデータに中身が入っているか検証
        if (!result.isEmpty()) {
            Quiz firstQuiz = result.get(0);
            assertTrue(firstQuiz.getId() > 0, "IDが正しくセットされていること");
            assertNotNull(firstQuiz.getQuestion(), "問題文がnullであってはならない");
            assertNotNull(firstQuiz.getOption1(), "選択肢1がnullであってはならない");
        }
    }

    @Test
    @DisplayName("引数に0を指定した場合、空のリストが返ること")
    void testGetRandomQuizzes_ZeroLimit() throws Exception {
        List<Quiz> result = quizDAO.getRandomQuizzes(0);
        
        assertNotNull(result);
        assertTrue(result.isEmpty(), "0件指定の場合はリストが空であること");
    }

    @Test
    @DisplayName("引数に負の数を指定した場合、空のリストが返ること")
    void testGetRandomQuizzes_NegativeLimit() throws Exception {
        List<Quiz> result = quizDAO.getRandomQuizzes(-1);
        
        assertNotNull(result);
        assertTrue(result.isEmpty(), "マイナス指定の場合はリストが空であること");
    }

    @Test
    @DisplayName("連続で呼び出した際に、ランダムに並び替えられていること")
    void testGetRandomQuizzes_IsRandom() throws Exception {
        // データの総数より十分小さい件数（例: 3件）を2回取得して比較する
        List<Quiz> list1 = quizDAO.getRandomQuizzes(3);
        List<Quiz> list2 = quizDAO.getRandomQuizzes(3);

        // 片方でも空ならテストをスキップ（またはアサーション失敗）
        if (list1.size() < 3 || list2.size() < 3) {
            fail("テストデータが足りないため、ランダム性の検証ができません。");
        }

        // 1番目のクイズのIDが一致しない、または2番目、3番目の並びが異なることを期待
        boolean isDifferent = false;
        for (int i = 0; i < 3; i++) {
            if (list1.get(i).getId() != list2.get(i).getId()) {
                isDifferent = true;
                break;
            }
        }

        // ※確率的に同じ順序になる可能性が極めて低いため、基本的には異なるはず
        assertTrue(isDifferent, "2回連続で取得した結果の並び順が異なること");
    }
}
