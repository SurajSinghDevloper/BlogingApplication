package com.blog.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blog_id")
    private int blogId;
    private String title;
    private String content;

    
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<BlogImg> blogImages = new ArrayList<>();
    
    @Column(name = "\"likes\"") // Escaped the column name "likes"
    private int likes;
    private LocalDate date;
    @Column(name = "time")
    private LocalTime time;

    @Column(name = "user_id") // Store only the userId as a foreign key reference
    private int userId;

    public Blog() {
        super();
    }

	public Blog(int blogId, String title, String content, List<BlogImg> blogImages, int likes,
			LocalDate date, LocalTime time, int userId) {
		super();
		this.blogId = blogId;
		this.title = title;
		this.content = content;
		this.blogImages = blogImages;
		this.likes = likes;
		this.date = date;
		this.time = time;
		this.userId = userId;
	}

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
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

	public List<BlogImg> getBlogImages() {
		return blogImages;
	}

	public void setBlogImages(List<BlogImg> blogImages) {
		this.blogImages = blogImages;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
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

   

}
