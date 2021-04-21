package com.example.workshopspringbootmongodb.dto;

import com.example.workshopspringbootmongodb.domain.Post;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostDTO implements Serializable {

    public static final long serialVersionUID = 1L;

    private String id;
    private Date date;
    private String title;
    private String body;

    private AuthorDTO authorDTO;

    private List<CommentDTO> comments = new ArrayList<>();

    public PostDTO(){}

    public PostDTO(Post post){
        id = post.getId();
        date = post.getDate();
        title = post.getTitle();
        body = post.getBody();
        authorDTO = post.getAuthor();
        comments = post.getComments();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public AuthorDTO getAuthorDTO() {
        return authorDTO;
    }

    public void setAuthorDTO(AuthorDTO authorDTO) {
        this.authorDTO = authorDTO;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

}
