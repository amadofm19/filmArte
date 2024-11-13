package com.administrator.filmarte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findByViewingDate(String viewingDate);
    List<History> findByDuration(int duration);
    List<History> findByGenreContainingIgnoreCase(String genre);



    // BY ID
    @Query(value = "SELECT * FROM history WHERE idHistory = :idHistory", nativeQuery = true)
    History getHistoryById(@Param("idHistory") int idHistory);

    @Query(value = "SELECT h FROM History h WHERE h.idHistory = :idHistory")
    History getHistoryByIdJPQL(@Param("idHistory") int idHistory);

    // BY VIEWING DATE
    @Query(value = "SELECT * FROM history WHERE viewingDate = :viewingDate", nativeQuery = true)
    List<History> getHistoriesByViewingDate(@Param("viewingDate") String viewingDate);

    @Query(value = "SELECT h FROM History h WHERE h.viewingDate = :viewingDate")
    List<History> getHistoriesByViewingDateJPQL(@Param("viewingDate") String viewingDate);

    // BY DURATION
    @Query(value = "SELECT * FROM history WHERE duration = :duration", nativeQuery = true)
    List<History> getHistoriesByDuration(@Param("duration") int duration);

    @Query(value = "SELECT h FROM History h WHERE h.duration = :duration")
    List<History> getHistoriesByDurationJPQL(@Param("duration") int duration);

    // BY GENRE
    @Query(value = "SELECT * FROM history WHERE genre = :genre", nativeQuery = true)
    List<History> getHistoriesByGenre(@Param("genre") String genre);

    @Query(value = "SELECT h FROM History h WHERE h.genre = :genre")
    List<History> getHistoriesByGenreJPQL(@Param("genre") String genre);

    // BY LETTER VIEWING DATE STARTING WITH
    @Query(value = "SELECT * FROM history WHERE viewingDate LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<History> getHistoriesByViewingDateStartingWith(@Param("letter") String letter);

    // BY LETTER GENRE STARTING WITH
    @Query(value = "SELECT * FROM history WHERE genre LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<History> getHistoriesByGenreStartingWith(@Param("letter") String letter);

}
