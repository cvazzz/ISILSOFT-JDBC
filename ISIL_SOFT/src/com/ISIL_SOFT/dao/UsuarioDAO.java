package com.ISIL_SOFT.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.ISIL_SOFT.model.Usuario;





public class UsuarioDAO {
	private String url; /*Este atributo guarda la ruta donde se encuentra la base de datos*/
	private Connection conexion; /*Este atribuo contiene la conexion que vamos a establecer*/
	
	public UsuarioDAO() {
		/*Vamos a definir todo lo necesario para conectarnos con la base de datos*/
		/*1. Definimos la ruta donde esta la base de datos*/
		/*La ruta de la BD es: jdbc:sqlserver://servidorBD:1433;databaseName=instanciaBD;user=usuarioBD;password=passwordBD*/
		this.url = "jdbc:sqlserver://localhost:1433;databaseName=ISIL_SOFT;user=sa;password=sebastian";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			this.conexion = DriverManager.getConnection(this.url);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int validarUsuario(String correo, String password) {
	    int existe = 0;
	    CallableStatement cstmt; // Este objeto nos permite llamar al procedimiento almacenado
	    try {
	        cstmt = this.conexion.prepareCall("{call ValidarCredenciales(?, ?, ?)}");

	        cstmt.setString(1, correo); // Configurar el primer parámetro del procedimiento
	        cstmt.setString(2, password); // Configurar el segundo parámetro del procedimiento
	        cstmt.registerOutParameter(3, java.sql.Types.BIT); // Configurar el tercer parámetro como parámetro de salida

	        cstmt.execute(); // Ejecutar el procedimiento almacenado

	        existe = cstmt.getInt(3); // Obtener el resultado del procedimiento almacenado

	        cstmt.close(); // Cerrar el CallableStatement
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return existe;
	}
	public int validarUsuarioPorCorreo(String correo) {
	    int existe = 0;
	    CallableStatement cstmt; // Este objeto nos permite llamar al procedimiento almacenado
	    try {
	        cstmt = this.conexion.prepareCall("{call ValidarUsuarioPorCorreo(?, ?)}");

	        cstmt.setString(1, correo); // Configurar el primer parámetro del procedimiento
	        cstmt.registerOutParameter(2, java.sql.Types.BIT); // Configurar el segundo parámetro como parámetro de salida

	        cstmt.execute(); // Ejecutar el procedimiento almacenado

	        existe = cstmt.getInt(2); // Obtener el resultado del procedimiento almacenado

	        cstmt.close(); // Cerrar el CallableStatement
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return existe;
	}
	public void registrarUsuario(String correo, String nombre, String apePaterno, String apeMaterno, String direccion, String password) {
	    CallableStatement cstmt; // Este objeto nos permite llamar al procedimiento almacenado
	    try {
	        cstmt = this.conexion.prepareCall("{call RegistrarUsuario(?, ?, ?, ?, ?, ?)}");

	        cstmt.setString(1, correo); // Configurar el primer parámetro del procedimiento
	        cstmt.setString(2, nombre); // Configurar el segundo parámetro del procedimiento
	        cstmt.setString(3, apePaterno); // Configurar el tercer parámetro del procedimiento
	        cstmt.setString(4, apeMaterno); // Configurar el cuarto parámetro del procedimiento
	        cstmt.setString(5, direccion); // Configurar el quinto parámetro del procedimiento
	        cstmt.setString(6, password); // Configurar el sexto parámetro del procedimiento

	        cstmt.execute(); // Ejecutar el procedimiento almacenado

	        cstmt.close(); // Cerrar el CallableStatement
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	/*public String obtenerContraseñaPorCorreo(String correo) {
	    String contrasenhaRecuperada = null;
	    CallableStatement cstmt;
	    try {
	        cstmt = this.conexion.prepareCall("{call ValidarCorreoYObtenerContraseña(?, ?)}");
	        cstmt.setString(1, correo);
	        cstmt.registerOutParameter(2, java.sql.Types.VARCHAR);
	        cstmt.execute();
	        contrasenhaRecuperada = cstmt.getString(2);
	        cstmt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return contrasenhaRecuperada;
	}*/
	public List<Usuario> buscarContrasenha(String correo) throws SQLException {
	    List<Usuario> listaContrasenha = new ArrayList<Usuario>();
	    CallableStatement cstmt = null;
	    ResultSet rs = null;

	    try {
	        cstmt = this.conexion.prepareCall("{call ObtenerUsuarioPorCorreo(?)}");
	        cstmt.setString(1, correo);
	        rs = cstmt.executeQuery();

	        while (rs.next()) {
	            String password = rs.getString("password"); // Asegúrate de que el nombre de la columna sea correcto
	            Usuario objUsuario = new Usuario(password);
	            listaContrasenha.add(objUsuario);
	        }
	    } catch (SQLException e) {
	        // Manejar excepciones aquí
	    } finally {
	        // Cerrar ResultSet y CallableStatement
	        if (rs != null) {
	            rs.close();
	        }
	        if (cstmt != null) {
	            cstmt.close();
	        }
	    }

	    return listaContrasenha;
	}



}