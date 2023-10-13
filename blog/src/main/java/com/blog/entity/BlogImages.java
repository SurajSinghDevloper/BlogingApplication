package com.blog.entity;
import javax.persistence.*;

@Entity
public class BlogImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageName;

    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;
    


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	
	
    // Constructors, getters, setters, and other methods
    
}
