package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.movieshop.exceptions.UserException;
import com.movieshop.model.User;
import com.movieshop.model.UserDAO;

public class UserTests {

	// @Test(expected = UserException.class)
	// public void testBadPassword() throws UserException {
	// UserDAO dao = new UserDAO();
	// dao.login("ivcho@abv.bg", "sha1(1234)");
	// }

	@Test(expected = UserException.class)
	public void testBadEmail() throws UserException {
		UserDAO dao = new UserDAO();
		dao.login("pesho_lukankata", "1234");
	}

	@Test
	public void testSuccess() throws UserException {
		UserDAO dao = new UserDAO();
		User user = dao.login("pesho@abv.bg", "1234");

		int id = user.getId();
		assertEquals(id, 2);
	}

	@Test
	public void testRegister() throws UserException {
		UserDAO dao = new UserDAO();
		User testUser = new User("testindg", "testing", "tesdting@abv.bg", "12345");
		int id = dao.register(testUser);

		assertTrue(id >= 1);

		User user = dao.login("tesdting@abv.bg", "12345");

		assertEquals(id, user.getId());
	}

}
