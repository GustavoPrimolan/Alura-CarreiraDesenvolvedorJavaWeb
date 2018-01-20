<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
Bem vindo ao nosso gerenciador de empresas!<br/>
<!-- POR PADRÃO O HTML USA NOS FORMULÁRIOS O MÉTODO GET
	SE EU QUISER QUE NÃO UTILIZE O MÉTODO GET É PRECISO SETAR O MÉTODO POST
 -->
 
<c:if test="${not empty usuarioLogado}"> 
 	Logado como ${usuarioLogado.email}<br/>
 </c:if>
 <br/>
<form action="executa" method="POST">
	<input type="hidden" name="tarefa" value="NovaEmpresa">
	Nome: <input type="type" name="nome">
	<input type="submit" value="Enviar">
</form>

<form action="executa" method="POST">
	<input type="hidden" name="tarefa" value="Login"/>
	Email: <input type="type" name="email"/>
	Senha: <input type="password" name="senha"/>
	<input type="submit" value="Enviar"/>
</form>


<form action="executa" method="POST">
	<input type="hidden" name="tarefa" value="Logout">
	<input type="submit" value="Deslogar">
</form>

</body>
</html>