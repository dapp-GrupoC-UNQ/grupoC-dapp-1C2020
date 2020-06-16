import axios from 'axios';

const SERVICE_URL = 'http://localhost:8080/';

const StoreService = () => {
    const getAllStores = (category = '') => {
        return axios.get(`${SERVICE_URL}stores`, {
                    params: {
                        category: category}
                    })
    }

    const getAllStoresWithACategory = (category) => {
        return axios.get(`${SERVICE_URL}stores?category=${category}`)
    }

    const getStoreProducts = (storeId) => {
        return axios.get(`${SERVICE_URL}stores/${storeId}/products`)
    }

    const getStoreById = (storeId) => {
        return axios.get(`${SERVICE_URL}stores/${storeId}`)
    }
    return {
        getAllStores: getAllStores,
        getAllStoresWithACategory: getAllStoresWithACategory,
        getStoreProducts: getStoreProducts,
        getStoreById: getStoreById
    }
}

export default StoreService;

