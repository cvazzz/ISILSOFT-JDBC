package com.Parcial.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Parcial.model.Usuario;

public class UsuarioDAO {
	private String url;
	private Connection conexion;
	
	public UsuarioDAO() {
		
		this.url = "jdbc:sqlserver://DESKTOP-KBD7PJG:1433;databaseName=parcial;user=sa;password=200308";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.conexion = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int validarAcceso(String correo, String password) throws SQLException {
		int tieneAcceso = 0;
		Statement stmt = this.conexion.createStatement();
		String query = "select * from Usuario where correo = '" + correo +"' and password='"+ password +"';";
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			tieneAcceso = 1;
		}
		return tieneAcceso;
	}
	
	public List<Usuario> buscarContrasenha(String correo) throws SQLException {
		List<Usuario> listaContrasenha = new ArrayList();
		Statement stmt = this.conexion.createStatement();
		String query = "select * from Usuario where correo ='"+ correo +"';";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			String password = rs.getString(7);
			Usuario objUsuario = new Usuario(password);
			listaContrasenha.add(objUsuario);
		}
		return listaContrasenha;
	}
	
	public int validarExisteUsuario(String correo) throws SQLException {
		int existe = 0;
		Statement stmt = this.conexion.createStatement();
		String query ="select * from Usuario where correo ='" + correo + "';";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			existe = 1;
		}
		return existe;
	}
	
	public void registrarUsuario(String correo, String nombre, String apeMaterno, String apePaterno, String direccion, String password) throws SQLException {
		Statement stmt = this.conexion.createStatement();
		String query ="insert into Usuario(correo,nombre,apeMaterno,apePaterno,direccion,password) values ('"+correo+"','"+nombre+"','"+apeMaterno+"','"+apePaterno+"','"+direccion+"','"+password+"');";
		stmt.executeUpdate(query);
	}
}
