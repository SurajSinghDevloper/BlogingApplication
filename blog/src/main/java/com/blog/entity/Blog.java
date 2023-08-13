package com.blog.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blog_id")
    private int blogId;
    private String title;
    private String content;
    @Column(name = "blog_img")
    private String blogImg;
    @Column(name = "\"likes\"") // Escaped the column name "likes"
    private int likes;
    private LocalDate date;
    @Column(name = "time")
    private LocalTime time;

    @Column(name = "user_id") // Store only the userId as a foreign key reference
    private int userId;

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public Blog() {
        super();
    }

    public Blog(int blogId, String title, String content, String blogImg, int likes, LocalDate date, LocalTime time,
            int userId) {
        super();
        this.blogId = blogId;
        this.title = title;
        this.content = content;
        this.blogImg = blogImg;
        this.likes = likes;
        this.date = date;
        this.time = time;
        this.userId = userId;
    }

    // Getters and setters for other fields

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * @return String return the blogImg
     */
    public String getBlogImg() {
        return blogImg;
    }

    /**
     * @param blogImg the blogImg to set
     */
    public void setBlogImg(String blogImg) {
        this.blogImg = blogImg;
    }

    /**
     * @return int return the likes
     */
    public int getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * @return LocalTime return the time
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

}
