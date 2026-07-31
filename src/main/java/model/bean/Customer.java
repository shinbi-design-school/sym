package model.bean;

import java.io.Serializable;

public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id; // ID
	private String login; // ログイン名
	private String password;  // パスワード
	private int totalPoint;   // 累計ポイント

	public Customer() {
	}
	// 全フィールドを初期化するコンストラクタ
	public Customer(int id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.totalPoint = 0; // 初回ログイン及び、再ログイン時は0ポイントからスタート
	}
	
	// クイズクリア時にポイントを累積加算するためのメソッド
	public void addPoint(int point) {
		this.totalPoint += point;
	}
	
	//フィールドの値を取得（ゲッタ）
	
	public int getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	public int getTotalPoint() { // トータルポイント
		return totalPoint;
	}
	
	
	//フィールドの値を設定（セッタ）
	
	public void setId(int id) {
		this.id = id;
	}
	public void setLogin(String login) {
		this.login = login; 
	}

	public void setPassword(String password) {
		this.password = password;   
	}
	
	public void setTotalPoint(int totalPoint) { // トータルポイント
		this.totalPoint = totalPoint;
	}

}


