package br.com.alura.gerenciador.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.Empresa;
import br.com.alura.gerenciador.dao.EmpresaDAO;

public class NovaEmpresa implements Tarefa {

	// O MÉTODO POST PEGA AS INFORMAÇÕES E NÃO ENVIA NA URL
	// ELE É O MÉTODO MAIS INDICADO PARA ENVIAR DADOS PARA O SERVIDOR
	// POIS HÁ UM LIMITE QUE PODE SER PASSADO NA URL ATRAVÉS DO MÉTODO GET

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse resp) {

		String nome = req.getParameter("nome");

		Empresa empresa = new Empresa(nome);
		new EmpresaDAO().adiciona(empresa);
		// COLOCADO NO REQUEST O OBJETO EMPRESA PARA PODER MOSTRAR NO JSP
		req.setAttribute("empresa", empresa);

		return "/WEB-INF/paginas/novaEmpresa.jsp";

	}
}
