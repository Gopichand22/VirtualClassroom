package com.online.classroom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.online.classroom.model.Register;

public class RegisterMeDao {
	private static final String URL = "jdbc:mysql://localhost:3306/virtual_classroom_db";
	private static final String USER_ID = "root";
	private static final String PASS = "087722";
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	Connection con = null;
	PreparedStatement ps = null;

	public String registerUser(Register register) {

		String name = register.getUser();
		String emaill = register.getEmail();
		String pass = register.getPwd();
		String role = register.getRole();
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(URL, USER_ID, PASS);
			if (role.equals("staff")) {

				ps = con.prepareStatement(
						"insert into teachers(teacher_name,teacher_pass,teacher_email) values(?,?,?)");
				ps.setString(1, name);
				ps.setString(2, emaill);
				ps.setString(3, pass);
				if (!ps.execute()) {
					return "staff";
				}

			} else if (role.equals("student")) {
				ps = con.prepareStatement("insert into students(stu_name,stu_pass,stu_email) values(?,?,?)");
				ps.setString(1, name);
				ps.setString(2, emaill);
				ps.setString(3, pass);
				if (!ps.execute()) {
					return "student";
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (ps != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Invalid user credentials";
	}

}
