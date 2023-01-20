package com.txurdi.fct.jpa.dao;

import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;
import com.txurdi.fct.jpa.model.Usuario;

public class UsuarioDaoSql {
	
	/**
	 * Query para buscar el usuario por contraseña y email
	 * 
	 * @param Usuario
	 * @return String
	 */
	protected static String getFindUserQuery(Usuario usuario) {
		return "SELECT u FROM Usuario u WHERE u.email = '" + usuario.getEmail() + "' AND u.password = '" + usuario.getPassword() + "' AND u.estado = " + DefaultEnumInteger.VALIDO.getValue();
	}
	
	/**
	 * Query que te devuelven si el usuario fue registrado
	 * 
	 * @param Usuario
	 * @return String
	 */
	protected static String getFindUserQueryRegister(Usuario usuario) {
		return "SELECT u FROM Usuario u WHERE u.email = '" + usuario.getEmail() + "'";
	}
	
	/**
	 * Query que devuelven usuarios no admitidos
	 * 
	 * @return String
	 */
	protected static String getUsuariosNoAdmitidasQuery() {
		return "FROM Usuario u WHERE u.estado = " + DefaultEnumInteger.DEFECTO.getValue();
	}
}