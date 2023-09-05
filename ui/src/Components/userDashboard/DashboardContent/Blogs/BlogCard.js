import React from "react";
import "./BlogCard.css";

const BlogCard = () => {
  return (
    <div>
      <div>
        <div className="col">
          <div className="col-md-9">
            <div className="blog-card">
              <img
                src="https://via.placeholder.com/400x250"
                alt="Image"
                className="blog-img"
              />
              <h2 className="blog-title">Blog Title</h2>
              <p className="blog-content">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
                vehicula, urna quis mattis vulputate.
              </p>
              <button className="btn btn-primary btn-like">Like</button>
              <button className="btn btn-secondary btn-dislike">Dislike</button>
              <div className="comment-section">
                <h4>Comments</h4>
                <div className="comment">Comment 1</div>
                <div className="comment">Comment 2</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BlogCard;
