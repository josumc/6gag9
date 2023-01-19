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
	public int getId_publicacion() {
		return id_publicacion;
	}

	public void setId_publicacion(int id_publicacion) {
		this.id_publicacion = id_publicacion;
	}

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

	// TODO DOCUMENTAR ESTOS METODOS EN PUBLICACION
	public String getDescripcion() {
		return this.descripcion;
	}


	public int getEstado() {
		return this.estado;
	}
	
	public void setEstado(DefaultEnumInteger defaultEnumInteger) {
		this.estado = defaultEnumInteger.getValue();
	}
	
	public Usuario getId_usuario() {
		return this.usuario;
	}
	
	public Set<Likes> getLikes() {
		return likes;
	}

	public void setLikes(Set<Likes> likes) {
		this.likes = likes;
	}

	/**
	 * Devuelve la id de publicacion
	 * 
	 * @return int
	 */
	public int getId() {
		return this.id_publicacion;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
}