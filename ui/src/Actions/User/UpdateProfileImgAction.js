import { userConstant } from "../../Constants/UserConstant";
import { getCookie } from "../../Configuration/Cookies";
import RouteTo from "../../Hoc/RouteTo";

export const updateUserProfileImage = (profileImage, email) => {
  return async (dispatch, getState) => {
    dispatch({ type: userConstant.UPDATE_PROFILE_IMG_REQUEST });
    const formData = new FormData();
    formData.append("email", email);
    formData.append("imageFile", profileImage);
    const Authorization = {
      Authorization: getCookie("token"),
    };
    try {
      const res = await RouteTo.post("/user/updateImage", formData, {
        headers: Authorization,
      });

      if (res.status === 200) {
        const updatedUser = res.data;
        console.log(updatedUser, "UPDATED USER DETAILS");

        // Store the updated user object in local storage
        localStorage.removeItem("user");
        localStorage.setItem("user", JSON.stringify(updatedUser));

        dispatch({
          type: userConstant.UPDATE_PROFILE_IMG_SUCCESS,
          payload: { user: updatedUser },
        });
      } else {
        dispatch({
          type: userConstant.UPDATE_PROFILE_IMG_FAILURE,
          payload: { error: "Failed to update profile, please try again." },
        });
      }
    } catch (error) {
      console.error("Error:", error);
      dispatch({
        type: userConstant.UPDATE_PROFILE_IMG_FAILURE,
        payload: { error: "An error occurred while updating profile." },
      });
    }
  };
};
