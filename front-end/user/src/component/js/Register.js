import React, { useState } from "react";
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { useForm } from "react-hook-form";
import "../css/Login.css";
import FormErrorMsg from "./ErrorMessageInForm";
import { registerApi } from "../../api/Authentication";
import { loginAction } from "../../redux/slices/authenticationSlice";

import { Modal } from "react-bootstrap";
// import {$, jQuery} from "jquery";


const Register = (props) => {
    const { register, formState: { errors }, handleSubmit } = useForm();
    const [passwordsMatch, setPasswordMatch] = useState(true);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const onSubmit = async data => {
        if (data.password !== data.repeatPassword) {
            setPasswordMatch(false);
        } else {
            let result = await registerApi(data.email, data.password, data.firstName, data.lastName);

            // if (result !== null) {
            //     navigate('/');
            // }
        }
    }

    const closeLoginModal = () => {
        props.onCloseLoginModal();
    }

    return (
        <Modal id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" show={props.show}>
            <div>
                <div class="modal-content">
                    <div class="modal-body">
                        <form onSubmit={handleSubmit(onSubmit)}>
                            <div className="form-group">
                                <label>Email</label>
                                <input {...register("email", { required: true, pattern: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g })} type="text" className="form-control" placeholder="Email" />
                                {errors.email?.type === 'required' && <FormErrorMsg message="Email is required" />}
                                {errors.email?.type === 'pattern' && <FormErrorMsg message="Invalid email" />}
                            </div>
                            <div className="form-group">
                                <label>First name</label>
                                <input {...register("firstName", { required: true })} type="text" className="form-control" placeholder="First name" />
                                {errors.firstName?.type === 'required' && <FormErrorMsg message="First name is required" />}
                            </div>
                            <div className="form-group">
                                <label>Last name</label>
                                <input {...register("lastName", { required: true })} type="text" className="form-control" placeholder="Last name" />
                                {errors.lastName?.type === 'required' && <FormErrorMsg message="Last name is required" />}
                            </div>
                            <div className="form-group">
                                <label>Password</label>
                                <input {...register("password", { required: true })} type="password" className="form-control" placeholder="Password" />
                                {errors.password?.type === 'required' && <FormErrorMsg message="Password is required" />}
                                {!passwordsMatch && <FormErrorMsg message="Password and repeat password must match." />}
                            </div>
                            <div className="form-group">
                                <label>Repeat password</label>
                                <input {...register("repeatPassword", { required: true })} type="password" className="form-control" placeholder="Repeat password" />
                                {!passwordsMatch && <FormErrorMsg message="Password and repeat password must match." />}
                            </div>
                            <button type="submit" class="btn btn-primary">Register</button>
                            <button type="button" class="btn btn-secondary ml-2" onClick={closeLoginModal} >Close</button>
                        </form>
                    </div>
                </div>
            </div>
        </Modal>
    );
}

export default Register;