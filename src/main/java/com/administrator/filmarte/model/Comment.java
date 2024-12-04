package com.administrator.filmarte.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "comment")
@Schema(description = "Entity representing a comment in the system.")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the comment.", example = "1", required = true)
    @Column(name = "idComment")
    @JsonProperty("idComment")
    private int idComment;

    @Schema(description = "Author of the comment.", example = "John Doe", required = true)
    @NotBlank(message = "Author name is mandatory")
    @Size(min = 1, max = 50, message = "Author name must be between 1 and 50 characters")
    @Column(name = "author")
    @JsonProperty("author")
    private String author;

    @Schema(description = "Content of the comment.", example = "This is a great movie!", required = true)
    @NotBlank(message = "Comment content is mandatory")
    @Size(min = 1, max = 500, message = "Content must be between 1 and 500 characters")
    @Column(name = "content")
    @JsonProperty("content")
    private String content;

    @Schema(description = "Sentiment analysis of the comment. Automatically generated by the system.", accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("analysis")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "analysis")
    private String analysis;

    @ManyToOne
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    @JsonIgnore
    private User user;

    public Comment() {
    }

    public Comment(int idComment, String author, String content, User user) {
        this.idComment = idComment;
        this.author = author;
        this.content = content;
        this.user = user;
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    @Override
    public String toString() {
        return "Comment{" + "idComment=" + idComment + ", author=" + author + ", content=" + content + ", user=" + user + '}';
    }
}
