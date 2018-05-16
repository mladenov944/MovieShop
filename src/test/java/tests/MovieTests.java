package tests;

import org.junit.Test;

import com.movieshop.exceptions.MovieException;
import com.movieshop.model.Movie;
import com.movieshop.model.MovieDAO;

public class MovieTests {

	@Test
	public void addMovie() throws MovieException {
		MovieDAO movieDAO = new MovieDAO();
		Movie asd = new Movie("test", "test", (short) 2014, "mnogo hubav film", "C:\\Uploads\\cars.jpg", 29.99f,
				"Animation", "https://www.imdb.com/title/tt0317219/?ref_=nv_sr_2", (short) 43);
		movieDAO.addMovie(asd);
	}
}
