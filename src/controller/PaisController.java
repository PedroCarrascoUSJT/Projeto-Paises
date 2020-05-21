package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.PaisService;
import model.Pais;
/**
 * Servlet implementation class PaisController
 */
@WebServlet("/ManterPais.do")
public class PaisController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public PaisController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	}

}
