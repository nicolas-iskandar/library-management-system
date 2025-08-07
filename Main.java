import java.util.List;

import DAO.BookDAO;
import DAO.BookToUserDAO;
import DAO.UserDAO;
import DTOs.BookDTO;
import DTOs.UserDTO;

public class Main {
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAO();
		BookDAO bookDAO = new BookDAO();
		BookToUserDAO bookToUserDAO = new BookToUserDAO();

		UserDTO user1 = new UserDTO("User1");
		UserDTO user2 = new UserDTO("User2");
		UserDTO user3 = new UserDTO("User3");
		userDAO.saveUser(user1);
		userDAO.saveUser(user2);
		userDAO.saveUser(user3);

		BookDTO book1 = new BookDTO("Book1");
		BookDTO book2 = new BookDTO("Book2");
		BookDTO book3 = new BookDTO("Book3");
		bookDAO.saveBook(book1);
		bookDAO.saveBook(book2);
		bookDAO.saveBook(book3);

		List<UserDTO> users = userDAO.getUsers();
		System.out.println(users);
		List<BookDTO> books = bookDAO.getUsers();
		System.out.println(books);

		System.out.println(userDAO.getUserById(users.get(0).getId()));
		System.out.println(bookDAO.getBookById(books.get(0).getId()));

		bookToUserDAO.borrowBook(users.get(0), books.get(0));
		bookToUserDAO.borrowBook(users.get(1), books.get(1));
		bookToUserDAO.borrowBook(users.get(2), books.get(2));

		System.out.println(bookToUserDAO.getBorrowedBook(users.get(0)));
		System.out.println(bookToUserDAO.getBorrowedBook(users.get(1)));
		System.out.println(bookToUserDAO.getBorrowedBook(users.get(2)));
	}
}
