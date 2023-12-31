<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<h1>ISIL_SOFT</h1>
		<br>
		<br>
		<form action="Login" method="POST">
			<input type="hidden" name="opcionPOST" value="validarUsuario">
			Correo: <input type="email" name="correo"><br><br>
			Password: <input type="password" name="password"><br><br>
			<button>Ingresar</button>
			<br>
			<br>
		</form>
		<form action="Usuario" method="POST">
		<input type="hidden" name="opcionPOST" value="mostrarNuevoUsuario">
		<button>Nuevo Usuario</button>
		<br>
		<br>
	</form>
	<form action="Usuario" method="POST">
		<input type="hidden" name="opcionPOST" value="mostrarRecuperarContrasenha">
		<button>Recuperar Contraseña</button>
	</form>
	</body>
</html>