import React, { useState } from "react";
import LoginModal from "./modals/LoginModal";

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
        <button
          className="navbar-toggler"
          type="button"
          data-toggle="collapse"
          data-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarNav">
          <button type="button" className="btn btn-primary" onClick={openModal}>
            Login
          </button>
        </div>
        <LoginModal isOpen={modalOpen} onClose={closeModal} />
      </div>

      {/* Modal */}
    </nav>
  );
};

export default NavBar;
