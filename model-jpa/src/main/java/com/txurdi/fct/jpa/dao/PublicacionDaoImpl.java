package com.txurdi.fct.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;
import com.txurdi.fct.jpa.model.Publicacion;

/**
 * Gestor de la tabla publicacion
 * 
 * @author luiokx
 * @author josumc
 */
public class PublicacionDaoImpl {
	/***
	 * Metodo para añadir una publicacion
	 * 
	 * @param Publicacion
	 */
	public static void addPublicacion(Publicacion publicacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(publicacion);
		em.getTransaction().commit();
		
		em.close();
	}
	
	/**
	 * Funcion para devolver todas las publicaciones
	 * 
	 * @return List<Publicacion>
	 */
	public static List<Publicacion> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		List<Publicacion> items = (List<Publicacion>) em.createQuery(PublicacionDaoSql.getFindAllQuery()).getResultList();
		
		em.close();
		
		return items;
	}
	
	/**
	 * Funcion para devolver una publicacion por ID
	 * 
	 * @param id
	 * @return Publicacion
	 */
	public static Publicacion findPublicacionWithId(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery(PublicacionDaoSql.findByIdQuery(id));
		query.setMaxResults(1);
		
		Publicacion response = (Publicacion) query.getSingleResult();

		em.close();		
		
		return response;
	}
	
	/**
	 * Funcion para devolver una lista de publicaciones no admitidas
	 * 
	 * @return List<Publicacion>
	 */
	public static List<Publicacion> getPublicacionNoAdmitidas() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		List<Publicacion> items = null;
		
		try {
			
			Query query = em.createQuery(PublicacionDaoSql.getPublicacionNoAdmitidasQuery());
			
			items = (List<Publicacion>) query.getResultList();
			
		} catch(NoResultException e) {
			em.close();
			e.printStackTrace();
		}
		
		return items;
	}
	
	/**
	 * Metodo para el admin para que el admin pueda validar una publicacion
	 *
	 * @param int
	 */
	public static void validarEstado(int publicacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Publicacion row = em.find(Publicacion.class, publicacion);
		row.setEstado(DefaultEnumInteger.VALIDO);
		
		em.merge(row);
		em.getTransaction().commit();
		
		em.close();
	}
	
	/**
	 * Funcion para devolver publicaciones de un usuario
	 * 
	 * @param id_user
	 * @return List<Publicacion>
	 */
	public static List<Publicacion> getPublicacionesFromUser(long id_user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		List<Publicacion> items = null;
		
		try {
			Query query = em.createQuery(PublicacionDaoSql.getPublicacionesFromUserQuery(id_user));
			
			items = (List<Publicacion>) query.getResultList();
		} catch(NoResultException e) {
			em.close();
			e.printStackTrace();
		}
		
		return items;
	}
	
	/**
	 * Funcion para hacer el borrado logico de una publicacion
	 * 
	 * @param publicacion
	 */
	public static void borradoLogico(int publicacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Publicacion row = em.find(Publicacion.class, publicacion);
		row.setEstado(DefaultEnumInteger.ELIMINADO);
		
		em.merge(row);
		em.getTransaction().commit();
		
		em.close();
	}
	
	public static List<Publicacion> getFromDescription(String description) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		List<Publicacion> items = null;
		
		try {
			Query query = em.createQuery(PublicacionDaoSql.getFromDescripcionQuery(description));
			
			items = (List<Publicacion>) query.getResultList();
			
		} catch(NoResultException e) {
			em.close();
			e.printStackTrace();
		}
		
		return items;
	}
	
	/**
	 * Funcion para devolver una publicacion aleatoria
	 * 
	 * @return Publicacion
	 */
	public static Publicacion getRandomPublicacion() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		Query countQuery = em.createQuery(PublicacionDaoSql.QUERY_RANDOM);
		countQuery.setMaxResults(1);
		
		Publicacion response = (Publicacion) countQuery.getSingleResult();
		
		em.close();
		
		return response;
	}
}
