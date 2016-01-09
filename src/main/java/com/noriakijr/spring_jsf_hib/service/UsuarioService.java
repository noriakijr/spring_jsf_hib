package com.noriakijr.spring_jsf_hib.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.noriakijr.spring_jsf_hib.model.Usuario;
import com.noriakijr.spring_jsf_hib.persistence.PersistenceException;
import com.noriakijr.spring_jsf_hib.persistence.UsuarioDAO;

@Service
public class UsuarioService {

	@Inject
	private UsuarioDAO usuarioDAO;
	
	@Transactional(rollbackFor = Exception.class)
	public Usuario salvar(Usuario usuario) throws ServiceException {
		try {
			return usuarioDAO.salvar(usuario);
		} catch (PersistenceException e) {
			throw new ServiceException("N�o foi poss�vel salvar", e);
		}
	}

	@Transactional(readOnly = true)
	public List<Usuario> list() {
		return usuarioDAO.list();
	}

	@Transactional(rollbackFor = Exception.class)
	public void excluir(Usuario usuario) throws ServiceException {
		try {
			usuarioDAO.excluir(usuario);
		} catch (PersistenceException e) {
			throw new ServiceException("N�o foi poss�vel excluir", e);
		}
	}
	
	@Transactional(readOnly = true)
	public Usuario findById(Usuario usuario) {
		if(usuario == null || usuario.getId() == null)
			return null;
		return usuarioDAO.find(usuario);
	}
	
}
