import React, { useState, useEffect } from "react";
import { useSelector, useDispatch } from "react-redux";
import { updateUserProfile } from "../../../../Actions/User/UpdateUserAction"; // Import both actions from the correct file
import "./UserProfile.css";
import { updateUserProfileImage } from "../../../../Actions/User/UpdateProfileImgAction";
import { Col, Row } from "react-bootstrap";

const UserProfile = () => {
  const user = useSelector((state) => state.auth.user);
  const dispatch = useDispatch();
  const [profileImage, setProfileImage] = useState(null);
  const imageUrl = `${process.env.REACT_APP_BASE_URL}/user/images/${user.profileImg}`;
  const [imagePreviewUrl, setImagePreviewUrl] = useState(imageUrl);
  const [formData, setFormData] = useState({
    name: "",
    bio: "",
    email: "",
    mobile: "",
    address: "",
    password: "",
    securityQuestion: "",
    securityAnswer: "",
    gender: "",
  });

  useEffect(() => {
    if (user) {
      setFormData({
        name: user.name || "",
        bio: user.bio || "",
        email: user.email || "",
        mobile: user.mobile || "",
        address: user.address || "",
        password: user.password || "",
        securityQuestion: user.securityQuestion || "",
        gender: user.gender || "",
        securityAnswer: user.securityAnswer || "",
      });
    }
  }, [user]);

  const handleInputChange = (event) => {
    const { name, value } = event.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleProfileImageSubmit = (e) => {
    e.preventDefault();
    if (profileImage) {
      const newImageUrl = URL.createObjectURL(profileImage);
      setImagePreviewUrl(newImageUrl);
      dispatch(updateUserProfileImage(profileImage, user.email, newImageUrl));
      setProfileImage(null);
    }
  };

  const handleProfileImageChange = (e) => {
    setProfileImage(e.target.files[0]);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    dispatch(updateUserProfile(formData));
  };
  return (
    <div className="centered-div-container">
      <div className="centered-div">
        <div className="card mb-4">
          <div className="card-body text-center">
            <img
              src={imagePreviewUrl}
              alt="avatar"
              className="rounded-circle img-fluid"
              style={{ maxWidth: "150px", maxHeight: "200px" }}
            />
            <h5 className="my-3">{user.name}</h5>
            <p className="text-muted mb-1">{user.bio}</p>
            <p className="text-muted mb-4">{user.address}</p>

            <input
              type="file"
              className="form-control"
              name="profileImage"
              onChange={handleProfileImageChange}
            />
            <div className="d-flex justify-content-center mb-2">
              <button
                type="button"
                onClick={handleProfileImageSubmit}
                className="btn btn-primary"
                disabled={!profileImage}
              >
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
                  <label htmlFor="name">Your Bio</label>
                  <input
                    type="text"
                    className="form-control"
                    name="bio"
                    placeholder="Enter You bio Status"
                    value={formData.bio}
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
                  <label htmlFor="file">Your Gender</label>
                  <select
                    className="form-control"
                    name="gender"
                    value={formData.gender}
                    onChange={handleInputChange}
                  >
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                  </select>
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

export default UserProfile;
