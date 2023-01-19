package com.txurdi.fct.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.txurdi.fct.jpa.model.Likes;
import com.txurdi.fct.jpa.model.Publicacion;
import com.txurdi.fct.jpa.model.Usuario;

public class LikeDaoImpl  {
	public static void removeLike(Usuario usuario, String publicacion, String id_like) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Likes row = new Likes(Integer.parseInt(id_like), usuario, publicacion);
		
		em.remove(em.contains(row) ? row : em.merge(row));
		
		em.getTransaction().commit();
		em.close();
	}
	
	
	
	public static void addLike(Usuario usuario, String publicacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		Likes row = new Likes(usuario, publicacion);
		
		em.getTransaction().begin();
		em.persist(row);
		em.getTransaction().commit();
		
		em.close();
	}
	
	public static int getCountLikesOfImages(Publicacion publicacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNativeQuery(LikeDaoSql.COUNT_LIKE);
		
		int response = (int) query.setParameter(1, publicacion.getId())
			.getSingleResult();	
		
		em.close();
		
		return response;
	}
	
	public static Likes getFromPublicacionWithUser(Usuario user, Publicacion publicacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		Likes items = null;
		
		try {
			Query query = em.createQuery("FROM Likes l WHERE l.usuario = " + user.getId() + " AND l.publicacion = " + publicacion.getId());
			query.setMaxResults(1);
			items = (Likes) query.getSingleResult();
			
		} catch(NoResultException e) {
			em.close();
			e.printStackTrace();
		}
		
		em.close();
		
		return items;
	}
	
	public static List<Likes> getAllLikesFromUser(Usuario user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		List<Likes> items = (List<Likes>) em.createQuery("FROM Likes l WHERE l.usuario = " + user.getId()).getResultList();
		
		em.close();
		
		return items;
	}
}
