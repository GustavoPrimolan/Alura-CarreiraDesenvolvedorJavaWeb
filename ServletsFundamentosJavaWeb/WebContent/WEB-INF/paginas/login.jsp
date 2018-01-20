<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>

<c:if test="${not empty usuario }">
	Usuario ${usuario.email } logado<br/>
</c:if>

<c:if test="${empty usuario }">
	Usuario invalido <br/>
</c:if>
</body>
</html>