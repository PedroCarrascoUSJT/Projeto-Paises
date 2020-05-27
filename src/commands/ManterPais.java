package commands;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.PaisService;
import model.Pais;
/**
 * Servlet implementation class PaisController
 */

public class ManterPais implements Command{

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String nome = request.getParameter("nome");
			int populacao = Integer.parseInt(request.getParameter("populacao"));
			double area = Double.parseDouble(request.getParameter("area"));
			
			Pais pais = new Pais(nome,populacao,area);
			pais.setId(new PaisService().criar(pais));
			
			request.setAttribute("pais", pais);
			
			RequestDispatcher view = request.getRequestDispatcher("Pais.jsp");
			view.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}
