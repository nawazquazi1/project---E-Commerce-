package org.servlet;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.model.user;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;


public class UsersModel {
	public void addUser(DataSource dataSource, user newUser,HttpServletRequest request) {
		Connection connect = null;
		PreparedStatement statement = null;
		RequestDispatcher dispatcher=null;
		try {
			connect = dataSource.getConnection();
			String username = newUser.getName();
			String email = newUser.getEmail();
			String password = newUser.getPassword();
			String phoneNo = newUser.getNumber();
			String query = "insert into users (name,email,password,contact) values (?,?,?,?)";
			statement = connect.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, email);
			statement.setString(3, password);
			statement.setString(4, phoneNo);
			int rowCount=statement.executeUpdate();
			if(rowCount>0) {				
				request.setAttribute("status", "success");
			}else {
				request.setAttribute("status", "failed");	
			}	

		} catch (SQLException e) {
			e.getStackTrace();
		}

	}


}
