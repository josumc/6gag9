package com.txurdi.fct.jpa.defaultenum;

/**
 * Valores unicos para datos Integer en especifico
 * 
 * @author luiokx
 * @author jmoran
 */
public enum DefaultEnumInteger {
	/**
	 * Para cuando la variable no se ha inicializado
	 */
	NOT_INIT(4), 
	
	/**
	 * Para cuando la variable se ha inicializado a traves por contructor por defecto
	 */
	DEFECTO(0), 
	
	/**
	 * Para cuando necesitemos una variable que indique que un dato fue admitido
	 */
	VALIDO(1), 
	
	/**
	 * Variable para indicar que un usuario es administrador
	 */
	ADMINISTRADOR(9), 
	
	/**
	 * Variable para indicar un eliminado logico de algun campo
	 */
	ELIMINADO(2);
	
	private final int value;
	
	private DefaultEnumInteger(int value) {
		this.value = value;
	}
	
	/**
	 * Devuelve el valor Integer del Enum
	 * 
	 * @return value
	 */
	public int getValue() {
        return this.value;
    } 
}