package br.com.alura.gerenciador.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.alura.gerenciador.Usuario;
import br.com.alura.gerenciador.dao.UsuarioDAO;

public class Login implements Tarefa {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) {

		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscaPorEmailESenha(email, senha);
		
		if(usuario != null){
			HttpSession session = req.getSession();
			
			session.setAttribute("usuarioLogado", usuario);
			
			
			req.setAttribute("resultado", "Usuario invalido");
			
		
		}
		
		req.setAttribute("usuario", usuario);
		return "/WEB-INF/paginas/login.jsp";
		
		
	}
}
