package com.txurdi.fct.servlet.cuenta;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.txurdi.fct.jpa.dao.PublicacionDaoImpl;
import com.txurdi.fct.jpa.dao.UsuarioDaoImpl;
import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;
import com.txurdi.fct.jpa.model.Publicacion;
import com.txurdi.fct.jpa.model.Usuario;

/**
 * Servlet para controlar las funciones de administrador
 * @author luiokx
 * @author josumc
 */
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("usuario") == null) {
			response.sendRedirect("./Inicio");
			return;
		}
		
		if(((Usuario) request.getSession().getAttribute("usuario")).getTipo() != DefaultEnumInteger.ADMINISTRADOR.getValue()) {
			response.sendRedirect("./Inicio");
			return;
		}
		
		List<Publicacion> publicaciones = PublicacionDaoImpl.getPublicacionNoAdmitidas();
		List<Usuario> usuarios = UsuarioDaoImpl.getUsuariosNoAdmitidos();
		
		request.getSession().setAttribute("publicaciones", publicaciones);
		request.getSession().setAttribute("usuarios", usuarios);
		RequestDispatcher rd = request.getRequestDispatcher("./../view/admin.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("usuario") == null) {
			response.sendRedirect("./../Inicio");
			return;
		}
		
		String action = request.getParameter("action");
		
		//En caso de editar
		if(action.equals("edit")) {
			String id_publicacion = request.getParameter("idPublicacion");
			String estado = request.getParameter("estado");
			
			//Si no ha cambiado el estado
			if(estado.equals(String.valueOf(DefaultEnumInteger.DEFECTO.getValue()))) {
				doGet(request, response);
				return;
			}
			
			PublicacionDaoImpl.validarEstado(Integer.parseInt(id_publicacion));
			
		} else if(action.equals("delete")) {
			String id_publicacion = request.getParameter("btnWithId");
			
			PublicacionDaoImpl.borradoLogico(Integer.parseInt(id_publicacion));
		} else if(action.equals("editUsuario")) {
			//Editar usuario
			String id_user = request.getParameter("idPublicacion");
			String estado = request.getParameter("estado");
			
			//Si no ha cambiado el estado
			if(estado.equals(String.valueOf(DefaultEnumInteger.DEFECTO.getValue()))) {
				doGet(request, response);
				return;
			}
			
			UsuarioDaoImpl.validarEstado(Long.parseLong(id_user));
			
		} else if(action.equals("deleteUsuario")) {
			//Borrado logico usuario
			String id_user = request.getParameter("btnWithId");
			UsuarioDaoImpl.borradoLogico(Long.parseLong(id_user));
		}
		
		
		doGet(request, response);
	}

}
