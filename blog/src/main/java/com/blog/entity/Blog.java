package com.blog.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int blogId;
	private String blogTitle;
	private String blogContent;
	@Column(name = "\"likes\"")
	private int likes;
	private LocalTime time;
	private LocalDate date;
	private int UserId;
	@Column(name = "username")
	private String username;
	
	@JsonIgnore
	@OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BlogImages> images;
	public int getBlogId() {
		return blogId;
	}
	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogContent() {
		return blogContent;
	}
	public void setBlogContent(String blogContent) {
		this.blogContent = blogContent;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getUser_id() {
		return UserId;
	}
	public void setUser_id(int UserId) {
		this.UserId = UserId;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String userName) {
		this.username = userName;
	}
	public List<BlogImages> getImages() {
		return images;
	}
	public void setImages(List<BlogImages> images) {
		this.images = images;
	}
	
	

}
