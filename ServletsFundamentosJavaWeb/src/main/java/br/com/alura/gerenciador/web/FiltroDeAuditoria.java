package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.alura.gerenciador.Usuario;



//É UM CARACTERÍSTICA NO CONJUNTO DE URIs
//É UM INTERCEPTOR, UMA AUDITORIA

//O CONCEITO CHAMADO DE FILTRO SERVE PARA QUE SEJA UMA VERIFICAÇÃO ANTES DE ACESSAR
//A PÁGINA DESEJADA
//NO PARÂMETRO URL PATTERNS SERÁ EXECUTADO PARA TODAS AS PÁGINAS QUE IRÁ SER ACESSADA NO SISTEMA

@WebFilter(urlPatterns = "/*")
public class FiltroDeAuditoria implements Filter {

	@Override
	public void destroy() {

	}

	// O MÉTODO DO FILTER IRÁ FAZER COM QUE SEJA EXECUTADO NO MOMENTO EM QUE A
	// URL QUE ESTÁ NA
	// URL PATTERNS
	// NO CASO ELE APENAS IRÁ PRINTAR EM QUAL PÁGINA O USUÁRIO ESTÁ PEGANDO
	// O MÉTODO getRequestURI() SERVE PARA PEGAR EM QUAL URL O USUÁRIO ESTÁ
	// PEGANDO
	// SE VOCÊ DESEJA PEGAR OS PARÂMETROS DA URL, É UTILIZADO OUTRO MÉTODO
	// O chain.doFilter(request, response) SERVE PARA QUE O FILTRO DEIXE
	// CONTINUAR O PROCESSO DO SISTEMA
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		String usuario = getUsuario(req);
		System.out.println("Usuario " + usuario + " acessando a URI " + uri);
		chain.doFilter(request, response);

	}

	private String getUsuario(HttpServletRequest req) {
		
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");
		
		if(usuario==null) return "<deslogado>";
		
		return usuario.getEmail();
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
