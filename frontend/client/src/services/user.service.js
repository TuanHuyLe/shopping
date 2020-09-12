import axios from 'axios';
import authHeader from './auth-header';

const API_URL = '/api/shopping';

class UserService {
    getAllProducts() {
        return axios.get(API_URL + '/products');
    }

    createProduct() {
        return axios.post(API_URL + '/product', { headers: authHeader() });
    }

    updateProduct() {
        return axios.put(API_URL + '/product', { headers: authHeader() });
    }

    getAdminBoard() {
        return axios.get(API_URL + 'admin', { headers: authHeader() });
    }
}

export default new UserService();