import React, { useState } from "react";
import { Modal, Button, Alert } from "react-bootstrap";
import axios from "axios";


const SignupModal = ({ isOpen, onClose }) => {
  const [fullName, setFullName] = useState("");
  const [email, setEmail] = useState("");
  const [mobile, setMobile] = useState("");
  const [password, setPassword] = useState("");


  const handleSaveChanges = async() => {
    console.log("Full Name:", fullName);
    console.log("Email:", email);
    console.log("Mobile:", mobile);
    console.log("Password:", password);
    const user ={fullName,
      email,
      mobile,
      password,};

      try {
        if(fullName !== ""||email !== "" || mobile !==""|| password !==""){
          const response = await axios.post("http://localhost:8081/api/signup/user", user);
          console.log("Response:", response.data);
        }else{
          Alert("All Fields Are Important")
        }
        onClose();
        user.clear();
      } catch (error) {
        console.error("Error:", error);
      }
    // Close the modal after handling the data
    onClose();
    
  };

  return (
    <Modal show={isOpen} onHide={onClose}>
      <Modal.Header closeButton>
        <Modal.Title>Signup</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <form>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Full Name
            </label>
            <input
              type="text"
              className="form-control"
              id="name"
              value={fullName}
              onChange={(e) => setFullName(e.target.value)}
              aria-describedby="name"
            />
            <label htmlFor="exampleInputEmail1" className="form-label">
              Email address
            </label>
            <input
              type="email"
              className="form-control"
              value={email}
              id="exampleInputEmail1"
              onChange={(e) => setEmail(e.target.value)}
              aria-describedby="emailHelp"
            />
            <label htmlFor="mobile" className="form-label">
              Mobile Number
            </label>
            <input
              type="text"
              className="form-control"
              id="mobile"
              value={mobile}
              onChange={(e) => setMobile(e.target.value)}
              aria-describedby="mobile"
            />
            <div className="mb-3">
              <label htmlFor="exampleInputPassword1" className="form-label">
                Password
              </label>
              <input
                type="password"
                className="form-control"
                id="exampleInputPassword1"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
          </div>
        </form>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onClose}>
          Close
        </Button>
        <Button variant="primary" onClick={handleSaveChanges}>
          Register 
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default SignupModal;
