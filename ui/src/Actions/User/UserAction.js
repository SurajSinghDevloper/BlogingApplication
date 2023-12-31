import RouteTo from "../../Hoc/RouteTo";
import { userConstant } from "../../Constants/UserConstant";

export const signup = (user) => {
  console.log("👉👉 ~~ file: auth.action.js:6 ~~ login ~~ user:", user);
  return async (dispatch) => {
    dispatch({ type: userConstant.USER_REGISTER_REQUEST });
    try {
      const res = await RouteTo.post(`/user/signup`, { ...user });

      if (res.status === 200) {
        const { message } = res.data;
        dispatch({
          type: userConstant.USER_REGISTER_SUCCESS,
          payload: { message },
        });
      } else {
        if (res.status === 400) {
          dispatch({
            type: userConstant.USER_REGISTER_FAILURE,
            payload: {
              error: res.data.error,
            },
          });
        }
      }
    } catch (error) {
      console.log("Error:", error);
      dispatch({ type: userConstant.USER_REGISTER_FAILURE });
    }
  };
};
