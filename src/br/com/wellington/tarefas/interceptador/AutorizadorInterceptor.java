package br.com.wellington.tarefas.interceptador;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		
		if(uri.endsWith("loginForm")||uri.endsWith("efetuaLogin")||uri.contains("resources"))
			return true;
		
		if(session.getAttribute("usuarioLogado")!=null) {
			return true;
		}
		
		response.sendRedirect("loginForm");
		return false;
	}

}
