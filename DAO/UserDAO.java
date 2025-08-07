package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTOs.UserDTO;
import Utils.DBUtils;

public class UserDAO {
	public UserDTO getUserById(int id) {
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					UserDTO user = new UserDTO(rs.getString("name"));
					user.setId(rs.getInt("id"));
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public UserDTO getUserByName(String name) {
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE name = ?")) {
			ps.setString(1, name);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					UserDTO user = new UserDTO(rs.getString("name"));
					user.setId(rs.getInt("id"));
					return user;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<UserDTO> getUsers() {
		List<UserDTO> users = new ArrayList<>();
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM users")) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					UserDTO user = new UserDTO(rs.getString("name"));
					user.setId(rs.getInt("id"));
					users.add(user);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public void saveUser(UserDTO user) {
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement("INSERT INTO users (name) VALUES (?)")) {
			if (getUserByName(user.getName()) != null)
				return;

			ps.setString(1, user.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
