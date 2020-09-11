import React from "react";
import logo from '../plugins/login/images/img-01.png';
import '../plugins/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css';
import '../plugins/login/vendor/animate/animate.css';
import '../plugins/login/vendor/css-hamburgers/hamburgers.min.css';
import '../plugins/login/vendor/select2/select2.min.css';
import '../plugins/login/css/util.css';
import '../plugins/login/css/main.css';
import Axios from "axios";

class Login extends React.Component {
    constructor(props) {
        super(props);
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.signIn = this.signIn.bind(this);
        this.state = {
            email: '',
            password: ''
        };
    }
    handleEmailChange(e) {
        this.setState({ email: e.target.value })
    }
    handlePasswordChange(e) {
        this.setState({ password: e.target.value })
    }
    signIn(e) {
        e.preventDefault();
        Axios.post('/api/auth/login', {
            username: this.state.email,
            password: this.state.password
        })
            .then(function (response) {
                console.log(response.data.token);
            })
            .catch(function (error) {
                alert('Unauthorized');
            });
    }
    render() {
        return (
            <div className="limiter">
                <div className="container-login100">
                    <div className="wrap-login100">
                        <div className="login100-pic js-tilt" data-tilt>
                            <img src={logo} alt="IMG" />
                        </div>

                        <form className="login100-form validate-form">
                            <span className="login100-form-title">
                                {" "}
                            Dang nhap{" "}
                            </span>

                            <div
                                className="wrap-input100 validate-input"
                                data-validate="Valid email is required: ex@abc.xyz"
                            >
                                <input
                                    onChange={this.handleEmailChange}
                                    id="inputEmail"
                                    className="input100"
                                    type="text"
                                    name="email"
                                    autoComplete="off"
                                    placeholder="Email"
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                    <i
                                        className="fa fa-envelope"
                                        aria-hidden="true"
                                    ></i>
                                </span>
                            </div>

                            <div
                                className="wrap-input100 validate-input"
                                data-validate="Password is required"
                            >
                                <input
                                    onChange={this.handlePasswordChange}
                                    id="inputPassword"
                                    className="input100"
                                    type="password"
                                    name="pass"
                                    placeholder="Password"
                                />
                                <span className="focus-input100"></span>
                                <span className="symbol-input100">
                                    <i
                                        className="fa fa-lock"
                                        aria-hidden="true"
                                    ></i>
                                </span>
                            </div>

                            <div className="container-login100-form-btn">
                                <button className="login100-form-btn" onClick={this.signIn}>Login</button>
                            </div>

                            <div className="text-center p-t-12">
                                <span className="txt1"> Forgot </span>
                                <a className="txt2" href="/">
                                    {" "}
                                Username / Password?{" "}
                                </a>
                            </div>

                            <div className="text-center p-t-136">
                                <a className="txt2" href="/">
                                    Create your Account
                                <i
                                        className="fa fa-long-arrow-right m-l-5"
                                        aria-hidden="true"
                                    ></i>
                                </a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default Login;
