import React, { useEffect } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import Home from "./Components/Home";
import "./App.css";
import Dashboard from "./Components/userDashboard/Dashboard";
import { useDispatch, useSelector } from 'react-redux';
import { isUserLoggedIn } from "./Actions/AuthAction";
import PrivateRoute from "./Hoc/PrivateRoute";

const App = () => {

  const auth = useSelector(state => state.auth);


  const dispatch = useDispatch ()

  useEffect(() => {
    if (!auth.authenticate) {
      dispatch(isUserLoggedIn());
    }
  }, [])

  return (
    <Router>
      <Switch>
        <Route path="/" exact component={Home} />
        <PrivateRoute path="/dashboard" exact component={Dashboard} />
      </Switch>
    </Router>
  );
};

export default App;
