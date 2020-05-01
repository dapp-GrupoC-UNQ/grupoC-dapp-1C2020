import axios from 'axios';
const SERVICE_URL = 'http://localhost:8080/';

const StoreService = () => {
    const getAllStores = () => {
        return axios.get(`${SERVICE_URL}stores`)
    }

    return {
        //Se pasa la referencia para que solamente se hagan llamadas al backend cuando sea necesario
        //Cada una de estas funciones al ser invocadas devuelven una promise
        getAllStores: getAllStores
    }
}

export default StoreService;

