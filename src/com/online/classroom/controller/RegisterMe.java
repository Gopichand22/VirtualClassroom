package com.online.classroom.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.online.classroom.dao.RegisterMeDao;
import com.online.classroom.model.Register;

@WebServlet("/RegisterMe")
public class RegisterMe extends HttpServlet {

	private static final long serialVersionUID = 6789909920189781934L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		RegisterMeDao registerDao = new RegisterMeDao();

		String user = req.getParameter("name");
		String email = req.getParameter("email");
		String pwd = req.getParameter("pass");
		String confirmPwd = req.getParameter("cpass");
		String rol = req.getParameter("role");

		Register register = new Register();

		register.setUser(user);
		register.setEmail(email);
		register.setPwd(pwd);
		register.setConfirmPwd(confirmPwd);
		register.setRole(rol);

		String roles = registerDao.registerUser(register);
		if (roles.equals("staff")) {
			try {
				req.getRequestDispatcher("staff.jsp").forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				req.getRequestDispatcher("index.jsp").forward(req, res);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
}
