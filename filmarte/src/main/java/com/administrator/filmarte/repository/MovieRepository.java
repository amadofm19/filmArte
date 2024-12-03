package com.administrator.filmarte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
     List<Movie> findByTitleContainingIgnoreCase(String title);
     List<Movie> findByYear(int year);
     List<Movie> findByTitleContainingIgnoreCaseAndYear(String title, int year);

    @Query(value = "SELECT * FROM movie WHERE idMovie = :idMovie", nativeQuery = true)
    Movie getMovieById(@Param("idMovie") int idMovie);

    @Query(value = "SELECT m FROM Movie m WHERE m.idMovie = :idMovie")
    Movie getMovieByIdJPQL(@Param("idMovie") int idMovie);

    @Query(value = "SELECT * FROM movie WHERE title = :title", nativeQuery = true)
    List<Movie> getMoviesByTitle(@Param("title") String title);

    @Query(value = "SELECT m FROM Movie m WHERE m.title = :title")
    List<Movie> getMoviesByTitleJPQL(@Param("title") String title);

    @Query(value = "SELECT * FROM movie WHERE year = :year", nativeQuery = true)
    List<Movie> getMoviesByYear(@Param("year") int year);

    @Query(value = "SELECT m FROM Movie m WHERE m.year = :year")
    List<Movie> getMoviesByYearJPQL(@Param("year") int year);

    @Query(value = "SELECT * FROM movie WHERE description = :description", nativeQuery = true)
    List<Movie> getMoviesByDescription(@Param("description") String description);

    @Query(value = "SELECT m FROM Movie m WHERE m.description = :description")
    List<Movie> getMoviesByDescriptionJPQL(@Param("description") String description);

    @Query(value = "SELECT * FROM movie WHERE title LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Movie> getMoviesByTitleStartingWith(@Param("letter") String letter);

    @Query(value = "SELECT * FROM movie WHERE description LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Movie> getMoviesByDescriptionStartingWith(@Param("letter") String letter);
}
