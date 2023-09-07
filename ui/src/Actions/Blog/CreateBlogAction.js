import RouteTo from "../../Hoc/RouteTo";
import { BLOGConstant } from "../../Constants/BlogConstant"; // Replace with your createBlog constants file
import { getCookie } from "../../Configuration/Cookies";

const user = localStorage.getItem("user");

// Action creator to create a new blog post
export const createBlog = (blogData) => {
  return async (dispatch) => {
    dispatch({ type: BLOGConstant.BLOG_CREATE_REQUEST });
    const formData = new FormData();
    formData.append("title", blogData.title);
    formData.append("content", blogData.content);
    formData.append("email", blogData.email);
    formData.append("imageFiles", blogData.imageFiles);
    console.log("DATA FROM COOKIES ", user);
    // Append each File individually
    for (const file of blogData.imageFiles) {
      formData.append("imageFiles", file);
    }

    const Authorization = {
      Authorization: getCookie("token"),
    };

    try {
      // Send a POST request to your backend API to create the blog
      const res = await RouteTo.post("/user/blogs/create", formData, {
        headers: Authorization,
      });

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
