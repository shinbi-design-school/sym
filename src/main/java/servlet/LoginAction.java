package servlet;

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
			request.setAttribute("error", "ログイン名またはパスワードが違います。");
			return "/WEB-INF/jsp/login-in.jsp";
		}
		
		session.setAttribute("customer", customer);
		return "/WEB-INF/jsp/login-out.jsp";
	}
	
}
