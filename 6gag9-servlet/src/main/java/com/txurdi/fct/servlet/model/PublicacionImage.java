package com.txurdi.fct.servlet.model;




import java.util.Base64;

import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;
import com.txurdi.fct.jpa.model.Publicacion;


public class PublicacionImage extends Publicacion {
	private int like;
	private String imageDecoded;
	private int id_like;
	
	public PublicacionImage() {
		super();
	}
	
	public PublicacionImage(com.txurdi.fct.jpa.model.Publicacion publicacion, int like) {
		super();
		super.setId_publicacion(publicacion.getId_publicacion());
		super.setEstado(DefaultEnumInteger.VALIDO);
		super.setDescripcion(publicacion.getDescripcion());
		super.setFoto(publicacion.getFoto());
		this.imageDecoded = decodeImage();
		this.like = like;
	}
	
	public String getImageDecoded() {
			
		return this.imageDecoded;
	}
	
	public int getLike() {
		return this.like;
	}
	
	public void setLike(int like) {
		this.like = like;
	}
	
	
	
	public int getId_like() {
		return id_like;
	}

	public void setId_like(int id_like) {
		this.id_like = id_like;
	}

	private String decodeImage() {
		String response = "";
		
	
			
		response = Base64.getEncoder().encodeToString(super.getFoto());
		
		
		return response;
	}
}