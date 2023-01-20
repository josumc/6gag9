package com.txurdi.fct.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.txurdi.fct.jpa.dao.LikeDaoImpl;
import com.txurdi.fct.jpa.model.Usuario;

/**
 * Servlet para controlar los likes de una pagina
 * 
 * @author luiokx
 * @author josumc
 */
public class LikePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		String id_publicacion = request.getParameter("id_publicacion");
		String id_like = request.getParameter("id_like");
		String method = request.getParameter("method_like");
		
		if(method.equals("no")) {
			LikeDaoImpl.addLike(usuario, id_publicacion);
		} else {
			LikeDaoImpl.removeLike(usuario, id_publicacion, id_like);
		}
		
		response.sendRedirect("./Inicio");
		
	}
}
