// import React from "react";
// import "./UserProfile.css";
// function UserProfile({ user }) {
//   return (
//     <>
// <div className="centered-div-container">
//   <div className="centered-div">
//     <div className="card mb-4">
//       <div className="card-body text-center">
//         <img
//           src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"
//           alt="avatar"
//           className="rounded-circle img-fluid"
//           style={{ width: "150px;" }}
//         />
//         <h5 className="my-3">{user.name}</h5>
//         <p className="text-muted mb-1">Full Stack Developer</p>
//         <p className="text-muted mb-4">Bay Area, San Francisco, CA</p>
//         <div className="d-flex justify-content-center mb-2">
//           <button type="button" className="btn btn-primary">
//             Follow
//           </button>
//           <button type="button" className="btn btn-outline-primary ms-1">
//             Message
//           </button>
//         </div>
//       </div>
//     </div>
//   </div>
//         <div className="card-body" style={{ width: "546px" }}>
//           <div className="form-card">
//             <form>
//               <div className="form-group">
//                 <label htmlFor="name">Full Name</label>
//                 <input
//                   type="text"
//                   className="form-control"
//                   placeholder="Enter Name"
//                 />
//               </div>
//               <div className="form-group">
//                 <label htmlFor="exampleInputEmail1">Email address</label>
//                 <input
//                   type="email"
//                   className="form-control"
//                   id="exampleInputEmail1"
//                   aria-describedby="emailHelp"
//                   placeholder="Enter email"
//                 />
//               </div>
//               <div className="form-group">
//                 <label htmlFor="name">Mobile</label>
//                 <input
//                   type="text"
//                   className="form-control"
//                   placeholder="Enter Mobile"
//                 />
//               </div>
//               <div className="form-group">
//                 <label htmlFor="name">Address</label>
//                 <input
//                   type="text"
//                   className="form-control"
//                   placeholder="Enter Address"
//                 />
//               </div>
//               <div className="form-group">
//                 <label htmlFor="exampleInputPassword1">Password</label>
//                 <input
//                   type="password"
//                   className="form-control"
//                   id="exampleInputPassword1"
//                   placeholder="Password"
//                 />
//               </div>
//               <div className="form-group">
//                 <label htmlFor="file">Profile Pic</label>
//                 <input
//                   type="file"
//                   className="form-control"
//                   placeholder="Profile Image"
//                 />
//               </div>
//               <div className=" align-content-xl-center mt-5">
//                 <button type="submit" class="btn btn-primary">
//                   Update
//                 </button>
//               </div>
//             </form>
//           </div>
//         </div>
//       </div>
//     </>
//   );
// }

// export default UserProfile;

import React from "react";
import { connect } from "react-redux";
import { updateUserProfile } from "../../../../Actions/UpdateUserAction"; // Import the action to update user profile
import "./UserProfile.css";
import { Col, Row } from "react-bootstrap";

class UserProfile extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      fullName: "",
      username: "",
      email: "",
      mobile: "",
      address: "",
      password: "",
      securityQuestion: "",
      securityAnswer: "",
      profileImg: null,
    };
  }

  componentDidMount() {
    // Populate form fields with user data from props
    const { user } = this.props;
    if (user) {
      this.setState({
        fullName: user.fullName || "",
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
  }

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  handleFileChange = (event) => {
    this.setState({ profilePic: event.target.files[0] });
  };

  handleSubmit = (event) => {
    event.preventDefault();
    // Dispatch action to update user profile
    const {
      fullName,
      email,
      mobile,
      address,
      password,
      securityQuestion,
      securityAnswer,
      profileImg,
    } = this.state;
    const updatedProfile = {
      fullName,
      email,
      mobile,
      address,
      password,
      securityQuestion,
      profileImg,
      securityAnswer,
    };
    this.props.updateUserProfile(updatedProfile);
  };

  render() {
    const { user } = this.props;
    return (
      <div className="centered-div-container">
        <div className="centered-div">
          <div className="card mb-4">
            <div className="card-body text-center">
              <img
                src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp"
                alt="avatar"
                className="rounded-circle img-fluid"
                style={{ width: "150px" }}
              />
              <h5 className="my-3">{user.name}</h5>
              <p className="text-muted mb-1">Full Stack Developer</p>
              <p className="text-muted mb-4">Bay Area, San Francisco, CA</p>
              <div className="d-flex justify-content-center mb-2">
                <button type="button" className="btn btn-primary">
                  Follow
                </button>
                <button type="button" className="btn btn-outline-primary ms-1">
                  Message
                </button>
              </div>
            </div>
          </div>
        </div>
        <div className="card-body" style={{ width: "546px" }}>
          <div className="form-card">
            <form onSubmit={this.handleSubmit}>
              <Row>
                <Col>
                  <div className="form-group ">
                    <label htmlFor="name">Full Name</label>
                    <input
                      type="text"
                      className="form-control"
                      name="fullName"
                      placeholder="Enter Name"
                      value={this.state.fullName || user.name}
                      onChange={this.handleInputChange}
                    />
                  </div>
                </Col>
                <Col>
                  <div className="form-group">
                    <label htmlFor="name">User Name</label>
                    <input
                      type="text"
                      className="form-control"
                      name="userName"
                      placeholder="Enter User Name"
                      value={this.state.username || user.username}
                      onChange={this.handleInputChange}
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
                      value={this.state.email || user.email}
                      onChange={this.handleInputChange}
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
                      value={this.state.address || user.address}
                      onChange={this.handleInputChange}
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
                      value={this.state.mobile || user.mobile}
                      onChange={this.handleInputChange}
                    />
                  </div>
                </Col>
                <Col>
                  <div className="form-group">
                    <label htmlFor="address">Password</label>
                    <input
                      type="password"
                      className="form-control"
                      name="password"
                      placeholder="Enter Password"
                      value={this.state.password || user.password}
                      onChange={this.handleInputChange}
                    />
                  </div>
                </Col>
              </Row>
              <Row>
                <Col>
                  <div className="form-group">
                    <label htmlFor="address">Security Question</label>
                    <input
                      type="text"
                      className="form-control"
                      name="securityQuestion"
                      placeholder="Enter Name"
                      value={
                        this.state.securityQuestion || user.securityQuestion
                      }
                      onChange={this.handleInputChange}
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
                      value={this.state.securityAnswer || user.securityAnswer}
                      onChange={this.handleInputChange}
                    />
                  </div>
                </Col>
              </Row>

              <div className="form-group">
                <label htmlFor="file">Profile Pic</label>
                <input
                  type="file"
                  className="form-control"
                  name="profileImg"
                  onChange={this.handleFileChange}
                />
              </div>
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
  }
}

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
