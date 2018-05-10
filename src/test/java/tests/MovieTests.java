package tests;

import org.junit.Test;

import com.movieshop.exceptions.MovieException;
import com.movieshop.model.MovieDAO;


public class MovieTests {

	@Test(expected = MovieException.class)
	public void testBadMovieByIndex() throws MovieException {
		MovieDAO dao = new MovieDAO();
		dao.getMovieId(200);
	}
}
