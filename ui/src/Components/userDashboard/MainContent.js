import React from "react";
import Lobby from "./DashboardContent/Lobby";
import UserProfile from "./DashboardContent/UserProfile/UserProfile";
import Messages from "./DashboardContent/Messages/Messages";
import { useDispatch } from "react-redux";
import { signout } from "../../Actions/AuthAction";
import { useHistory } from "react-router-dom";

const MainContent = ({ activePage }) => {
  const dispatch = useDispatch();
  const history = useHistory();
  const handleLogout = () => {
    dispatch(signout());
    history.push("/");
  };
  const renderPage = () => {
    switch (activePage) {
      case "dashboard":
        return <Lobby />;
      case "profile":
        return <UserProfile />;
      case "message":
        return <Messages />;
      case "setting":
        return <h1>Settings Page</h1>;
      default:
        return null;
    }
  };

  return (
    <div className="main-container">
      <header className="header">
        <a
          href="/"
          onClick={handleLogout}
          className="btn btn-light"
          style={{ float: "right" }}
        >
          Logout
        </a>
        <h3>User Singh</h3>
      </header>
      <div
        className="input-group"
        style={{
          backgroundColor: "#fff",
        }}
      >
        <div className="form-outline">
          <input
            type="search"
            id="form1"
            className="form-control"
            style={{
              width: "900px",
              marginLeft: "100px",
              backgroundColor: "#fff",
            }}
          />
        </div>
        <button type="button" className="btn btn-primary">
          <i className="fa fa-search"></i>
        </button>
      </div>
      <div className="content">{renderPage()}</div>
    </div>
  );
};

export default MainContent;
