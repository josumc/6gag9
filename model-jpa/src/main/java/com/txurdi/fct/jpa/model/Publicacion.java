package com.txurdi.fct.jpa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;
import com.txurdi.fct.jpa.defaultenum.DefaultEnumString;

/**
 * Clase objeto para controlar las publicaciones
 * 
 * @author luiokx
 * @author jmoran
 */

@Entity
@Table
public class Publicacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_publicacion")
	private int id_publicacion;

	@NotNull
    @Lob
    @Column(length=500000)
    private byte[] foto;

	@Column
	@NotNull
	private String descripcion;

	@Column
	@NotNull
	private int estado;

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private Usuario usuario;

	@OneToMany(mappedBy = "publicacion", fetch = FetchType.EAGER)
	private Set<Likes> likes;

	/**
	 * Constructor por defecto para inicializar las variables
	 */
	public Publicacion() {
		super();
		this.foto = null;
		this.descripcion = DefaultEnumString.EMPTY.getValue();
		this.estado = DefaultEnumInteger.NOT_INIT.getValue();
		this.usuario = new Usuario();
	}
	
	public Publicacion(String id) {
		this.id_publicacion = Integer.parseInt(id);
	}
	
	/**
	 * Constructor para crear una nueva publicacion
	 * 
	 * @param foto
	 * @param descripcion
	 * @param genero
	 * @param estado
	 * @param id_usuario
	 */
	public Publicacion(byte[] foto, String descripcion, Usuario id_usuario) {
		this();
		this.foto = foto;
		this.descripcion = descripcion;
		this.estado = DefaultEnumInteger.DEFECTO.getValue();
		this.usuario = id_usuario;
	}

	/**
	 * Devuelve la foto de la publicacion
	 * 
	 * @return Blob
	 */
	public byte[] getFoto() {
		return this.foto;
	}

	/**
	 * Funcion que devuelve el valor de la descripcion
	 * 
	 * @return String
	 */
	public String getDescripcion() {
		return this.descripcion;
	}

	/**
	 * Funcion que devuelve el estado de la publicacion
	 * 
	 * @return int
	 */
	public int getEstado() {
		return this.estado;
	}
	
	/**
	 * Metodo para establecer el estado de la publicacion
	 * 
	 * @param DefaultEnumInteger
	 */
	public void setEstado(DefaultEnumInteger defaultEnumInteger) {
		this.estado = defaultEnumInteger.getValue();
	}
	
	/**
	 * Funcion para devolver el usuario relacionado con la publicacion
	 * 
	 * @return Usuario
	 */
	public Usuario getId_usuario() {
		return this.usuario;
	}
	
	/**
	 * Funcion para devolver los likes relacionados con la publicacion
	 * 
	 * @return Set<Likes>
	 */
	public Set<Likes> getLikes() {
		return likes;
	}
	

	/**
	 * Devuelve la id de publicacion
	 * 
	 * @return int
	 */
	public int getId() {
		return this.id_publicacion;
	}

	/**
	 * 
	 * Metodo para establecer el parametro de la foto
	 * 
	 * @param byte[]
	 */
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	/**
	 * Metodo para establecer la descripcion
	 * 
	 * @param String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Metodo para cambiar la propiedad del estado de la publicacion
	 * 
	 * @param int
	 */
	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	/**
	 * Metodo para establecer el id de la publicacion
	 * 
	 * @param int
	 */
	public void setId_publicacion(int id_publicacion) {
		this.id_publicacion = id_publicacion;
	}
}