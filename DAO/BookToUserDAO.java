package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import DTOs.BookDTO;
import DTOs.BookToUserDTO;
import DTOs.UserDTO;
import Utils.DBUtils;

public class BookToUserDAO {
	public BookToUserDTO getBookById(int id) {
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM book_to_user WHERE book_id = ?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new BookToUserDTO(rs.getInt("book_id"), rs.getInt("user_id"),
							rs.getTimestamp("borrow_date"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public BookToUserDTO getBorrowedBook(UserDTO user) {
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM book_to_user WHERE user_id = ?")) {
			ps.setInt(1, user.getId());
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new BookToUserDTO(rs.getInt("book_id"), rs.getInt("user_id"),
							rs.getTimestamp("borrow_date"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void borrowBook(UserDTO user, BookDTO book) {
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO book_to_user (book_id, user_id, borrow_date) VALUES (?, ?, ?)")) {
			if (getBookById(book.getId()) != null) {
				System.out.println("Book is already borrowed.");
				return;
			}

			ps.setInt(1, book.getId());
			ps.setInt(2, user.getId());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
