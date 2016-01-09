package com.noriakijr.spring_jsf_hib.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.noriakijr.spring_jsf_hib.model.Usuario;

@Repository
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;
	
	public Usuario salvar(Usuario usuario) throws PersistenceException {
		try {
			return em.merge(usuario);
		} catch (Exception e) {
			throw new PersistenceException("N�o foi poss�vel salvar", e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> list() {
		Query query = em.createQuery("select u from Usuario u order by u.nome");
		return query.getResultList();
	}

	public void excluir(Usuario usuario) throws PersistenceException {
		try {
			// antes da exclusao, eh necessario obter a referencia do objeto
			usuario = em.getReference(Usuario.class, usuario.getId());
			em.remove(usuario);
		} catch (Exception e) {
			throw new PersistenceException("N�o foi poss�vel excluir", e);
		}
	}

	public Usuario find(Usuario usuario) {
		return em.find(Usuario.class, usuario.getId());
	}
}
