package model.tool;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.action")
public class FrontController extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String path = request.getServletPath().substring(1);
			String name = path.replace(".a", "A").replace('/', '.');
			name = "servlet." + name;
			Action action = (Action)Class.forName(name).getDeclaredConstructor().newInstance();
			System.out.print("name" + name);
			String url = action.execute(request, response);
			request.getRequestDispatcher(url).forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace(out);
		}
	}

}
