import React, { useState } from "react";
import { useSelector, useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { useForm } from "react-hook-form";
import "../css/Login.css";
import FormErrorMsg from "./ErrorMessageInForm";
import { login } from "../../api/Authentication";
import { loginAction } from "../../redux/slices/authenticationSlice";

import { Modal } from "react-bootstrap";
// import {$, jQuery} from "jquery";


const Login = (props) => {
    const { register, formState: { errors }, handleSubmit } = useForm();
    const dispatch = useDispatch();
    const [showModal, setShowModal] = useState(false);

    const handleClose = () => setShowModal(false);
    const handleShow = () => setShowModal(true);

    const onSubmit = data => {
        async function fetchLogin() {
            let rs = await login(data.email, data.password);

            return rs;
        }

        let loginSuccess = fetchLogin();

        if (loginSuccess) {
            dispatch(loginAction());
            // $('#exampleModalCenter').modal('hide');
        }
    }

    return (
        <Modal id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true" show={props.show}>
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Login</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form onSubmit={handleSubmit(onSubmit)}>
                            <div className="form-group">
                                <label>Email</label>
                                <input {...register("email", { required: true, pattern: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g })} type="text" className="form-control" placeholder="Email" />
                                {errors.email?.type === 'required' && <FormErrorMsg message="Email is required" />}
                                {errors.email?.type === 'pattern' && <FormErrorMsg message="Invalid email" />}
                            </div>
                            <div className="form-group">
                                <label>Password</label>
                                <input {...register("password", { required: true })} type="password" className="form-control" placeholder="Password" />
                                {errors.password?.type === 'required' && <FormErrorMsg message="Password is required" />}
                            </div>
                            <button type="submit" class="btn btn-primary">Login</button>
                            <button type="button" class="btn btn-secondary ml-2" data-dismiss="modal">Close</button>
                        </form>
                    </div>
                </div>
            </div>
        </Modal>
    );
}

export default Login;