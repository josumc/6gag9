package com.txurdi.fct.servlet;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.txurdi.fct.jpa.dao.PublicacionDaoImpl;
import com.txurdi.fct.jpa.model.Usuario;

/**
 * Servlet para gestionar el upload y transformar el codigo base64 de la imagen a bytes
 *
 * @author luiokx
 * @author josumc
 */
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String imageString = request.getParameter("dropedText").replace("data:image/jpeg;base64,", "");
    	String description = request.getParameter("description");
    	
    	byte[] decodedBytes = Base64.getDecoder().decode(imageString);
    	
    	
    	HttpSession session = request.getSession();
    	Usuario user = (Usuario) session.getAttribute("usuario");
    	
    	com.txurdi.fct.jpa.model.Publicacion publicacion = new com.txurdi.fct.jpa.model.Publicacion(decodedBytes, description, user);
    	PublicacionDaoImpl.addPublicacion(publicacion);
    	response.sendRedirect("./Inicio");
    }
}
