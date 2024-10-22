package com.administrator.filmarte.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Table;

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

    @Schema(description = "Author of the comment.", example = "Jane Doe", required = true)
    @NotBlank(message = "The author's name must not be null and must contain at least one non-whitespace character")
    @Size(min = 1, max = 50, message = "The author's name must be at most 50 characters, and has at least one character")
    @Column(name = "author")
    @JsonProperty("author")
    private String author;

    @Schema(description = "Content of the comment.", example = "This is an excellent movie!", required = true)
    @NotBlank(message = "The content must not be null and must contain at least one non-whitespace character")
    @Column(name = "content")
    @JsonProperty("content")
    private String content;

    @Schema(description = "Type of the comment.", example = "Review", required = true)
    @NotBlank(message = "The type must not be null and must contain at least one non-whitespace character")
    @Column(name = "type")
    @JsonProperty("type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser")
    @JsonProperty("idUser")
    @JsonBackReference
    private User idUser;


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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return idComment + " :: " + author + " :: " + content + " :: " + type;
    }
}
