package com.txurdi.fct.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.txurdi.fct.jpa.dao.LikeDaoImpl;
import com.txurdi.fct.jpa.dao.PublicacionDaoImpl;
import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;
import com.txurdi.fct.jpa.model.Likes;
import com.txurdi.fct.jpa.model.Usuario;
import com.txurdi.fct.servlet.model.PublicacionImage;

/**
 * Servlet para realizar la busqueda de una publicacion por id
 * 
 * @author luiokx
 * @author josumc
 */
public class Busqueda extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Busqueda() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id_publicacion = Integer.parseInt(request.getParameter("id_publicacion"));
			Usuario user = (Usuario) request.getSession().getAttribute("usuario");
			PublicacionImage publicacion = getImageWithId(id_publicacion, user);
			
			request.getSession().setAttribute("publicacion", publicacion);
			
			RequestDispatcher rd = request.getRequestDispatcher("./view/busqueda.jsp");
			rd.forward(request, response);
		} catch(NumberFormatException ne) {
			RequestDispatcher rd = request.getRequestDispatcher("./view/busqueda.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * Funcion que devuelve PublicacionImage para saber el like del usuario
	 * @param id
	 * @param user
	 * @return
	 */
	private PublicacionImage getImageWithId(int id, Usuario user) {
		com.txurdi.fct.jpa.model.Publicacion publi = PublicacionDaoImpl.findPublicacionWithId(id);
		
		PublicacionImage response = null;
		
		try {
			response = new PublicacionImage(publi, DefaultEnumInteger.DEFECTO.getValue());
			
			Likes likesFromUser = null;
			if (user != null) {
				likesFromUser = LikeDaoImpl.getFromPublicacionWithUser(user,publi);
			}
			
			if(likesFromUser != null) {
				response.setLike(DefaultEnumInteger.VALIDO.getValue());
			} else {
				response.setLike(DefaultEnumInteger.DEFECTO.getValue());
			}
		} catch(NullPointerException s) {
			
		}
		
		return response;
	}
}
