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
	<h1>Nuevo Usuario</h1>
		<br>
		<c:if test="${not empty mensaje}"> 
		<div><span>${mensaje}</span></div>
		</c:if>
		<br>
		<form action="UsuarioController" method="POST">
		<input type="hidden" name="opcionP" value="registrarUsuario">
			Correo: <input type="text" name="correo"><br><br>
			Nombre: <input type="text" name="nombre"><br><br>
			Apellido Paterno: <input type="text" name="apePaterno"><br><br>
			Apellido Materno: <input type="text" name="apeMaterno"><br><br>
			Direccion: <input type="text" name="direccion"><br><br>
			Password: <input type="text" name="password"><br><br>
			<button>Grabar</button>
		</form>
</body>
</html>