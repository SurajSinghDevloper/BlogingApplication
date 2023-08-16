import { authConstant } from "../Constants/AuthConstant";

const initialState = {
    token: null,
    user: {
        name: '',
        email: '',
        mobile: '',
        password: ''
    },
    authenticate: false,
    authenticating: false
};

const reducer = (state = initialState, action) => {
    console.log("🚀 ~ file: auth.reducer.js:8 ~ reducer ~ action:", action);
    switch (action.type) {
        case authConstant.LOGIN_REQUEST:
            return {
                ...state,
                authenticating: true
            };
        case authConstant.LOGIN_SUCCESS:
            return {
                ...state,
                user: action.payload.user,
                token: action.payload.token,
                authenticate: true,
                authenticating: false
            };
        case authConstant.LOGOUT_REQUEST:
            return initialState
        default:
            return state;
    }
};

export default reducer;