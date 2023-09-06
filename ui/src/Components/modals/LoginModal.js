import React, { useState } from "react";
import { Modal, Button, Alert } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { login } from "../../Actions/User/AuthAction";
import { Redirect } from "react-router-dom/cjs/react-router-dom";

const LoginModal = ({ isOpen, onClose }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const dispatch = useDispatch();
  const auth = useSelector((state) => state.auth);
  const handleRedirect = () => {
    if (auth.authenticate === true) {
      return <Redirect to="/dashboard" />;
    } else {
      return null; // Return null if no redirection is required
    }
  };
  const handleSaveChanges = async () => {
    const user = { email, password };
    try {
      if (email !== "" || password !== "") {
        await dispatch(login(user));
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
        {handleRedirect()}
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={onClose}>
          Close
        </Button>
        <Button variant="primary" onClick={handleSaveChanges}>
          Save changes
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default LoginModal;
