import axios from 'axios'

const api =process.env.BASEURL;
const RouteTo = axios.create({
    baseURL: api
    // headers: {
    //     'Authorization':''
    // }
});


export default RouteTo;