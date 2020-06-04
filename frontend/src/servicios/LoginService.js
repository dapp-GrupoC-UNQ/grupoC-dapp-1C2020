import axios from 'axios';
const SERVICE_URL = 'http://localhost:8080/';

const LoginService = () => {

    const validateUser = (user) => {
        return axios.post(`${SERVICE_URL}validateUser`, user)
    }

    const registerUser = (user) => {
        return axios.post(`${SERVICE_URL}users`, user)
    }

    const registerStoreUser = (storeAdmin) => {
        const body = {
            username: storeAdmin.username,
            password: storeAdmin.password,
            store: storeAdmin.store
        }

        return axios.post(`${SERVICE_URL}storeAdmin`, body)
    }

    return {
        validateUser: validateUser,
        registerUser: registerUser,
        registerStoreUser: registerStoreUser
    }
}

export default LoginService;