<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
	Resultado da busca:
	<!-- PARA CADA UMA DAS EMPRESAS PASSADAS NA CAMADA ANTERIOR
		É COLOCADO NA VARIAVEL empresa
	 	FOR EACH FAZ PARTE DE UMA BIBLIOTECA DE TAGS
	 	TAGLIB
	 	QUE SE CHAMA CORE (abreviado C)
	 -->
	<ul>
		<c:forEach var="empresa" items="${empresas}">
			<li>${empresa.id }:${empresa.nome }</li>
		</c:forEach>
	</ul>
</body>
</html>