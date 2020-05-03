import axios from 'axios';
const SERVICE_URL = 'http://localhost:8080/';

const LoginService = () => {

    const validateUser = (user) => {
        return axios.post(`${SERVICE_URL}validateUser`, user)
    }

    return {
        validateUser: validateUser
    }
}

export default LoginService;