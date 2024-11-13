package com.administrator.filmarte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.administrator.filmarte.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findByAuthor(String author);
    List<Comment> findByType(String type);
    List<Comment> findByContentContaining(String content);

    // BY ID
    @Query(value = "SELECT * FROM comment WHERE idComment = :idComment", nativeQuery = true)
    Comment getCommentById(@Param("idComment") int idComment);

    @Query(value = "SELECT c FROM Comment c WHERE c.idComment = :idComment")
    Comment getCommentByIdJPQL(@Param("idComment") int idComment);

    // BY AUTHOR
    @Query(value = "SELECT * FROM comment WHERE author = :author", nativeQuery = true)
    List<Comment> getCommentsByAuthor(@Param("author") String author);

    @Query(value = "SELECT c FROM Comment c WHERE c.author = :author")
    List<Comment> getCommentsByAuthorJPQL(@Param("author") String author);

    // BY CONTENT
    @Query(value = "SELECT * FROM comment WHERE content = :content", nativeQuery = true)
    List<Comment> getCommentsByContent(@Param("content") String content);

    @Query(value = "SELECT c FROM Comment c WHERE c.content = :content")
    List<Comment> getCommentsByContentJPQL(@Param("content") String content);

    // BY TYPE
    @Query(value = "SELECT * FROM comment WHERE type = :type", nativeQuery = true)
    List<Comment> getCommentsByType(@Param("type") String type);

    @Query(value = "SELECT c FROM Comment c WHERE c.type = :type")
    List<Comment> getCommentsByTypeJPQL(@Param("type") String type);

    // BY LETTER AUTHOR STARTING WITH
    @Query(value = "SELECT * FROM comment WHERE author LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Comment> getCommentsByAuthorStartingWith(@Param("letter") String letter);

    // BY LETTER CONTENT STARTING WITH
    @Query(value = "SELECT * FROM comment WHERE content LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Comment> getCommentsByContentStartingWith(@Param("letter") String letter);

    // BY LETTER TYPE STARTING WITH
    @Query(value = "SELECT * FROM comment WHERE type LIKE CONCAT(:letter, '%')", nativeQuery = true)
    List<Comment> getCommentsByTypeStartingWith(@Param("letter") String letter);

}