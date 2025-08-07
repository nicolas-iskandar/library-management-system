package DTOs;

import java.sql.Timestamp;

public class BookToUserDTO {
	private int bookId;
	private int userId;
	private Timestamp dateBorrowed;

	public BookToUserDTO(int bookId, int userId, Timestamp dateBorrowed) {
		this.bookId = bookId;
		this.userId = userId;
		this.dateBorrowed = dateBorrowed;
	}

	public int getBookId() {
		return bookId;
	}

	public Timestamp getDateBorrowed() {
		return dateBorrowed;
	}

	public int getUserId() {
		return userId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setDateBorrowed(Timestamp dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "BookToUserDTO [bookId=" + bookId + ", userId=" + userId + ", dateBorrowed=" + dateBorrowed + "]";
	}
}