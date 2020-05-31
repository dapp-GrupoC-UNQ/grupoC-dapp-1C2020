import axios from 'axios';
const SERVICE_URL = 'http://localhost:8080/';

const LoginService = () => {

    const validateUser = (user) => {
        return axios.post(`${SERVICE_URL}validateUser`, user)
    }

    const registerUser = (user) => {
        return axios.post(`${SERVICE_URL}users`, user)
    }

    const registerStoreUser = (storeUser, store) => {
        const body = {
            username: storeUser.username,
            password: storeUser.password,
            store: store
        }

        return axios.post(`${SERVICE_URL}stores`, body)
    }

    return {
        validateUser: validateUser,
        registerUser: registerUser,
        registerStoreUser: registerStoreUser
    }
}

export default LoginService;