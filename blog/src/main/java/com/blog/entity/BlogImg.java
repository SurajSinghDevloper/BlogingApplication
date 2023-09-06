package com.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="blogImage")
public class BlogImg {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id")
    private int imageId;

    @Column(name = "image_name")
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;
    
    
    
	public BlogImg() {
		super();
		// TODO Auto-generated constructor stub
	}



	public BlogImg(int imageId, String imageName, Blog blog) {
		super();
		this.imageId = imageId;
		this.imageName = imageName;
		this.blog = blog;
	}



	public int getImageId() {
		return imageId;
	}



	public void setImageId(int imageId) {
		this.imageId = imageId;
	}



	public String getImageName() {
		return imageName;
	}



	public void setImageName(String imageName) {
		this.imageName = imageName;
	}



	public Blog getBlog() {
		return blog;
	}



	public void setBlog(Blog blog) {
		this.blog = blog;
	}
    
    
}
