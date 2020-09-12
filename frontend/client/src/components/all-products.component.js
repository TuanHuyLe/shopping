import React, { Component } from "react";

import UserService from "../services/user.service";

export default class Products extends Component {
    constructor(props) {
        super(props);

        this.state = {
            totalItems: 0,
            totalPages: 0,
            currentPage: 0,
            products: [],
            content: ''
        };
    }

    componentDidMount() {
        UserService.getAllProducts().then(
            response => {
                this.setState({
                    totalItems: response.data.totalItems,
                    totalPages: response.data.totalPages,
                    currentPage: response.data.currentPage,
                    products: response.data.products,
                    content: 'Danh sách sản phẩm'
                });
            },
            error => {
                this.setState({
                    content:
                        (error.response && error.response.data) ||
                        error.message ||
                        error.toString()
                });
            }
        );
    }

    render() {
        return (
            <div className="container">
                <header className="jumbotron">
                    <h3>Total Product {this.state.totalItems}</h3>
                    <h4>Total Page {this.state.totalPages}</h4>
                    <h4>Current Page {this.state.currentPage}</h4>
                    <h5>Danh sach san pham</h5>
                    <ul>
                        {this.state.products &&
                            this.state.products.map((product, index) => <li key={index}>{product.name} - {product.price} vnd</li>)}
                    </ul>
                </header>
            </div>
        );
    }
}