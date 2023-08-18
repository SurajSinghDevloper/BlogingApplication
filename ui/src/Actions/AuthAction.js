import RouteTo from "../Hoc/RouteTo";
import { authConstant } from "../Constants/AuthConstant";
import { getCookie, setCookie } from "../Configuration/Cookies";

export const login = (user) => {
  return async (dispatch) => {
    dispatch({ type: authConstant.LOGIN_REQUEST });

    try {
      const res = await RouteTo.post(`/api/user/login`, { ...user });

      if (res.status === 200) {
        const { token, user } = res.data;
        const userJson = user;
        localStorage.setItem("user", JSON.stringify(userJson));
        setCookie("token", JSON.stringify(token), 1);
        dispatch({
          type: authConstant.LOGIN_SUCCESS,
          payload: { token, user },
        });
      } else {
        dispatch({
          type: authConstant.LOGIN_FAILURE,
          payload: { error: "Failed to log in, please try again." },
        });
      }
    } catch (error) {
      console.error("Error:", error);
      dispatch({
        type: authConstant.LOGIN_FAILURE,
        payload: { error: "An error occurred while logging in." },
      });
    }
  };
};

export const isUserLoggedIn = () => {
  return (dispatch) => {
    let token = "";
    token = getCookie(token);
    console.log("TOKEN FROM isUserLoggedIn --------->>>>>", token);
    const userJSON = localStorage.getItem("user");

    if (token && userJSON) {
      const user = JSON.parse(userJSON);
      dispatch({
        type: authConstant.LOGIN_SUCCESS,
        payload: { token, user },
      });
    } else {
      dispatch({
        type: authConstant.LOGIN_FAILURE,
        payload: { error: "User is not logged in." },
      });
    }
  };
};

export const signout = () => {
  return (dispatch) => {
    localStorage.clear();
    dispatch({
      type: authConstant.LOGOUT_REQUEST,
    });
  };
};
