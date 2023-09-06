import React from "react";
import { Button, Col, ModalFooter, Row } from "react-bootstrap";
import BlogCard from "./BlogCard";

const CreateBlog = () => {
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
                    id="formGroupExampleInput"
                    placeholder="Example input placeholder"
                  />
                </div>
                <div className="mb-3">
                  <label className="form-label">Content</label>
                  <textarea
                    type="text"
                    className="form-control"
                    id="formGroupExampleInput2"
                    placeholder="Another input placeholder"
                    style={{ minHeight: "200px" }}
                  />
                </div>
                <div className="mb-3">
                  <label className="form-label">Images</label>
                  <input
                    type="file"
                    className="form-control"
                    id="formGroupExampleInput"
                    placeholder="Example input placeholder"
                  />
                </div>
                <div className="mb-5 ">
                  <Row>
                    <Col>
                      <Button type="button" className="btn btn-primary">
                        Create Blog
                      </Button>
                    </Col>
                    <Col>
                      <Button
                        type="button"
                        className="btn btn-danger"
                        style={{ marginLeft: "200px" }}
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
