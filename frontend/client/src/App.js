import React, { Component } from "react";
import { Switch, Route, Link, NavLink } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./services/auth.service";

import Login from "./components/login.component";
import Register from "./components/signup.component";
import Products from "./components/all-products.component";
import Profile from "./components/profile.component";
import BoardUser from "./components/board-user.component";
import BoardDevelop from "./components/board-develop.component";
import BoardAdmin from "./components/board-admin.component";

class App extends Component {
    constructor(props) {
        super(props);
        this.logOut = this.logOut.bind(this);

        this.state = {
            showDevelopBoard: false,
            showAdminBoard: false,
            currentUser: undefined,
        };
    }

    componentDidMount() {
        const user = AuthService.getCurrentUser();

        if (user) {
            this.setState({
                currentUser: user,
                showDevelopBoard: user.roles.includes("ROLE_DEVELOP"),
                showAdminBoard: user.roles.includes("ROLE_ADMIN"),
            });
        }
    }

    logOut() {
        AuthService.logout();
    }

    render() {
        const { currentUser, showDevelopBoard, showAdminBoard } = this.state;

        return (
            <div>
                <nav className="navbar navbar-expand navbar-dark bg-dark">
                    <Link to={"/"} className="navbar-brand">
                        SHOPPING
                    </Link>
                    <div className="navbar-nav mr-auto">
                        <li className="nav-item">
                            <NavLink to={"/products"} activeClassName="active" className="nav-link">
                                Products
                            </NavLink>
                        </li>

                        {showDevelopBoard && (
                            <li className="nav-item">
                                <NavLink to={"/dev"} activeClassName="active" className="nav-link">
                                    Develop Board
                                </NavLink>
                            </li>
                        )}

                        {showAdminBoard && (
                            <li className="nav-item">
                                <NavLink to={"/admin"} activeClassName="active" className="nav-link">
                                    Admin Board
                                </NavLink>
                            </li>
                        )}

                        {currentUser && (
                            <li className="nav-item">
                                <NavLink to={"/user"} activeClassName="active" className="nav-link">
                                    User
                                </NavLink>
                            </li>
                        )}
                    </div>

                    {currentUser ? (
                        <div className="navbar-nav ml-auto">
                            <li className="nav-item">
                                <Link to={"/profile"} className="nav-link">
                                    {currentUser.name}
                                </Link>
                            </li>
                            <li className="nav-item">
                                <a href="/login" className="nav-link" onClick={this.logOut}>
                                    LogOut
                                </a>
                            </li>
                        </div>
                    ) : (
                            <div className="navbar-nav ml-auto">
                                <li className="nav-item">
                                    <NavLink to={"/login"} activeClassName="active" className="nav-link">
                                        Login
                                    </NavLink>
                                </li>

                                <li className="nav-item">
                                    <NavLink to={"/signup"} activeClassName="active" className="nav-link">
                                        Sign Up
                                    </NavLink>
                                </li>
                            </div>
                        )}
                </nav>

                <div className="container mt-3">
                    <Switch>
                        <Route exact path={["/", "/products"]} component={Products} />
                        <Route exact path="/login" component={Login} />
                        <Route exact path="/signup" component={Register} />
                        <Route exact path="/profile" component={Profile} />
                        <Route path="/user" component={BoardUser} />
                        <Route path="/dev" component={BoardDevelop} />
                        <Route path="/admin" component={BoardAdmin} />
                    </Switch>
                </div>
            </div>
        );
    }
}

export default App;