package com.txurdi.fct.jpa.dao;

/**
 * Querys que gestionan los likes
 * @author luiok
 * @author jcmoran
 */
public class LikeDaoSql {
	public static final String ADD_LIKE = "INSERT INTO Like(id_user, id_publicacion) VALUES (?,?)";
	public static final String REMOVE_LIKE = "DELETE FROM Likes WHERE id_user = ? AND id_publicacion = ?";
	public static final String COUNT_LIKE = "SELECT COUNT(id) FROM Like WHERE id_publicacion = ?";
}