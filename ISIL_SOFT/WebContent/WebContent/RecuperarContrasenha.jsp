<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Recuperar Contraseña</h1>
	<br>
	<br>
		<h2>Buscar Usuario</h1>
		<br>
		<form action="UsuarioController" method="GET">
		<input type="hidden" name="opcion" value="buscarContrasenha">
			Correo: <input type="text" name="correo">
			<button>Recuperar</button>
		</form>
		<br>
		<br>
			<h2>Contraseña Recuperada</h1><br>
			Contraseña: 
			<c:forEach var="objContrasenha" items="${contrasenha}">
			<td>${objContrasenha.password}</td>
			</c:forEach>
			<br><br>
			<form action="UsuarioController" method="POST">
			<input type="hidden" name="opcionP" value="RegresarLogin">
			<button>Regresar a Login</button>
			</form>
</body>
</html>