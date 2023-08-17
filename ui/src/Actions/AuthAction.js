import RouteTo from "../Hoc/RouteTo";
import { authConstant } from "../Constants/AuthConstant";

// export const login = (user) => {
//   return async (dispatch) => {
//     dispatch({ type: authConstant.LOGIN_REQUEST });

//     try {
//       const res = await RouteTo.post(`/api/user/login`, { ...user });

//       if (res.status === 200) {
//         const { token, user } = res.data;
//         localStorage.setItem("token", token);
//         const userJson = user;
//         localStorage.setItem("user", JSON.stringify(userJson));

//         dispatch({
//           type: authConstant.LOGIN_SUCCESS,
//           payload: { token, user },
//         });
//       } else {
//         dispatch({
//           type: authConstant.LOGIN_FAILURE,
//           payload: { error: "Failed to log in, please try again." },
//         });
//       }
//     } catch (error) {
//       console.error("Error:", error);
//       dispatch({
//         type: authConstant.LOGIN_FAILURE,
//         payload: { error: "An error occurred while logging in." },
//       });
//     }
//   };
// };

export const login = (user) => {
  return async (dispatch) => {
    dispatch({ type: authConstant.LOGIN_REQUEST });

    try {
      const res = await RouteTo.post(`/api/user/login`, { ...user });

      if (res.status === 200) {
        const { token, user } = res.data;
        localStorage.setItem("token", token);
        const userJson = user;
        localStorage.setItem("user", JSON.stringify(userJson));

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
    const token = localStorage.getItem("token");
    console.log(token, "    token");
    const userJSON = localStorage.getItem("user");
    console.log(userJSON, "    userjson");

    if (token && userJSON) {
      const user = JSON.parse(userJSON);
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
