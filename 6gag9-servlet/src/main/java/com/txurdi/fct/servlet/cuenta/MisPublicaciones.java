package com.txurdi.fct.servlet.cuenta;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.txurdi.fct.jpa.dao.PublicacionDaoImpl;
import com.txurdi.fct.jpa.model.Publicacion;
import com.txurdi.fct.jpa.model.Usuario;

/**
 * Servlet implementation class MisPublicaciones
 * @author luiokx
 * @author josumc
 */
public class MisPublicaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MisPublicaciones() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		if(user == null) {
			response.sendRedirect("./Inicio");
			return;
		}
		
		List<Publicacion> publicaciones = PublicacionDaoImpl.getPublicacionesFromUser(user.getId());
		request.getSession().setAttribute("publicaciones", publicaciones);
		
		RequestDispatcher rd = request.getRequestDispatcher("./../view/mis-publicaciones.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
