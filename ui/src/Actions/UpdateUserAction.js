import { userConstant } from "../Constants/UserConstant";
import { getCookie } from "../Configuration/Cookies";
import axios from "axios";

export const updateUserProfile = (profileData) => {
  return async (dispatch, getState) => {
    dispatch({ type: userConstant.UPDATE_PROFILE_REQUEST });
    const formData = new FormData();
    formData.append("name", profileData.name);
    formData.append("username", profileData.username);
    formData.append("email", profileData.email);
    formData.append("address", profileData.address);
    formData.append("mobile", profileData.mobile);
    // formData.append("password", profileData.password);
    formData.append("securityQuestion", profileData.securityQuestion);
    formData.append("securityAnswer", profileData.securityAnswer);
    formData.append("imageFile", profileData.profileImg);

    console.log("From Update User username ========  ", profileData.username);

    const tokenN = getCookie("token");
    console.log("token from update User  =====   ", tokenN);

    const Authorization = {
      Authorization: getCookie("token"),
    };

    try {
      // Assuming you have an API endpoint for updating user profile
      const res = await axios.post(
        "http://localhost:8081/user/updateUser",
        formData,
        {
          headers: Authorization,
        }
      );

      console.log("RESPONE FROM=======    ", res.status);
      if (res.status === 200) {
        const updatedUser = res.data.user;

        // Update user data in Redux store
        dispatch({
          type: userConstant.UPDATE_PROFILE_SUCCESS,
          payload: { user: updatedUser },
        });
      } else {
        dispatch({
          type: userConstant.UPDATE_PROFILE_FAILURE,
          payload: { error: "Failed to update profile, please try again." },
        });
      }
    } catch (error) {
      console.error("Error:", error);
      dispatch({
        type: userConstant.UPDATE_PROFILE_FAILURE,
        payload: { error: "An error occurred while updating profile." },
      });
    }
  };
};
