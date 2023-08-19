import React, { useEffect } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import Home from "./Components/Home";
import "./App.css";
import Dashboard from "./Components/userDashboard/Dashboard";
import { useDispatch, useSelector } from "react-redux";
import { isUserLoggedIn } from "./Actions/AuthAction";
import PrivateRoute from "./Hoc/PrivateRoute";

const App = () => {
  const auth = useSelector((state) => {
    return state.auth;
  });

  const dispatch = useDispatch();

  useEffect(() => {
    console.log("From APP file ===== AUTH VALUE  =======", auth.authenticate);
    if (!auth.authenticate) {
      dispatch(isUserLoggedIn());
    }
  }, [auth.authenticate, dispatch]);

  return (
    <Router>
      <Switch>
        <Route exact path="/" component={Home} />
        {/* <PrivateRoute path="/dashboard" component={Dashboard} /> */}
        <PrivateRoute
          path="/dashboard"
          component={Dashboard}
          isAuthenticated={auth.authenticate}
        />
      </Switch>
    </Router>
  );
};

export default App;
