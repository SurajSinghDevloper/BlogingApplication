import axios from "axios";
import React, { useState } from "react";
import { Modal, Button, Alert } from "react-bootstrap";
import { useHistory } from "react-router-dom";

const LoginModal = ({ isOpen, onClose }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();
  const handleSaveChanges = async() => {
    console.log("Email:", email);
    console.log("Password:", password);
    const user ={email,password};
    
      try {
        if(email !== "" || password !==""){
          const response = await axios.post("http://localhost:8081/api/user/login", user);
          console.log("Response:", response.data);
          if(response.data===true){
            history.push("/dashboard");
          }else{
            Alert("Invalid Credential Try Again")
          }
          
        }else{
          Alert("All Fields Are Important")
        }
        onClose();
      } catch (error) {
        console.error("Error:", error);
      }
    // Close the modal after handling the data
    onClose();
    
  };

  return (
    <Modal show={isOpen} onHide={onClose}>
      <Modal.Header closeButton>
        <Modal.Title>Login</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <form>
          <div className="mb-3">
            <label for="exampleInputEmail1" className="form-label">
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
            <div class="mb-3">
              <label for="exampleInputPassword1" className="form-label">
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
        <Button variant="primary" onClick={handleSaveChanges}>Save changes</Button>
      </Modal.Footer>
    </Modal>
  );
};

export default LoginModal;
