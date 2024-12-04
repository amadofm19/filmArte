package com.administrator.filmarte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.FavoritesList;

@Repository
public interface FavoritesListRepository extends JpaRepository<FavoritesList, Integer> {
    List<FavoritesList> findByMovieTitleContainingIgnoreCase(String title);
    List<FavoritesList> findByGenereContainingIgnoreCase(String genere);
    List<FavoritesList> findByViewingStatusContainingIgnoreCase(String status);

    @Query(value = "SELECT * FROM favorites_list WHERE idFavoritesList = :idFavoritesList", nativeQuery = true)
    FavoritesList getFavoritesListById(@Param("idFavoritesList") int idFavoritesList);

    @Query(value = "SELECT f FROM FavoritesList f WHERE f.idFavoritesList = :idFavoritesList")
    FavoritesList getFavoritesListByIdJPQL(@Param("idFavoritesList") int idFavoritesList);

    @Query(value = "SELECT * FROM favorites_list WHERE movieTitle = :movieTitle", nativeQuery = true)
    List<FavoritesList> getFavoritesListByMovieTitle(@Param("movieTitle") String movieTitle);

    @Query(value = "SELECT f FROM FavoritesList f WHERE f.movieTitle = :movieTitle")
    List<FavoritesList> getFavoritesListByMovieTitleJPQL(@Param("movieTitle") String movieTitle);

    @Query(value = "SELECT * FROM favorites_list WHERE genere = :genere", nativeQuery = true)
    List<FavoritesList> getFavoritesListByGenere(@Param("genere") String genere);

    @Query(value = "SELECT f FROM FavoritesList f WHERE f.genere = :genere")
    List<FavoritesList> getFavoritesListByGenereJPQL(@Param("genere") String genere);

    @Query(value = "SELECT * FROM favorites_list WHERE viewingStatus = :viewingStatus", nativeQuery = true)
    List<FavoritesList> getFavoritesListByViewingStatus(@Param("viewingStatus") String viewingStatus);

    @Query(value = "SELECT f FROM FavoritesList f WHERE f.viewingStatus = :viewingStatus")
    List<FavoritesList> getFavoritesListByViewingStatusJPQL(@Param("viewingStatus") String viewingStatus);

    @Query(value = "SELECT * FROM favorites_list WHERE rating = :rating", nativeQuery = true)
    List<FavoritesList> getFavoritesListByRating(@Param("rating") int rating);

    @Query(value = "SELECT f FROM FavoritesList f WHERE f.rating = :rating")
    List<FavoritesList> getFavoritesListByRatingJPQL(@Param("rating") int rating);

    @Query(value = "SELECT * FROM favorites_list WHERE movieTitle LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<FavoritesList> getFavoritesListByMovieTitleStartingWith(@Param("letter") String letter);

    @Query(value = "SELECT * FROM favorites_list WHERE genere LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<FavoritesList> getFavoritesListByGenereStartingWith(@Param("letter") String letter);

    @Query(value = "SELECT * FROM favorites_list WHERE viewingStatus LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<FavoritesList> getFavoritesListByViewingStatusStartingWith(@Param("letter") String letter);

    @Query(value = "SELECT * FROM favorites_list WHERE description LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<FavoritesList> getFavoritesListByDescriptionStartingWith(@Param("letter") String letter);
}