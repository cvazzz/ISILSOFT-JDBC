package com.ISIL_SOFT.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ISIL_SOFT.dao.UsuarioDAO;



/**
 * Servlet implementation class LoginController
 */
@WebServlet("/Login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioDAO objUsuarioDAO = new UsuarioDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String accion = request.getParameter("opcionPOST");
	    switch (accion) {
	        case "validarUsuario": {
	            validarUsuario(request, response);
	            break;
	        }
	    }
	}

	public void validarUsuario(HttpServletRequest request, HttpServletResponse response) {
	    String paginaDestino = "/index.jsp";
	    String correo = request.getParameter("correo");
	    String password = request.getParameter("password");
	    int existe = objUsuarioDAO.validarUsuario(correo, password);

	    if (existe == 1) {
	        paginaDestino = "/pagPrueba.jsp";
	    }

	    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);

	    try {
	        dispatcher.forward(request, response);
	    } catch (ServletException | IOException e) {
	        e.printStackTrace();
	    }
	}

}
