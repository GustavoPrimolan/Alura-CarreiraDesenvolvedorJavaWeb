package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout implements Tarefa {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) {
		// TIRAR O ATRIBUTO
		req.getSession().removeAttribute("usuarioLogado");
		
		// INVALIDATE TIRA TODA A SESSÃO DO USUÁRIO
		// req.getSession().invalidate();;
		// PEGA O COOKIE E COLOCA O TEMPO DELE PARA ZERO
		/*
		 * Cookie cookie = new Cookies(req.getCookies()).buscaUsuarioLogado();
		 * 
		 * 
		 * 
		 * PrintWriter writer = resp.getWriter(); if (cookie == null) {
		 * writer.println("<html><body>Usuario nao estava logado!</body></html>"
		 * ); } cookie.setMaxAge(0); resp.addCookie(cookie);
		 */
		//O TRECHO ABAIXO SERVE PARA REDIRECIONAR O CLIENTE COM UMA RESPOSTA (RESPONSE)
		//resp.sendRedirect("logout.html");
		//O TRECHO ABAIXO SERVE PARA CONSEGUIR ACESSAR UM ARQUIVO NO /WEB-INF
		//O ARQUIVO É JOGADO LÁ PARA QUE USUÁRIOS NÃO CONSIGAM ACESSAR A PÁGINA DIRETAMENTE
		//POR CONTA DISSO ESSE PROCEDIMENTO É FEITO ATRAVÉS DO REQUEST E NÃO DO RESPONSE
		//DISPACHANDO O RESULTADO DA PÁGINA PARA O CLIENTE
		
		
		//MUDOU O QUE ESTÁ ACIMA E APENAS RETORNA PARA A PÁGINA PARA O FAZ TUDO
		return "/WEB-INF/paginas/logout.html";

	}


}
