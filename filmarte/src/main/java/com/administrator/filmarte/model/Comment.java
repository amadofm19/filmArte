package com.administrator.filmarte.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "Entity representing a comment in the system.")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the comment.", example = "1", required = true)
    private int idComment;

    @Schema(description = "Author of the comment.", example = "Jane Doe", required = true)
    private String autor;

    @Schema(description = "Content of the comment.", example = "This is an excellent movie!", required = true)
    private String content;

    @Schema(description = "Type of the comment.", example = "Review", required = true)
    private String type;

    public int getIdComment(){
        return idComment;
    }
    public void setIdComment(int idComment){
        this.idComment = idComment;
    }
    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
}
