import { combineReducers } from "redux";
import authReducer from "./AuthReducer";
import userReducer from "./UserReducer";
import userUpdate from "./UpdateUserReducer";

const rootReducer = combineReducers({
  auth: authReducer,
  user: userReducer,
  updateUser: userUpdate,
});

export default rootReducer;
