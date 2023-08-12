// import React, { useState } from "react";
// import LoginModal from "./modals/LoginModal";
// import SignupModal from "./modals/SignupModal";

// const NavBar = ({ loggedIn, onLogout }) => {
//   const [modalOpen, setModalOpen] = useState(false);
//   const [modalSignupOpen, setSignupModalOpen] = useState(false);

//   const openModal = () => {
//     setModalOpen(true);
//   };

//   const closeModal = () => {
//     setModalOpen(false);
//   };

//   const openSignupModal = () => {
//     setSignupModalOpen(true);
//   };

//   const closeSignupModal = () => {
//     setSignupModalOpen(false);
//   };

//   return (
//     <nav className="navbar navbar-expand-lg navbar-light bg-light">
//       <div className="container">
//         <a className="navbar-brand" href="/">
//           Your App
//         </a>

//         <div style={{ float: "right", justifyContent: "right" }}>
//           {loggedIn ? (
//             <button type="button" className="btn btn-primary" onClick={onLogout}>
//               Logout
//             </button>
//           ) : (
//             <>
//               <button type="button" className="btn btn-primary" onClick={openModal}>
//                 Login
//               </button>
//               <button type="button" className="btn btn-primary" onClick={openSignupModal} style={{ marginLeft: "10px" }}>
//                 Signup
//               </button>
//             </>
//           )}
//         </div>
//         <LoginModal isOpen={modalOpen} onClose={closeModal} />
//         <SignupModal isOpen={modalSignupOpen} onClose={closeSignupModal} />
//       </div>
//     </nav>
//   );
// };

// export default NavBar;

import React, { useState } from "react";
import LoginModal from "../modals/LoginModal";
import SignupModal from "../modals/SignupModal";

const NavBar = () => {
  const [modalOpen, setModalOpen] = useState(false);
  const [modalSignupOpen, setSignupModalOpen] = useState(false); 

  const openModal = () => {
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  const openSignupModal = () => {
    setSignupModalOpen(true);
  };

  const closeSignupModal = () => {
    setSignupModalOpen(false);
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="container">
        <a className="navbar-brand" href="/">
          Your App
        </a>
        <div style={{ float: "right", justifyContent: "right" }}>
              <button type="button" className="btn btn-primary" onClick={openModal}>
                Login
              </button>
              <button type="button" className="btn btn-primary" onClick={openSignupModal} style={{ marginLeft: "10px" }}>
                Signup
              </button>
        </div>
      </div>
      <LoginModal isOpen={modalOpen} onClose={closeModal} />
      <SignupModal isOpen={modalSignupOpen} onClose={closeSignupModal} />
    </nav>
  );
};

export default NavBar;

