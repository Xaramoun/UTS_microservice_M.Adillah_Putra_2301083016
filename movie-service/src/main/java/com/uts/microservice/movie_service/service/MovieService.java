package com.uts.microservice.movie_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uts.microservice.movie_service.model.Movie;
import com.uts.microservice.movie_service.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Ambil semua movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Ambil movie berdasarkan ID
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    // Membuat movie baru
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Update movie berdasarkan ID
    public Movie updateMovie(Long id, Movie movieDetails) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie tidak ditemukan dengan id " + id));

        movie.setTitle(movieDetails.getTitle());
        movie.setGenre(movieDetails.getGenre());
        movie.setDescription(movieDetails.getDescription());
        movie.setDuration(movieDetails.getDuration());
        movie.setReleaseDate(movieDetails.getReleaseDate()); // NEW: set releaseDate
        movie.setRating(movieDetails.getRating()); // NEW: set rating

        return movieRepository.save(movie);
    }

    // Menghapus movie berdasarkan ID
    public void deleteMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie tidak ditemukan dengan id " + id));
        movieRepository.delete(movie);
    }
}
