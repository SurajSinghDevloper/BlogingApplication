import React, { useState } from "react";
import Sidebar from "./Sidebar";
import MainContent from "./MainContent";

const Dashboard = (props) => {
  const [activePage, setActivePage] = useState("dashboard");

  return (
    <div className="dashboard-container">
      <Sidebar setActivePage={setActivePage} />
      <MainContent activePage={activePage} />
    </div>
  );
};

export default Dashboard;
