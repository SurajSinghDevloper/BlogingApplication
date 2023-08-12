import React from "react";

const Sidebar = ({ setActivePage }) => {
  return (
    <div className="sidebar">
    <div className="mt5" style={{marginTop:"40px"}}>
      <ul>
        <li><button onClick={() => setActivePage("dashboard")}>Dashboard</button></li>
        <li><button onClick={() => setActivePage("profile")}>Profile</button></li>
        <li><button onClick={() => setActivePage("message")}>Messages</button></li>
        <li><button onClick={() => setActivePage("setting")}>Settings</button></li>
      </ul>
      </div>
    </div>
  );
};

export default Sidebar;
