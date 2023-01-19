package com.txurdi.fct.jpa.defaultenum;

/**
 * Valores unicos para datos String en especifico
 * 
 * @author luiok
 * @author jmoran
 */
public enum DefaultEnumString {
	/**
	 * Para inicializar un string
	 */
	EMPTY("EMPTY_BLOCK");
	private final String value;
	
	private DefaultEnumString(String value) {
		this.value = value;
	}
	
	/**
	 * Devuelve el valor String del Enum
	 * 
	 * @return value
	 */
	public String getValue() {
        return this.value;
    } 
}