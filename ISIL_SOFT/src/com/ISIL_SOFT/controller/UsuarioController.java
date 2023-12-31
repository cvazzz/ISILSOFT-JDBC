package com.ISIL_SOFT.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ISIL_SOFT.dao.UsuarioDAO;
import com.ISIL_SOFT.model.Usuario;


/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/Usuario")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioDAO objUsuarioDAO = new UsuarioDAO();
    
       
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
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcionGET");
		switch (opcion) {
			
			case "buscarContrasenha" : {
				try {
					buscarContrasenha(request,response);
				} catch (SQLException | ServletException | IOException e) {
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String opcion = request.getParameter("opcionPOST");
	        switch (opcion) {
	            case "mostrarNuevoUsuario": {
	                mostrarNuevoUsuario(request, response);
	                break;
	            }
	            case "grabarNuevoUsuario": {
	                grabarNuevoUsuario(request, response);
	                break;
	            }
	            case "mostrarRecuperarContrasenha": {
	                mostrarRecuperarContrasenha(request, response);
	                break;
	            }
	            case "mostrarLogin": {
	                mostrarLogin(request, response);
	                break;
	            }
	        }
	}
	
	public void mostrarNuevoUsuario(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String nuevaPagina = "/NuevoUsuario.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		dispatcher.forward(request, response);
	}
		public void mostrarLogin(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
			String nuevaPagina = "/index.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
			dispatcher.forward(request, response);
		}
		public void grabarNuevoUsuario(HttpServletRequest request, HttpServletResponse response) {
			String correo = request.getParameter("correo");
			String nombre = request.getParameter("nombre");
			String apePaterno = request.getParameter("apePaterno");
			String apeMaterno = request.getParameter("apeMaterno");
			String direccion = request.getParameter("direccion");
			String password = request.getParameter("password");
			
			String paginaDestino;
			int existe = objUsuarioDAO.validarUsuarioPorCorreo(correo);
			if (existe==0) {
				/*Aqui el usuario no existe y hay que registrarlo*/
				/*Se le pone activo directamente porque un usuario siempre se crea en estado Activo*/
				objUsuarioDAO.registrarUsuario(correo,nombre, apePaterno, apeMaterno,direccion, password);
				paginaDestino = "/index.jsp";
			}
			else {
				/*Aqui el usuario existe y no hay que registrarlo, hay que avisarle al usuario*/
				paginaDestino = "/NuevoUsuario.jsp";
			}
			/*Este codigo siempre nos envia a una nueva pagina*/
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		/*public void recuperarContrasenha(HttpServletRequest request, HttpServletResponse response) {
		    String paginaDestino = "/RecuperarContrasenha.jsp";
		    String correo = request.getParameter("correo");

		    String contrasenhaRecuperada = objUsuarioDAO.obtenerContraseñaPorCorreo(correo);

		    request.setAttribute("contrasenhaRecuperada", contrasenhaRecuperada);

		    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
		    try {
		        dispatcher.forward(request, response);
		    } catch (ServletException | IOException e) {
		        e.printStackTrace();
		    }
		}*/

		public void mostrarRecuperarContrasenha(HttpServletRequest request, HttpServletResponse response) {
			String paginaDestino = "/RecuperarContrasenha.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
			try {
				dispatcher.forward(request, response);
			} catch (ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
