package com.noriakijr.spring_jsf_hib.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.noriakijr.spring_jsf_hib.model.Usuario;
import com.noriakijr.spring_jsf_hib.service.ServiceException;
import com.noriakijr.spring_jsf_hib.service.UsuarioService;

@Controller
@Scope("view")
public class UsuarioBean {

	private Usuario usuario;
	private List<Usuario> usuarios;
	@Inject
	private UsuarioService usuarioService;
	
	@PostConstruct
	public void init() {
		usuario = new Usuario();
		usuarios = usuarioService.list();
	}
	
	public void salvar() {
		try {
			usuarioService.salvar(usuario);
			usuario = new Usuario();
			usuarios = usuarioService.list();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Salvo com sucesso!"));
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
		}
	}

	public void excluir() {
		try {
			usuarioService.excluir(usuario);
			this.usuario = new Usuario();
			usuarios = usuarioService.list();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Excluído com sucesso!"));
		} catch (ServiceException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
