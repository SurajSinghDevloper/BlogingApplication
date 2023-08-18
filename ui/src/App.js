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
    console.log("From APP file ===== AUTH VALUE  =======", auth);
    if (!auth.authenticate) {
      dispatch(isUserLoggedIn());
      <Redirect to="/" />;
    }
  }, []);

  return (
    <Router>
      <Switch>
        <Route exact path="/" component={Home} />
        <PrivateRoute path="/dashboard" auth={auth} component={Dashboard} />
        <Route
          render={() =>
            auth.authenticate ? (
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
