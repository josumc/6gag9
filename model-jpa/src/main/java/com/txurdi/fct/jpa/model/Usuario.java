package com.txurdi.fct.jpa.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;
import com.txurdi.fct.jpa.defaultenum.DefaultEnumString;


/**
 * Clase objeto para controlar y gestionar los usuarios
 * 
 * @author luiokx
 * @author jmoran 
 */

@Entity
@Table
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_usuario")
	private long id_usuario;
	
	@NotNull
	@Column(unique = true)
	@Size(min = 2, max = 150, message = "El email debe estar entre 2 y 255 caracteres")
	private String email;
	
	@Column
	@NotNull
	@Size(min = 2, max = 255, message = "La contraseña debe estar entre 2 y 255 caracteres")
	private String password;

	@Column
	@NotNull
	private  int estado;

	@Column (name = "tipo")
	@NotNull
	private  int tipo;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private Set<Publicacion> publicaciones;
	
	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
	private Set<Likes> likes;
	
	public Usuario() {
		super();
		this.email = DefaultEnumString.EMPTY.getValue();
		this.password = DefaultEnumString.EMPTY.getValue();
		this.estado = DefaultEnumInteger.DEFECTO.getValue();
		this.tipo = DefaultEnumInteger.VALIDO.getValue();
	}
	
	/**
	 * Constructor general para crear usuarios
	 * @param email
	 * @param password
	 */
	public Usuario(String email, String password) {
		super();
		this.email = email;
		this.password = password;
		this.estado =  DefaultEnumInteger.DEFECTO.getValue();
		this.tipo = DefaultEnumInteger.VALIDO.getValue();
	}
	
	/**
	 * Devuelve la id del usuario
	 * 
	 * @return int
	 */
	public long getId() {
		return this.id_usuario;
	}
	
	/**
	 * Utilizar unicamente los siguientes enum
	 * Modifica si el usuario fue eliminado o validado
	 * 
	 * @param DefaultEnumInteger - NOT_INIT
	 * 							 - DEFECTO
	 * 							 - VALIDO
	 * 							 - ELIMINADO
	 * 							
	 * @see DefaultEnumInteger
	 */
	public void setEstado(DefaultEnumInteger defaultEnumInteger) {
		this.estado = defaultEnumInteger.getValue();
	}
	
	/**
	 * Devuelve el estado actual del usuario
	 * 
	 * @return int
	 */
	public int getEstado() {
		return this.estado;
	}
	
	/**
	 * Utilizar unicamente los siguientes enum
	 * Metodo para cambiar la clase de usuario
	 * 
	 * @param DefaultEnumInteger - DEFECTO
	 * 							 - ADMINISTRADOR
	 * 							 - ELIMINADO
	 * 							
	 * @see DefaultEnumInteger
	 */
	public void setTipo(DefaultEnumInteger defaultEnumInteger) {
		this.tipo = defaultEnumInteger.getValue();
	}
	
	/**
	 * Devuelve el tipo del usuario
	 * 
	 * @return int
	 */
	public int getTipo() {
		return this.tipo;
	}
	
	/**
	 * Funcion para devolver el email
	 * 
	 * @return String
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Funcion para devolver el email
	 * 
	 * @return String
	 */
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
