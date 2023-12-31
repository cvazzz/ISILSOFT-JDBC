package com.Parcial.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Parcial.dao.UsuarioDAO;
import com.Parcial.model.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO objUsuarioDAO = new UsuarioDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		switch(opcion) {
		case "crearUsuario":{
				mostrarCrearUsuario(request,response);
				break;
			}
		case "recuperarContrasenha":{
			mostrarRecuperarContrasenha(request,response);
			break;
			}
		case "buscarContrasenha":{
			try {
				buscarContrasenha(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			}
		}
	}

	
	public void buscarContrasenha(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
		String correo = request.getParameter("correo");
		objUsuarioDAO.buscarContrasenha(correo);
		List<Usuario> listaUsuarios = objUsuarioDAO.buscarContrasenha(correo);
		request.setAttribute("contrasenha", listaUsuarios);
		String nuevaPagina = "/RecuperarContrasenha.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		dispatcher.forward(request, response);
	}

	public void mostrarRecuperarContrasenha(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String nuevaPagina = "/RecuperarContrasenha.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		dispatcher.forward(request, response);
	}
	
	public void mostrarCrearUsuario(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String nuevaPagina = "/NuevoUsuario.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcionP = request.getParameter("opcionP");
		switch(opcionP) {
		case "RegresarLogin" :{
				RegresarLogin(request,response);
				break;
			}
		case "registrarUsuario" : {
			try {
				registrarUsuario(request,response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			}
		}
	}

	public void RegresarLogin(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String nuevaPagina = "/Login.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		dispatcher.forward(request, response);
	}
	
	public void registrarUsuario(HttpServletRequest request,HttpServletResponse response) throws SQLException, ServletException, IOException {
		String mensaje ="";
		String correo = request.getParameter("correo");
		String nombre = request.getParameter("nombre");
		String apePaterno = request.getParameter("apePaterno");
		String apeMaterno = request.getParameter("apeMaterno");
		String direccion = request.getParameter("direccion");
		String password = request.getParameter("password");
		int existe = objUsuarioDAO.validarExisteUsuario(correo);
		if(existe==0) {
			mensaje = "Usuario registrado con exito";
			request.setAttribute("mensaje", mensaje);
			String nuevaPagina = "/NuevoUsuario.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
			dispatcher.forward(request, response);
		}
		else {
			mensaje = "El correo del usuario ya se encuentra registrado";
			request.setAttribute("mensaje", mensaje);
			String nuevaPagina = "/NuevoUsuario.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
			dispatcher.forward(request, response);
		}
	}
}
