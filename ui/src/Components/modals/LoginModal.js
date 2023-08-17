import React, { useState } from "react";
import { Modal, Button, Alert } from "react-bootstrap";
import { useDispatch } from 'react-redux';
import { login } from "../../Actions/AuthAction"
import { useHistory } from "react-router-dom";
import { Link } from 'react-router-dom';
import Dashboard from "../userDashboard/Dashboard";


const LoginModal = ({ isOpen, onClose }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const dispatch = useDispatch();
  const history = useHistory();


  const handleSaveChanges = async () => {
    const user = { email, password };
    try {
      if (email !== "" || password !== "") {
       const res= dispatch(login(user));
       if(res){
        history.push("/dashboard");
       }
        
      } else {
        Alert("All Fields Are Important");
      }
    } catch (error) {
      console.error("Error:", error);
    }
    
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
        <Button variant="primary" onClick={handleSaveChanges}>Save changes</Button>
      </Modal.Footer>
    </Modal>
  );
};

export default LoginModal;
