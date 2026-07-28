package com.design_shinbi.quiz.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.design_shinbi.quiz.model.bean.Customer;

public class CustomerDAO extends DAO {

	// ログインIDとパスワードから顧客を検索するメソッド
	public Customer search(String login, String password) throws Exception {
		Customer customer = null;
		
		// SQLの実行
		String sql = "select * from customer where LOGIN = ? and password = ?";

		// 親クラスからデータベース接続（Connection）を取得
		try (Connection con = getConnection();
			 PreparedStatement st = con.prepareStatement(sql)) {
			
			st.setString(1, login);   // ログインIDを設定
			st.setString(2, password);// passwordを設定
            
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {     // 該当するレコードが1件見つかった場合の処理
					customer = new Customer();
					customer.setId(rs.getInt("ID"));                 // レコードの「ID」列の値をセット
					customer.setLogin(rs.getString("LOGIN"));        // レコードの「LOGIN」列の値をセット
					customer.setPassword(rs.getString("password"));  // レコードの「password」列の値をセット

				}
			}
		} // ここを抜ける時に自動で rs, st, con を閉じる
	return customer;
}}
