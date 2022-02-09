package test;

import main.models.Movie;
import main.models.Store;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class StoreTest {
    Store store;

    @Before
    public void setup() {
        store = new Store();
        store.addMovie(new Movie("The Shawshank Redemption", "Blue-Ray", 9.2));
        store.addMovie(new Movie("The Godfather", "Blue-Ray", 9.1));
    }

    @Test
    public void movieAdded() {
        Assertions.assertTrue(store.contains(new Movie("The Godfather", "Blue-Ray", 9.1)));
    }

    @Test
    public void sellMovieTest() {
        store.sellMovie("The Godfather");
        Assertions.assertFalse(store.contains(new Movie("The Godfather", "Blue-Ray", 9.1)));
    }

    @Test
    public void rentMovieTest() {
        store.rentMovie("The Godfather");
        Assertions.assertFalse(store.getMovie(1).isAvailable());
    }

    @Test
    public void returnMovieTest() {
        store.rentMovie("The Godfather");
        store.returnMovie("The Godfather");
        Assertions.assertTrue(store.getMovie(1).isAvailable());
    }

    @Test(expected = IllegalStateException.class)
    public void movieNotInStock() {
        store.rentMovie("The Godfather");
        store.sellMovie("The Godfather");
    }
}
