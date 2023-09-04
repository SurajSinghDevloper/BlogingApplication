import React, { useState, useEffect } from "react";
import { connect } from "react-redux";
import { updateUserProfile } from "../../../../Actions/UpdateUserAction"; // Import the action to update user profile
import "./UserProfile.css";
import { Col, Row } from "react-bootstrap";

const UserProfile = ({ user, updateUserProfile }) => {
  const [formData, setFormData] = useState({
    name: "",
    username: "",
    email: "",
    mobile: "",
    address: "",
    password: "",
    securityQuestion: "",
    securityAnswer: "",
    profileImg: "",
  });

  useEffect(() => {
    // Populate form fields with user data from props
    if (user) {
      setFormData({
        name: user.name || "",
        username: user.username || "",
        email: user.email || "",
        mobile: user.mobile || "",
        address: user.address || "",
        password: user.password || "",
        securityQuestion: user.securityQuestion || "",
        profileImg: user.profileImg || "",
        securityAnswer: user.securityAnswer || "",
      });
    }
  }, [user]);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleImageChange = (e) => {
    setFormData({ ...formData, profileImg: e.target.files[0] });
  };

  const handleProfileImageChange = (e) => {
    setFormData({ profileImg: e.target.files[0] });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    // Dispatch action to update user profile
    updateUserProfile(formData);
  };

  return (
    <div className="centered-div-container">
      <div className="centered-div">
        <div className="card mb-4">
          <div className="card-body text-center">
            <img
              src={
                process.env.REACT_APP_BASE_URL +
                "/user/images/" +
                user.profileImg
              }
              alt="avatar"
              className="rounded-circle img-fluid"
              style={{ width: "150px" }}
            />
            <h5 className="my-3">{user.name}</h5>
            <p className="text-muted mb-1">Full Stack Developer</p>
            <p className="text-muted mb-4">{user.address}</p>

            <input
              type="file"
              className="form-control"
              name="profileImg"
              onChange={handleImageChange}
            />
            <div className="d-flex justify-content-center mb-2">
              <button type="button" className="btn btn-primary">
                Update Image
              </button>
            </div>
          </div>
        </div>
      </div>
      <div className="card-body" style={{ width: "546px" }}>
        <div className="form-card">
          <form onSubmit={handleSubmit}>
            <Row>
              <Col>
                <div className="form-group ">
                  <label htmlFor="name">Full Name</label>
                  <input
                    type="text"
                    className="form-control"
                    name="name"
                    placeholder="Enter Name"
                    value={formData.name}
                    onChange={handleInputChange}
                  />
                </div>
              </Col>
              <Col>
                <div className="form-group">
                  <label htmlFor="name">User Name</label>
                  <input
                    type="text"
                    className="form-control"
                    name="username"
                    placeholder="Enter User Name"
                    value={formData.username}
                    onChange={handleInputChange}
                  />
                </div>
              </Col>
            </Row>
            <Row>
              <Col>
                <div className="form-group">
                  <label htmlFor="exampleInputEmail1">Email address</label>
                  <input
                    type="email"
                    className="form-control"
                    name="email"
                    placeholder="Enter email"
                    value={formData.email}
                    onChange={handleInputChange}
                  />
                </div>
              </Col>
              <Col>
                <div className="form-group">
                  <label htmlFor="address">Address</label>
                  <input
                    type="text"
                    className="form-control"
                    name="address"
                    placeholder="Enter Name"
                    value={formData.address}
                    onChange={handleInputChange}
                  />
                </div>
              </Col>
            </Row>
            <Row>
              <Col>
                <div className="form-group">
                  <label htmlFor="address">Mobile</label>
                  <input
                    type="number"
                    className="form-control"
                    name="mobile"
                    placeholder="Enter Mobile"
                    value={formData.mobile}
                    onChange={handleInputChange}
                  />
                </div>
              </Col>
              <Col>
                <div className="form-group">
                  <label htmlFor="address">Security Question</label>
                  <input
                    type="text"
                    className="form-control"
                    name="securityQuestion"
                    placeholder="Enter Name"
                    value={formData.securityQuestion}
                    onChange={handleInputChange}
                  />
                </div>
              </Col>
            </Row>
            <Row>
              <Col>
                <div className="form-group">
                  <label htmlFor="file">Profile Pic</label>
                  <input
                    type="file"
                    className="form-control"
                    name="profileImg"
                    onChange={handleImageChange}
                  />
                </div>
              </Col>
              <Col>
                <div className="form-group">
                  <label htmlFor="securityAnswer">Security Answer</label>
                  <input
                    type="text"
                    className="form-control"
                    name="securityAnswer"
                    placeholder="Enter Security Answer"
                    value={formData.securityAnswer}
                    onChange={handleInputChange}
                  />
                </div>
              </Col>
            </Row>

            <div className="align-content-xl-center mt-5">
              <button type="submit" className="btn btn-primary">
                Update
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = (state) => {
  return {
    user: state.auth.user,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    updateUserProfile: (profileData) =>
      dispatch(updateUserProfile(profileData)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(UserProfile);
