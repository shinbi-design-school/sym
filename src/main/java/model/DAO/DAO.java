package model.DAO;

import java.sql.Connection;

import javax.naming.InitialContext;
import javax.sql.DataSource;       

//すべてのDAOクラスの親となる基盤クラス
//データベースへの接続管理（コネクションの取得）を共通化

public class DAO {	
	static DataSource ds;
	
	//データベースへの接続（Connection）を取得して返すメソッド
	public Connection getConnection() throws Exception {
		
		if(ds == null) {
			InitialContext ic = new InitialContext();

			ds = (DataSource)ic.lookup("java:comp/env/jdbc/sym");
		}
		return ds.getConnection();
	}
}

