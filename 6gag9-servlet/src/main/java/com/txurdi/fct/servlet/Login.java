package com.txurdi.fct.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.txurdi.fct.jpa.dao.UsuarioDaoImpl;
import com.txurdi.fct.jpa.model.Usuario;

/**
 * Servlet implementation class Login
 * 
 * @author luiokx
 * @author josumc
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("./view/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email").toLowerCase();
		String password = request.getParameter("password");

		RequestDispatcher dispatcher = request.getRequestDispatcher("./view/login.jsp");

		// Controlamos los errores posibles
		// Campos vacios o nulos
		if (email == null || password == null || password.length() < 6) {
			request.setAttribute("status", "fill");
			dispatcher.forward(request, response);
			return;
		}
		
		//Comprobamos si el usuario existe o si la contrseña es incorrecta
		Usuario usuarioForm = new Usuario(email, password);
		Usuario userDB = UsuarioDaoImpl.findUser(usuarioForm);
		
		if (userDB == null) {
			//TODO ASIGNAR MISMO ERRROR DENTRO DE ESTOS ENCAPSULAMIENTOS
			request.setAttribute("status", "failed");
			dispatcher.forward(request, response);
		} else if(userDB != null) {
			//TODO iniciamos sesion
			
			
			HttpSession session = request.getSession();
			if(session == null) {
				session = request.getSession(true);
			}
			
			session.setAttribute("usuario", userDB);
			
			
			response.sendRedirect("./Inicio");
		}
	}
}
