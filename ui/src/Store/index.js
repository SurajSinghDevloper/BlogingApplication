import { applyMiddleware, legacy_createStore as createStore } from "redux";
import rootReducer from '../Reducer/index'
import thunk from "redux-thunk"

const store = createStore(rootReducer, applyMiddleware(thunk))


export default store;
