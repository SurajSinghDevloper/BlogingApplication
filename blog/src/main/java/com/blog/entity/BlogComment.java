package com.blog.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "blog_comment")
public class BlogComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String message;
    @Column(name = "likes")
    private int like;
    @CreationTimestamp
    private LocalDate date;
    @CreationTimestamp
    private LocalTime time;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "blog_id")
    private int blogId;

    public BlogComment() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BlogComment(int id, String message, int like, LocalDate date, LocalTime time, int userId, int blogId) {
        super();
        this.id = id;
        this.message = message;
        this.like = like;
        this.date = date;
        this.time = time;
        this.userId = userId;
        this.blogId = blogId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }
}
