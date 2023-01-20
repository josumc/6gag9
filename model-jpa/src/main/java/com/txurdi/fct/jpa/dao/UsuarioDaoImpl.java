package com.txurdi.fct.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.txurdi.fct.jpa.defaultenum.DefaultEnumInteger;
import com.txurdi.fct.jpa.model.Usuario;

/**
 * Clase controladora de la tabla usuarios
 * 
 * @author luiokx
 * @author josumc
 */
public class UsuarioDaoImpl {
	/**
	 * Metodo para añadir un usuario segun los parametros establecidos por el usuario
	 * 
	 * @param Usuario
	 */
	public static void addUser(Usuario usuario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
		
		em.close();
	}
	
	/**
	 * Metodo para actualizar la contraseña
	 * 
	 * @param Usuario
	 * @return
	 */
	public static Usuario updatePassword(Usuario usuario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Usuario row = em.find(Usuario.class, usuario.getId());
		row.setPassword(usuario.getPassword());
		
		Usuario response = em.merge(row);
		em.getTransaction().commit();
		
		em.close();
		
		return response;
	}
	
	/**
	 * Metodo administrador para cambiar el estado de un usuario a borrado
	 * 
	 * @param Usuario
	 */
	public static void borradoLogico(long usuario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Usuario row = em.find(Usuario.class, usuario);
		row.setEstado(DefaultEnumInteger.ELIMINADO);
		
		em.merge(row);
		em.getTransaction().commit();
		
		em.close();
	}

	/**
	 * Metodo para validar el estado de un usuario
	 * 
	 * @param Usuario
	 */
	public static void validarEstado(long usuario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		
		Usuario row = em.find(Usuario.class, usuario);
		row.setEstado(DefaultEnumInteger.VALIDO);
		
		em.merge(row);
		em.getTransaction().commit();
		
		em.close();
	}
	
	/**
	 * Funcion que te devuelve el usuario completo cuando intente hacer login
	 * 
	 * @param Usuario
	 * @return Usuario
	 */
	public static Usuario findUser(Usuario usuario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery(UsuarioDaoSql.getFindUserQuery(usuario));
		
		Usuario usu = null;
		
		try {
			usu = (Usuario) query.getSingleResult();
		
		} catch(NoResultException nulls) {
			em.close();
			return null;
		}
		
		em.close();
		
		return usu;
	}
	
	/**
	 * Funcion que te comprueba que el usuario ha sido registrado
	 * 
	 * @param Usuario
	 * @return
	 */
	public static Usuario findUserRegistered(Usuario usuario) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery(UsuarioDaoSql.getFindUserQueryRegister(usuario));
		
		Usuario usu = null;
		
		try {
			usu = (Usuario) query.getSingleResult();
		
		} catch(NoResultException nulls) {
			em.close();
			return null;
		}
		
		em.close();
		
		return usu;
	}
	
	/**
	 * Funcion para devolver los usuarios no admitidos
	 * 
	 * @return List<Usuario>
	 */
	public static List<Usuario> getUsuariosNoAdmitidos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_sixgagnine");
		EntityManager em = emf.createEntityManager();
		
		List<Usuario> items = null;
		
		try {
			
			Query query = em.createQuery(UsuarioDaoSql.getUsuariosNoAdmitidasQuery());
			
			items = (List<Usuario>) query.getResultList();
			
		} catch(NoResultException e) {
			em.close();
			e.printStackTrace();
		}
		
		return items;
	}
}
