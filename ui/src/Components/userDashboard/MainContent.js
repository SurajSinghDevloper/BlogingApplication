import React from "react";
import Lobby from "./DashboardContent/Lobby";

const MainContent = ({ activePage }) => {
  const renderPage = () => {
    switch (activePage) {
      case "dashboard":
        return <Lobby/>;
      case "profile":
        return <h1>Profile Page</h1>;
      case "message":
        return <h1>Messages Page</h1>;
      case "setting":
        return <h1>Settings Page</h1>;
      default:
        return null;
    }
  };

  return (
    <div className="main-container">
      <header className="header">
      <a href="/" className="btn btn-light" style={{float:"right"}}>Logout</a>
      <h3>User Singh</h3>
       
      </header>
      <div className="content">
        {renderPage()}
      </div>
    </div>
  );
};

export default MainContent;
