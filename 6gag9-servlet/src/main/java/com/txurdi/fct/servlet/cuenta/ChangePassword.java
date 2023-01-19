package com.txurdi.fct.servlet.cuenta;

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
 * Servlet implementation class ChangePassword
 */
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getSession().getAttribute("usuario") == null) {
			response.sendRedirect("./Inicio");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("./../view/change_password.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Recuperamos todos los datos
		HttpSession session = request.getSession();

		RequestDispatcher dispatcher = request.getRequestDispatcher("./../view/change_password.jsp");

		String password = request.getParameter("inputActualPassword");
		String passwordNew = request.getParameter("inputNewPassword");
		String passwordNewConfirm = request.getParameter("inputNewConfirmPassword");

		// Comprobamos que los campos no esten vacios, misma contraseña y sea mayor a 6
		if (password == null || passwordNew == null || passwordNewConfirm == null || passwordNew.length() < 6
				|| passwordNewConfirm.length() < 6) {
			request.setAttribute("status", "fill");
			dispatcher.forward(request, response);
			return;
		}

		Usuario userFromSession = (Usuario) session.getAttribute("usuario");

		if (!userFromSession.getPassword().equals(password)) {
			request.setAttribute("status", "equals");
			dispatcher.forward(request, response);
			return;
		}

		userFromSession.setPassword(passwordNewConfirm);
		Usuario merged = UsuarioDaoImpl.updatePassword(userFromSession);

		if (merged == null) {
			request.setAttribute("status", "error");
		} else {
			request.setAttribute("status", "success");
		}

		dispatcher.forward(request, response);
	}

}
