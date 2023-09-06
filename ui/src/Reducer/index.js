import { combineReducers } from "redux";
import authReducer from "./AuthReducer";
import userReducer from "./UserReducer";
import userUpdate from "./UpdateUserReducer";
import updateUserImg from "./UpdateProfileImgReducer";
import createBlog from "./CreateBlogReducer";

const rootReducer = combineReducers({
  auth: authReducer,
  user: userReducer,
  updateUser: userUpdate,
  updateUserImg: updateUserImg,
  createBlog: createBlog,
});

export default rootReducer;
