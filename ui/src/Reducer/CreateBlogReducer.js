import { BLOGConstant } from "../Constants/BlogConstant"; // Import your createBlog constants file

// Define your initial state
const initialState = {
  isLoading: false,
  error: null,
  createdBlog: null,
};

// Reducer to handle blog creation actions
const createBlogReducer = (state = initialState, action) => {
  switch (action.type) {
    case BLOGConstant.BLOG_CREATE_REQUEST:
      return { ...state, isLoading: true, error: null, createdBlog: null };
    case BLOGConstant.BLOG_CREATE_SUCCESS:
      return {
        ...state,
        isLoading: false,
        createdBlog: action.payload,
        error: null,
      };
    case BLOGConstant.BLOG_CREATE_FAILURE:
      return {
        ...state,
        isLoading: false,
        createdBlog: null,
        error: action.payload.error,
      };
    default:
      return state;
  }
};

export default createBlogReducer;
