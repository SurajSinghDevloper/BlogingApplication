import React, { useState } from "react";
import LoginModal from "./modals/LoginModal";
import SignupModal from "./modals/SignupModal";

const NavBar = (props) => {
  const [modalOpen, setModalOpen] = useState(false);

  const openModal = () => {
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container">
        <a className="navbar-brand" href="/">
          Your App
        </a>

        <div style={{ float: "right", justifyContent: "right", }} >
          <button type="button" className="btn btn-primary" onClick={openModal} >
            Login
          </button>

          <button type="button" className="btn btn-primary" onClick={openModal} style={{ marginLeft: "10px" }}>
            Signup
          </button>
        </div>
        <LoginModal isOpen={modalOpen} onClose={closeModal} />
        <SignupModal isOpen={modalOpen} onClose={closeModal} />
      </div>

      {/* Modal */}
    </nav >
  );
};

export default NavBar;
