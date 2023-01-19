package com.txurdi.fct.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.txurdi.fct.jpa.dao.UsuarioDaoImpl;
import com.txurdi.fct.jpa.model.Usuario;


/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("./view/register.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordC = request.getParameter("passwordC");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./view/register.jsp");
		
		//Controlamos los errores posibles
		//Campos vacios o nulos
		if(email == null || password == null || passwordC == null ||
				password.length() < 6 || passwordC.length() < 6) {
			//TODO controlar error
			request.setAttribute("status", "fill");
			dispatcher.forward(request, response);
			return;
		}
		
		//Comparar que las contraseñas sean iguales
		if(!password.equals(passwordC)) {
			//TODO controlar error
			request.setAttribute("status", "fill");
			dispatcher.forward(request, response);
			return;
		}
		
		//Comprobamos que no este duplicado
		Usuario usuarioForm = new Usuario(email, password);
		Usuario userDB = UsuarioDaoImpl.findUser(usuarioForm);
		
		//En caso de estar duplicado damos como error y no procedemos
		if(userDB != null && usuarioForm.getEmail().equals(userDB.getEmail())) {
			request.setAttribute("status", "duplicado");
			dispatcher.forward(request, response);
			return;
		}
		
		UsuarioDaoImpl.addUser(usuarioForm);
		
		//Comprobamos si fue insertado correctamente
		if(UsuarioDaoImpl.findUserRegistered(usuarioForm) != null) {
			request.setAttribute("status", "success");
		} else {
			request.setAttribute("status", "failed");
		}
		
		dispatcher.forward(request, response);
	}
}