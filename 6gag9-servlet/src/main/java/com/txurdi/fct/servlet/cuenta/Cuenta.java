package com.txurdi.fct.servlet.cuenta;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.txurdi.fct.jpa.model.Usuario;

/**
 * Servlet implementation class Cuenta
 * @author luiokx
 * @author josumc
 */
public class Cuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Recuperamos los objetos como el usuario y las publicaciones
		
		if(request.getSession().getAttribute("usuario") == null) {
			response.sendRedirect("./Inicio");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("./view/account.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().invalidate();
		
		response.sendRedirect("./Inicio");
	}
}