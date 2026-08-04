package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.bean.Customer;

public class CustomerDAO extends DAO {

	// ログインIDとパスワードから顧客を検索
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
					customer.setTotalPoint(rs.getInt("TOTALPOINT")); // レコードの「TOTALPOINT」列の値をセット
				}
			}
		} // ここを抜ける時に自動で rs, st, con を閉じる
	return customer;
	}
	
	// 顧客のポイントを更新するメソッド
	public void updatePoint(Customer customer) throws Exception {

	    String sql = "UPDATE customer SET TOTALPOINT = ? WHERE ID = ?";

	    try (Connection con = getConnection();
	         PreparedStatement st = con.prepareStatement(sql)) {

	        st.setInt(1, customer.getTotalPoint()); // 加算後のポイント
	        st.setInt(2, customer.getId());         // 顧客ID

	        st.executeUpdate();
	    }
	}
	
	public List<Customer> searchRank() throws Exception {
	    List<Customer> list = new ArrayList<>();
	    String sql = "SELECT LOGIN, TOTALPOINT FROM CUSTOMER ORDER BY TOTALPOINT DESC LIMIT 3;";

	    try (Connection con = getConnection();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Customer c = new Customer();
	            c.setLogin(rs.getString("LOGIN"));
	            c.setTotalPoint(rs.getInt("TOTALPOINT"));
	            list.add(c);
	        }
	    }
	    return list;
	}

}
