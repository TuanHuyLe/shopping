import axios from "axios";

const API_URL = "/api/auth";

class AuthService {
    login(username, password) {
        return axios
            .post(API_URL + "/login", {
                username,
                password
            })
            .then(response => {
                if (response.data.token) {
                    localStorage.setItem("user", JSON.stringify(response.data));
                }
                return response.data;
            });
    }

    logout() {
        localStorage.removeItem("user");
    }

    register(username, password, name) {
        return axios.post(API_URL + "/signup", {
            username,
            password,
            name
        });
    }

    getCurrentUser() {
        return JSON.parse(localStorage.getItem("user"));
    }
}

export default new AuthService();