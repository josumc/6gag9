package com.txurdi.fct.jpa.dao;

import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;

/**
 * Querys para gestionar las publicaciones
 * 
 * @author luiokx
 * @author jcmoran
 */
public class PublicacionDaoSql {
	protected static final String QUERY_RANDOM = "FROM Publicacion ORDER BY RAND()";
	
	/**
	 * Query para obtener una lista de las publicaciones no admitidas
	 * 
	 * @return String
	 */
	protected static String getPublicacionNoAdmitidasQuery() {
		return "FROM Publicacion p WHERE p.estado = " + DefaultEnumInteger.DEFECTO.getValue();
	}
	
	/**
	 * Query para devolver las publicaciones de un usuario
	 * 
	 * @param long
	 * @return String
	 */
	protected static String getPublicacionesFromUserQuery(long id_user) {
		return "FROM Publicacion p WHERE p.usuario = " + id_user;
	}
	
	/**
	 * Query para buscar las publicaciones validas
	 * 
	 * @return String
	 */
	protected static String getFindAllQuery() {
		return "FROM Publicacion p WHERE p.estado = " + DefaultEnumInteger.VALIDO.getValue() + " ORDER BY p.id_publicacion DESC";
	}
	
	/**
	 * Query para buscar publicacion por id
	 * 
	 * @param int
	 * @return String
	 */
	protected static String findByIdQuery(int id) {
		return "FROM Publicacion p WHERE p.id_publicacion = " + id;
	}
	
	/**
	 * Query para buscar una lista de publicaciones por busqueda <br>
	 * de descripcion
	 * 
	 * @param String
	 * @return String
	 */
	protected static String getFromDescripcionQuery(String description) {
		return "FROM Publicacion p WHERE p.descripcion LIKE '%" + description + "%'";
	}
}