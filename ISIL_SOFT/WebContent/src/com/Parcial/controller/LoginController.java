package com.Parcial.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Parcial.dao.UsuarioDAO;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsuarioDAO objUsuarioDAO = new UsuarioDAO();
       
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
		String opcion = request.getParameter("opcion");
		switch (opcion) {
		case "validarAcceso" : {
				try {
					validarAcceso(request, response);
				} catch (ServletException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
	public void validarAcceso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String nuevaPagina = "";
		String correo = request.getParameter("correo");
		String password = request.getParameter("password");
		
		int tieneAcceso = objUsuarioDAO.validarAcceso(correo,password);
		if(tieneAcceso==0) {
			nuevaPagina = "/Login.jsp";
		}
		else {
			nuevaPagina = "/principal.jsp";
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		dispatcher.forward(request, response);
	}

}
