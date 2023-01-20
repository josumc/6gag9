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

/**
 * Modelo para gestionar la tabla like
 * 
 * @author luiokx
 * @author josumc
 */
public class LikeDaoImpl  {
	/**
	 * Metodo para remover un like en una publicacion
	 * 
	 * @param Usuario
	 * @param Publicacion
	 */
	public static void removeLike(Usuario usuario, String publicacion, String id_like) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Likes row = new Likes(Integer.parseInt(id_like), usuario, publicacion);
		
		em.remove(em.contains(row) ? row : em.merge(row));
		
		em.getTransaction().commit();
		em.close();
	}
	
	/**
	 * Metodo para agregar un like en una publicacion
	 * 
	 * @param Usuario
	 * @param Publicacion
	 */
	public static void addLike(Usuario usuario, String publicacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		Likes row = new Likes(usuario, publicacion);
		
		em.getTransaction().begin();
		em.persist(row);
		em.getTransaction().commit();
		
		em.close();
	}
	
	/**
	 * Funcino que devuelve la cantidad de likes de una publicacion
	 * 
	 * @param Publicacion
	 * @return int
	 */
	public static int getCountLikesOfImages(Publicacion publicacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNativeQuery(LikeDaoSql.COUNT_LIKE);
		
		int response = (int) query.setParameter(1, publicacion.getId())
			.getSingleResult();	
		
		em.close();
		
		return response;
	}
	
	/**
	 * Funcino que devuelve un like de una publicacion en<br>
	 * funcion de cada usuario
	 * 
	 * @param Usuario
	 * @param Publicacion
	 * @return Likes
	 */
	public static Likes getFromPublicacionWithUser(Usuario user, Publicacion publicacion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		Likes items = null;
		
		try {
			Query query = em.createQuery(LikeDaoSql.getFromPublicacionWithUserQuery(user.getId(), publicacion.getId()));
			query.setMaxResults(1);
			items = (Likes) query.getSingleResult();
			
		} catch(NoResultException e) {
			em.close();
			e.printStackTrace();
		}
		
		em.close();
		
		return items;
	}
	
	/**
	 * Funcion que devuelve una lista de likes que pertenecen a un usuario
	 * 
	 * @param user
	 * @return List<Likes>
	 */
	public static List<Likes> getAllLikesFromUser(Usuario user) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		List<Likes> items = (List<Likes>) em.createQuery(LikeDaoSql.getAllLikesFromUserQuery(user.getId())).getResultList();;
		
		em.close();
		
		return items;
	}
}
