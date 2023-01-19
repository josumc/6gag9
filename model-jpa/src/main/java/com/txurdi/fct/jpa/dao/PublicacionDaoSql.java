package com.txurdi.fct.jpa.dao;

import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;

/**
 * Querys para gestionar las publicaciones
 * 
 * @author luiokx
 * @author jcmoran
 */
public class PublicacionDaoSql {
	public static final String ADD_PUBLICACION = "INSERT INTO Publicacion(foto, descripcion, genero, estado, id_usuario) VALUES (?, ?, ?, ?, ?)";
	public static final String VALIDAR_PUBLICACION = "UPDATE Publicacion SET estado = " + DefaultEnumInteger.VALIDO + " WHERE id = ?";
	public static final String BORRADO_LOGICO = "UPDATE Publicacion SET estado = " + DefaultEnumInteger.ELIMINADO + " WHERE id = ?";
}