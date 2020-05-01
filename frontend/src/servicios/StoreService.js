import axios from 'axios';
const SERVICE_URL = 'http://localhost:8080/';

const StoreService = () => {
    const getAllStores = () => {
        debugger
        return axios.get(`${SERVICE_URL}stores`)
    }

    return {
        getAllStores: getAllStores
    }
}

export default StoreService();

