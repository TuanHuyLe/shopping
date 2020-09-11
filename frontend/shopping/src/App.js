import React from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route,
    NavLink,
    Link,
} from "react-router-dom";
import Home from "./components/Home";
import Login from "./components/Login";

function App() {
    return (
        <div>
            <Router>
                {/* Menu */}
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <Link className="navbar-brand" to="/">
                        Shopping
                    </Link>
                    <button
                        className="navbar-toggler"
                        type="button"
                        data-toggle="collapse"
                        data-target="/navbarNavAltMarkup"
                        aria-controls="navbarNavAltMarkup"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div
                        className="collapse navbar-collapse"
                        id="navbarNavAltMarkup"
                    >
                        <div className="navbar-nav">
                            <NavLink
                                to="/"
                                exact
                                className="nav-link"
                                activeClassName="active"
                            >
                                Trang chu
                            </NavLink>
                            <NavLink
                                to="/dang-nhap"
                                className="nav-link"
                            >
                                Dang nhap
                            </NavLink>
                        </div>
                    </div>
                </nav>
                {/* Noi dung */}
                <Switch>
                    <Route path="/dang-nhap">
                        <Login />
                    </Route>
                    <Route path="/">
                        <Home />
                    </Route>
                </Switch>
            </Router>
        </div>
    );
}

export default App;
