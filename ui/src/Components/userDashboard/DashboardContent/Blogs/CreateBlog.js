import React, { useState } from "react";
import { Button, Col, Row } from "react-bootstrap";
import BlogCard from "./BlogCard";
import { useDispatch, useSelector } from "react-redux";
import { createBlog } from "../../../../Actions/Blog/CreateBlogAction";

const CreateBlog = () => {
  const user = useSelector((state) => state.auth.user);
  const dispatch = useDispatch();
  const [blogData, setBlogData] = useState({
    title: "",
    content: "",
    email: user.email,
    imageFiles: [],
  });

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setBlogData({ ...blogData, [name]: value });
  };

  const handleImageUpload = (event) => {
    const files = event.target.files;
    setBlogData({
      ...blogData,
      imageFiles: [...blogData.imageFiles, ...files],
    });
  };
  console.log("From create-blog ", blogData.imageFiles);

  const handleCreateBlog = async () => {
    for (let i = 0; i < blogData.imageFiles.length; i++) {
      // Do something with the element if needed
      const imageFile = blogData.imageFiles[i];
      console.log(imageFile);
    }

    try {
      const response = await dispatch(createBlog(blogData));
      if (response) {
        alert("Data Saved");
      }
    } catch (error) {
      console.error("Error creating blog:", error);
    }
  };

  return (
    <>
      <form className="mb-4">
        <Row>
          <Col>
            <div
              className="banner btn-outline-dark"
              style={{
                border: "1px  #737373", // Add a 2px solid black border
                boxShadow: "0px 4px 6px rgba(0, 0, 0, 0.1)", // Add a box shadow
                padding: "10px",
              }}
            >
              <h3 className="text-center">Create-Blog</h3>
              <div className="col-12">
                <div className="mb-3">
                  <label className="form-label">Title</label>
                  <input
                    type="text"
                    className="form-control"
                    name="title" // Set the name to match the state property
                    value={blogData.title} // Bind the value to the state
                    onChange={handleInputChange}
                    placeholder="Example input placeholder"
                  />
                </div>
                <div className="mb-3">
                  <label className="form-label">Content</label>
                  <textarea
                    type="text"
                    className="form-control"
                    name="content" // Set the name to match the state property
                    value={blogData.content} // Bind the value to the state
                    onChange={handleInputChange}
                    placeholder="Another input placeholder"
                    style={{ minHeight: "200px" }}
                  />
                </div>
                <div className="mb-3">
                  <label className="form-label">imageFiles</label>
                  <input
                    type="file"
                    className="form-control"
                    name="imageFiles"
                    multiple // Allow multiple file selection
                    onChange={handleImageUpload}
                    placeholder="Example input placeholder"
                  />
                </div>
                <div className="mb-5 ">
                  <Row>
                    <Col>
                      <Button
                        type="button"
                        className="btn btn-primary"
                        onClick={handleCreateBlog}
                      >
                        Create Blog
                      </Button>
                    </Col>
                    <Col>
                      <Button
                        type="button"
                        className="btn btn-danger"
                        style={{ marginLeft: "200px" }}
                        onClick={() =>
                          setBlogData({
                            title: "",
                            content: "",
                            imageFiles: null,
                          })
                        }
                      >
                        Clear Blog
                      </Button>
                    </Col>
                  </Row>
                </div>
              </div>
            </div>
          </Col>
          <Col>
            <div
              className="col-6"
              style={{
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
                width: "100%",
              }}
            >
              {/* <h1 className="text-center">Preview</h1> */}
              <BlogCard className="card" />
            </div>
          </Col>
        </Row>
      </form>
    </>
  );
};

export default CreateBlog;
