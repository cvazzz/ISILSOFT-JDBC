<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<br>
		<form action="LoginController" method="POST">
		<input type="hidden" name="opcion" value="validarAcceso">
		Correo: <input type="text" name="correo" id="correo"><br><br>
		Password: <input type="password" name="password" id="password"><br><br>
		<button>Ingresar</button><br><br>
		</form>
		<a href="UsuarioController?opcion=crearUsuario">Crear Usuario</a><br><br>
		<a href="UsuarioController?opcion=recuperarContrasenha">Recuperar Contraseña</a>
		
	</body>
</html>