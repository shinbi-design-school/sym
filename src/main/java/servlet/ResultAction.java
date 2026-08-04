package servlet;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.DAO.CustomerDAO;
import model.bean.Customer;
import model.tool.Action;

public class ResultAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CustomerDAO dao = new CustomerDAO();
		List<Customer> ranking = dao.searchRank();
		request.setAttribute("ranking", ranking);
		
		return "/WEB-INF/jsp/top.jsp";
	}

}
