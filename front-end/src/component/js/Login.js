import React, { useState } from "react";
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { useForm } from "react-hook-form";
import "../css/Login.css";
import { login } from "../../api/Authentication";
import { loginAction } from "../../redux/slices/authenticationSlice";


const Login = (props) => {
    const { register, formState: { errors }, handleSubmit } = useForm();
    const navigate = useNavigate();
    const dispatch = useDispatch();

    const onSubmit = async data => {
        let loginSuccess = await login(data.email, data.password);

        if (loginSuccess) {
            dispatch(loginAction());
            navigate('/');
        }
    }

    return (
        <div>
            <div className="sidenav">
                {/* <img src="/assets/bg.jpg" className="card-img" /> */}
                {/* <div className="hero">
                    <img src="/assets/bg.jpg" className="card-img" />
                    <div className="card-img-overlay">
                        <h2>E Shopper<br /> Login Page</h2>
                    </div>
                </div> */}
            </div>
            <div className="main">
                <div className="col-md-6 col-sm-12">
                    <div className="login-form">
                        <form onSubmit={handleSubmit(onSubmit)}>
                            <div className="form-group">
                                <label>Email</label>
                                <input {...register("email", { required: true, pattern: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g })} type="text" className="form-control" placeholder="Email" />
                                {errors.email?.type === 'required' && "Email is required"}
                                {errors.email?.type === 'pattern' && "Invalid email"}
                            </div>
                            <div className="form-group">
                                <label>Password</label>
                                <input {...register("password", { required: true })} type="password" className="form-control" placeholder="Password" />
                                {errors.password?.type === 'required' && "Password is required"}
                            </div>
                            <button type="submit" className="btn btn-dark">Login</button>
                            <button type="submit" className="btn btn-secondary ml-2">Register</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Login;