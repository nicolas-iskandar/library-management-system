package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DTOs.BookDTO;
import Utils.DBUtils;

public class BookDAO {
	public BookDTO getBookById(int id) {
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM books WHERE id = ?")) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					BookDTO bookDTO = new BookDTO(rs.getString("title"));
					bookDTO.setId(rs.getInt("id"));
					return bookDTO;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public BookDTO getBookByTitle(String title) {
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM books WHERE title = ?")) {
			ps.setString(1, title);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					BookDTO book = new BookDTO(rs.getString("title"));
					book.setId(rs.getInt("id"));
					return book;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<BookDTO> getUsers() {
		List<BookDTO> books = new ArrayList<>();
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement("SELECT * FROM books")) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					BookDTO book = new BookDTO(rs.getString("title"));
					book.setId(rs.getInt("id"));
					books.add(book);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public void saveBook(BookDTO book) {
		try (Connection connection = DBUtils.getConnection();
				PreparedStatement ps = connection.prepareStatement("INSERT INTO books (title) VALUES (?)")) {
			if (getBookByTitle(book.getTitle()) != null)
				return;

			ps.setString(1, book.getTitle());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
