package com.txurdi.fct.jpa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Clase objeto para gestionar los likes de las publicaciones
 * 
 * @author luiokx
 * @author jmoran
 */

@Entity
@Table
public class Likes implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_like")
	private int id_like;

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_publicacion", nullable = false)
	private Publicacion publicacion;

	/**
	 * Constructor por defecto para inicializar la clase
	 */
	public Likes() {
		super();
		this.publicacion = new Publicacion();
		this.usuario = new Usuario();
	}
	
	public Likes(Usuario user, String id_publicacion) {
		super();
		this.publicacion = new Publicacion(id_publicacion);
		this.usuario = user;
	}
	
	public Likes(int id_like, Usuario user, String id_publicacion) {
		super();
		this.id_like = id_like;
		this.publicacion = new Publicacion(id_publicacion);
		this.usuario = user;
	}
	
	/**
	 * Constructor para inicializar la clase
	 * 
	 * @param id_publicacion
	 * @param id_usuario
	 */
	public Likes(Publicacion id_publicacion, Usuario id_usuario) {
		super();
		this.publicacion = id_publicacion;
		this.usuario = id_usuario;
	}
	
	/**
	 * Funcion que devuelve el id del like
	 * 
	 * @return int
	 */
	public int getId_like() {
		return this.id_like;
	}
	
	/**
	 * Metodo para cambiar el valor del id
	 * 
	 * @param int
	 */
	public void setId_like(int id_like) {
		this.id_like = id_like;
	}

	/**
	 * Funcion para devolver el id de usuario
	 * 
	 * @return int
	 */
	public long getId_user() {
		return usuario.getId();
	}

	/**
	 * Metodo para establecer el id del usuario para añadir el like
	 * 
	 * @param id_usuario
	 */
	public void setId_user(Usuario id_usuario) {
		this.usuario = id_usuario;
	}

	/**
	 * Funcion para devolver el id de usuario
	 * 
	 * @return int
	 */
	public int getIdP_ublicacion() {
		return this.publicacion.getId();
	}

	/**
	 * Metodo para establecer el id de la publicacion para añadir el like
	 * 
	 * @param id_publicacion
	 */
	public void setId_publicacion(Publicacion id_publicacion) {
		this.publicacion = id_publicacion;
	}
}