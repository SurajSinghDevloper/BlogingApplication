import React from "react";
import Lobby from "./DashboardContent/Lobby/Lobby";
import UserProfile from "./DashboardContent/UserProfile/UserProfile";
import Messages from "./DashboardContent/Messages/Messages";
import { useDispatch, useSelector } from "react-redux";
import { signout } from "../../Actions/User/AuthAction";
import { useHistory } from "react-router-dom";
import CreateBlog from "./DashboardContent/Blogs/CreateBlog";
import ViewBlogs from "./DashboardContent/Blogs/ViewBlogs";

const MainContent = ({ activePage }) => {
  const user = useSelector((state) => state.auth.user);
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
      case "createBlog":
        return <CreateBlog />;
      case "viewBlogs":
        return <ViewBlogs />;
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
        <h3 style={{ fontFamily: "Raleway" }}>{user.name}</h3>
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
