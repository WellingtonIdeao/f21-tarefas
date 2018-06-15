package br.com.wellington.tarefas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.wellington.tarefas.model.Usuario;
import br.com.wellington.tarefas.model.UsuarioDAO;

@Controller
public class LoginController {
	private UsuarioDAO dao;
	
	@Autowired
	public LoginController(UsuarioDAO dao) {
		this.dao= dao;
	}
	@RequestMapping("loginForm")	
	public String loginForm() {
		return "formulario-login";
	}
	@RequestMapping("efetuaLogin")	
	public String efetuaLogin(Usuario usuario, HttpSession session) {
		if(dao.existeUsuario(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			return "menu";
		}
			
		return "redirect:loginForm";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
		
	}
}
