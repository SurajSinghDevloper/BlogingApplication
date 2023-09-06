import RouteTo from "../../Hoc/RouteTo";
import { BLOGConstant } from "../../Constants/BlogConstant"; // Replace with your createBlog constants file

// Action creator to create a new blog post
export const createBlog = (blogData) => {
  return async (dispatch) => {
    dispatch({ type: BLOGConstant.BLOG_CREATE_REQUEST });

    try {
      // Send a POST request to your backend API to create the blog
      const res = await RouteTo.post("/api/create-blog", blogData);

      if (res.status === 201) {
        // Assuming a 201 status code for successful creation
        const createdBlog = res.data;

        dispatch({
          type: BLOGConstant.BLOG_CREATE_SUCCESS,
          payload: createdBlog,
        });
      } else {
        dispatch({
          type: BLOGConstant.BLOG_CREATE_FAILURE,
          payload: { error: "Failed to create a blog post, please try again." },
        });
      }
    } catch (error) {
      console.error("Error:", error);
      dispatch({
        type: BLOGConstant.BLOG_CREATE_FAILURE,
        payload: { error: "An error occurred while creating a blog post." },
      });
    }
  };
};
