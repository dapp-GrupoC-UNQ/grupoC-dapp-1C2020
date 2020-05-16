import axios from 'axios';
const SERVICE_URL = 'http://localhost:8080/';

const LoginService = () => {

    const validateUser = (user) => {
        return axios.post(`${SERVICE_URL}validateUser`, user)
    }

    const registerUser = (user) => {
        return axios.post(`${SERVICE_URL}users`, user)
    }

    return {
        validateUser: validateUser,
        registerUser: registerUser
    }
}

export default LoginService;