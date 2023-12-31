package com.ISIL_SOFT.model;

public class Usuario {
	/*En las clases model, los atributos son los mismo campos de la tabla*/
	private int id;
	private String correo;
	private String nombre;
	private String  apePaterno;
	private String apeMaterno;
	private String direccion;
	private String password;
	
	
	public Usuario() {
		
	}
	public Usuario(int id, String correo, String nombre, String apePaterno, String apeMaterno, String direccion,
			String password) {
		super();
		this.id = id;
		this.correo = correo;
		this.nombre = nombre;
		this.apePaterno = apePaterno;
		this.apeMaterno = apeMaterno;
		this.direccion = direccion;
		this.password = password;
		
		
	}
	
	public int getid() {
		return id;
	}
	public void setid(int id) {
		this.id = id;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApePaterno() {
		return apePaterno;
	}
	public void setApePaterno(String apePaterno) {
		this.apePaterno = apePaterno;
	}
	public String getApeMaterno() {
		return apeMaterno;
	}
	public void setApeMaterno(String apeMaterno) {
		this.apeMaterno = apeMaterno;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Usuario(String password) {
		// TODO Auto-generated constructor stub
		this.password = password;
	}
	

	
}
