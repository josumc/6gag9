package com.txurdi.fct.restfull;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.txurdi.fct.jpa.dao.PublicacionDaoImpl;
import com.txurdi.fct.jpa.model.Publicacion;

@RestController
@RequestMapping("/api/publicacion")
public class PublicacionController {
	
	
	/**
	 * Solo devuelve publicaciones validas
	 * 
	 * @return
	 */
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost")
	public List<Publicacion> getAll() {
		return  PublicacionDaoImpl.findAll();
	}
	
	@GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost")
	public Publicacion getRandom() {
		return PublicacionDaoImpl.getRandomPublicacion();
	}

	
	@GetMapping(value = "search", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost")
	public List<Publicacion> getBySearch(@RequestParam("descripcion") String descripcion) {
		return PublicacionDaoImpl.getFromDescription(descripcion);
	}
}