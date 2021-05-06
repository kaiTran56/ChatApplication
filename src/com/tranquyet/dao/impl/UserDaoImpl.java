package com.tranquyet.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.tranquyet.dao.UserDao;
import com.tranquyet.server.User;

public class UserDaoImpl implements UserDao<User> {
	private final String USER = "root";
	private final String PASSWORD = "54935620tQ.";
	private final String ADDRESS = "jdbc:mysql://localhost:3306/network";
	private PreparedStatement statement;

	private Connection connect;

	public Connection getConnectionToDBC() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Connect to JDBC");
			return DriverManager.getConnection(ADDRESS, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> listUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(User t) {
		String sql = "Insert into account values(?,?);";
		connect = getConnectionToDBC();
		try {
			statement = connect.prepareStatement(sql);
			statement.setString(1, t.getUsername());
			statement.setString(2, t.getPassword());
			statement.executeUpdate();
			System.out.println("Sucessfull!");
			statement.close();
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void edit(User t) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean login(User t) {
		String sql = "select username, password from account where username like ? and password like ?;";
		connect = getConnectionToDBC();
		try {
			this.statement = connect.prepareStatement(sql);
			statement.setString(1, t.getUsername());
			statement.setString(2, t.getPassword());
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				String username = result.getString("username");
				String password = result.getString("password");

				return new User(username, password) != null ? true : false;

			}
			result.close();
			statement.close();
			connect.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
