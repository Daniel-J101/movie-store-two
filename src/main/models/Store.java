package main.models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Store {
    ArrayList<Movie> movies;

    public Store() {
        this.movies = new ArrayList<Movie>();
    }

    public Movie getMovie(int index) {
        return new Movie(this.movies.get(index));
    }

    public void setMovie(int index, Movie movie) {
        this.movies.set(index, new Movie(movie));
    }

    public void addMovie(Movie movie) {
        movies.add(new Movie(movie));
    }

    public void sellMovie(String name) {
        if(!movies.get(getMovieIndex(name)).isAvailable())
            throw new IllegalStateException("Movie is rented!");
        movies.removeIf(movie -> movie.getName().equals(name));
    }

    public void rentMovie(String name) {
        movies.get(getMovieIndex(name)).setAvailable(false);
    }

    public int getMovieIndex(String name) {
        return IntStream.range(0, movies.size())
                .filter(index -> movies.get(index).getName().equals(name))
                .findFirst()
                .orElse(-1000);
    }

    public void returnMovie(String name) {
        movies.get(getMovieIndex(name)).setAvailable(true);
    }

    public boolean contains(Movie movie) {
        return movies.contains(movie);
    }



    public String toString() {
        String temp = "";
        for (int i = 0; i < this.movies.size(); i++) {
            temp += this.movies.get(i).toString();
            temp += "\n\n";
        }
        return temp;
    }

}