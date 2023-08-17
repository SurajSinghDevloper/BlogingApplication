import React from "react";
import "./BlogCard.css";

const BlogCard = () => {
  return (
    <div>
      <div>
        <div class="col">
          <div class="col-md-9">
            <div class="blog-card">
              <img
                src="https://via.placeholder.com/400x250"
                alt="Blog Image"
                class="blog-img"
              />
              <h2 class="blog-title">Blog Title</h2>
              <p class="blog-content">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
                vehicula, urna quis mattis vulputate.
              </p>
              <button class="btn btn-primary btn-like">Like</button>
              <button class="btn btn-secondary btn-dislike">Dislike</button>
              <div class="comment-section">
                <h4>Comments</h4>
                <div class="comment">Comment 1</div>
                <div class="comment">Comment 2</div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BlogCard;
