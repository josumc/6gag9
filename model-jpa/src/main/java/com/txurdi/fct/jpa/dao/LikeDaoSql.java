package com.txurdi.fct.jpa.dao;

/**
 * Querys que gestionan los likes
 * @author luiok
 * @author jcmoran
 */
public class LikeDaoSql {
	/**
	 * Query para devolver el count del like de una publicacion
	 */
	public static String COUNT_LIKE = "SELECT COUNT(id) FROM Like WHERE id_publicacion = ?";

	/**
	 * Query usuario para devolver la publicacion con el usuario
	 * 
	 * @param id_user
	 * @param id_publicacion
	 * @return String
	 */
	protected static String getFromPublicacionWithUserQuery(long id_user, long id_publicacion) {
		return "FROM Likes l WHERE l.usuario = " + id_user + " AND l.publicacion = " + id_publicacion;
	}
	
	/**
	 * Query para devolver todo los likes del usuario
	 * 
	 * @param id
	 * @return
	 */
	protected static String getAllLikesFromUserQuery(long id) {
		return "FROM Likes l WHERE l.usuario = " + id;
	}
}