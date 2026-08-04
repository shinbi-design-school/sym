package servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.DAO.CustomerDAO;
import model.bean.Customer;
import model.tool.Action;

public class LoginAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		CustomerDAO dao = new CustomerDAO();
		Customer customer = dao.search(login, password);
		
		if(customer == null) {
			request.setAttribute("error", "ログイン情報が一致しません。");
			return "/WEB-INF/jsp/login.jsp";
		}
		
		List<Customer> ranking = dao.searchRank();
		request.setAttribute("ranking", ranking);
		
		session.setAttribute("customer", customer);
		return "/WEB-INF/jsp/top.jsp";
	}
	
}
