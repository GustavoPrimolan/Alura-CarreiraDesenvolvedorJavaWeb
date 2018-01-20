package br.com.alura.gerenciador.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/executa")
public class Controller extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String tarefa = req.getParameter("tarefa");
		//QUAL TAREFA SERÁ EXECUTADA EXATAMENTE
		
		if(tarefa == null){
			throw new IllegalArgumentException("Você esqueceu de passar a tarefa");
		}
		
		tarefa = "br.com.alura.gerenciador.web." + tarefa;
		
		//ESSA API QUE VEM COM O JAVA PERMITE VOCÊ PEGAR A CLASSE DE ACORDO COM A STRING
		Class<?> tipo = null;
		try {
			tipo = Class.forName(tarefa);
			//CONSIGO INSTANCIAR A CLASSE CLASS QUE É O TIPO DE CLASSE QUE ESTAVA NA STRING
			//A TAREFA SERÁ A INTERFACE QUE TODAS AS CLASSES QUE EU DESEJAR CHAMAR IRÁ TER
			//É NECESSÁRIO UM CAST PARA TAREFA, POIS O tipo.newInstance RETORNA UM OBJETO
			Tarefa instancia = (Tarefa) tipo.newInstance();
			//PEGA A PÁGINA QUE O USUÁRIO DESEJA SER REDIRECIONADO
			String pagina = instancia.executa(req, resp);
			RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
			dispatcher.forward(req, resp);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			throw new ServletException(e);
		}
		
		
		
		//PARA ONDE REDIRECIONAR?
	
	}
	
}
