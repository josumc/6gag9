package com.txurdi.fct.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.txurdi.fct.jpa.dao.LikeDaoImpl;
import com.txurdi.fct.jpa.dao.PublicacionDaoImpl;
import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;
import com.txurdi.fct.jpa.model.Likes;
import com.txurdi.fct.jpa.model.Usuario;
import com.txurdi.fct.servlet.model.PublicacionImage;

/**
 * Servlet implementation para devolver la pagina de inicio
 * 
 * @author luiokx
 * @author josumc
 */
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Inicio() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recuperamos los objetos como el usuario y las publicaciones
		HttpSession session = request.getSession();
		if (session == null) {
			session = request.getSession(true);
		}

		List<PublicacionImage> publicaciones = getImages(request);

		session.setAttribute("publicaciones", publicaciones);

		RequestDispatcher rd = request.getRequestDispatcher("./view/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private List<PublicacionImage> getImages(HttpServletRequest request) {
		List<com.txurdi.fct.jpa.model.Publicacion> AllItems = PublicacionDaoImpl.findAll();

		Usuario user = (Usuario) request.getSession().getAttribute("usuario");
		List<Likes> likesFromUser = null;
		if (user != null) {
			likesFromUser = LikeDaoImpl.getAllLikesFromUser((Usuario) request.getSession().getAttribute("usuario"));
		}

		List<PublicacionImage> items = new ArrayList<>();

		for (int i = 0; i < AllItems.size(); i++) {
			items.add(new PublicacionImage(AllItems.get(i), DefaultEnumInteger.DEFECTO.getValue()));
		}

		if (likesFromUser != null) {
			for (int i = 0; i < AllItems.size(); i++) {
				Set<Likes> like = AllItems.get(i).getLikes();

				for (Likes pos : like) {
					
					for(int x = 0; x < likesFromUser.size(); x++) {
						if(pos.getId_like() == likesFromUser.get(x).getId_like()) {
							items.get(i).setLike(DefaultEnumInteger.VALIDO.getValue());
							items.get(i).setId_like(likesFromUser.get(x).getId_like());
							break;
							
						}
					}
					
				}

			}
		}

		return items;
	}
}