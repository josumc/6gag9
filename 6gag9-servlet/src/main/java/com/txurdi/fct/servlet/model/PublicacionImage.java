package com.txurdi.fct.servlet.model;

import java.util.Base64;

import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;
import com.txurdi.fct.jpa.model.Publicacion;

/**
 * Clase unica de la web para gestionar las publicaciones<br>
 * Contiene si el usuario le ha dado like o no
 * 
 * @author luiokx
 * @author josumc
 */
public class PublicacionImage extends Publicacion {
	private static final long serialVersionUID = 1L;
	private int like;
	private String imageDecoded;
	private int id_like;
	
	public PublicacionImage() {
		super();
	}
	
	public PublicacionImage(com.txurdi.fct.jpa.model.Publicacion publicacion, int like) {
		super();
		super.setId_publicacion(publicacion.getId());
		super.setEstado(DefaultEnumInteger.VALIDO);
		super.setDescripcion(publicacion.getDescripcion());
		super.setFoto(publicacion.getFoto());
		this.imageDecoded = decodeImage();
		this.like = like;
	}
	
	/**
	 * Devuelve el String base64 de la imagen
	 * 
	 * @return String
	 */
	public String getImageDecoded() {
			
		return this.imageDecoded;
	}
	
	/**
	 * Metodo para devolver el estado del like
	 * 
	 * @return
	 */
	public int getLike() {
		return this.like;
	}
	
	/**
	 * Metodo para cambiar el estado del like
	 * 
	 * @param like
	 */
	public void setLike(int like) {
		this.like = like;
	}
	
	/**
	 * Funcion que devuelve el id del like
	 * 
	 * @return int
	 */
	public int getId_like() {
		return id_like;
	}

	/**
	 * Metodo para cambiar el id del like
	 * 
	 * @param id_like
	 */
	public void setId_like(int id_like) {
		this.id_like = id_like;
	}

	/**
	 * Decodifica la imagen de el objeto padre<br>
	 * la transforma en base64 para que la web pueda<br>
	 * interactuar e interpretarla
	 *
	 * @return String
	 */
	private String decodeImage() {
		String response = "";
			
		response = Base64.getEncoder().encodeToString(super.getFoto());
			
		return response;
	}
}