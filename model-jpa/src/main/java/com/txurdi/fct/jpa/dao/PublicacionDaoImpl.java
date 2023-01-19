package com.txurdi.fct.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;
import com.txurdi.fct.jpa.model.Publicacion;

public class PublicacionDaoImpl {

	public static void addPublicacion(Publicacion publicacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(publicacion);
		em.getTransaction().commit();
		
		em.close();
	}
	
	public static List<Publicacion> findAll() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		List<Publicacion> items = (List<Publicacion>) em.createQuery("FROM Publicacion p WHERE p.estado = " + DefaultEnumInteger.VALIDO.getValue()).getResultList();
		
		em.close();
		
		return items;
	}
	
	public static Publicacion findPublicacionWithId(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery("FROM Publicacion p WHERE p.id_publicacion = " + id);
		query.setMaxResults(1);
		
		Publicacion response = (Publicacion) query.getSingleResult();

		em.close();		
		
		return response;
	}
	
	public static List<Publicacion> getPublicacionNoAdmitidas() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		List<Publicacion> items = null;
		
		try {
			
			Query query = em.createQuery("FROM Publicacion p WHERE p.estado = " + DefaultEnumInteger.DEFECTO.getValue() );
			
			items = (List<Publicacion>) query.getResultList();
			
		} catch(NoResultException e) {
			em.close();
			e.printStackTrace();
		}
		
		return items;
	}
	
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
	
	public static List<Publicacion> getPublicacionesFromUser(long id_user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		List<Publicacion> items = null;
		
		try {
			Query query = em.createQuery("FROM Publicacion p WHERE p.usuario = " + id_user);
			
			items = (List<Publicacion>) query.getResultList();
		} catch(NoResultException e) {
			em.close();
			e.printStackTrace();
		}
		
		return items;
	}
	
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
			Query query = em.createQuery("FROM Publicacion p WHERE p.descripcion LIKE '%" + description + "%'");
			
			items = (List<Publicacion>) query.getResultList();
			
		} catch(NoResultException e) {
			em.close();
			e.printStackTrace();
		}
		
		return items;
	}
	
	public static Publicacion getRandomPublicacion() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		Query countQuery = em.createQuery("FROM Publicacion ORDER BY RAND()");
		countQuery.setMaxResults(1);
		
		Publicacion response = (Publicacion) countQuery.getSingleResult();
		
		em.close();
		
		return response;
	}
}
