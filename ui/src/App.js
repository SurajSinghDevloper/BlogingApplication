import React, { useEffect } from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Redirect,
} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import Home from "./Components/Home";
import "./App.css";
import Dashboard from "./Components/userDashboard/Dashboard";
import { useDispatch, useSelector } from "react-redux";
import { isUserLoggedIn } from "./Actions/AuthAction";
import PrivateRoute from "./Hoc/PrivateRoute";

const App = () => {
  const auth = useSelector((state) => state.auth);

  const dispatch = useDispatch();

  useEffect(() => {
    if (!auth.authenticate) {
      dispatch(isUserLoggedIn());
    }
  }, []);

  return (
    <Router>
      <Switch>
        <Route exact path="/" component={Home} />
        <Route exact path="/dashboard" component={Dashboard} />
        <Route
          exact
          path="/"
          render={() =>
            isUserLoggedIn ? (
              <Redirect to="/dashboard" />
            ) : (
              <Redirect to="/login" />
            )
          }
        />
      </Switch>
    </Router>
  );
};

export default App;
