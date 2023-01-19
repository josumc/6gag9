package com.txurdi.fct.jpa.dao;

import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;
import com.txurdi.fct.jpa.model.Usuario;

public class UsuarioDaoSql {
	public static String ADD_USUARIO = "INSERT INTO Usuario (email, password, estado, tipo) VALUES (?, ?, ?, ?)";
	public static String VALIDAR_USUARIO = "UPDATE Usuario SET estado = " + DefaultEnumInteger.VALIDO + " WHERE id_usuario = ?";
	public static final String BORRADO_LOGICO = "UPDATE Usuario SET estado = " + DefaultEnumInteger.ELIMINADO + " WHERE id_usuario = ?";
	public static final String FIND_USER = "SELECT u FROM Usuario u WHERE u.email = a AND u.password = b";
	
	public static final String getFindUserQuery(Usuario usuario) {
		return "SELECT u FROM Usuario u WHERE u.email = '" + usuario.getEmail() + "' AND u.password = '" + usuario.getPassword() + "' AND u.estado = " + DefaultEnumInteger.VALIDO.getValue();
	}
	
	public static final String getFindUserQueryRegister(Usuario usuario) {
		return "SELECT u FROM Usuario u WHERE u.email = '" + usuario.getEmail() + "' AND u.password = '" + usuario.getPassword() + "'";
	}
}