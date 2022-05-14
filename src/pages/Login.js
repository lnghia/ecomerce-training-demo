import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { Password } from 'primereact/password';
import { Toast } from 'primereact/toast';
import React, { useState, useRef } from 'react';
import { useForm } from 'react-hook-form';
import { useHistory } from 'react-router-dom';
import { login } from '../axios/api/authentication';
// import { useNavigate } from 'react-router-dom';
import './css/LoginModal.css';


const Login = () => {
    const history = useHistory();
    const toast = useRef(null);
    // const navigate = useNavigate();
    const defaultValues = {
        email: '',
        password: '',
    }

    const { register, formState: { errors }, handleSubmit, reset } = useForm({ defaultValues });

    const onSubmit = async (data) => {
        try {
            let result = await login(data.email, data.password);

            if (result) {
                reset();
                history.replace("/products");
                // navigate("/");
            } else {
                toast.current.show({ severity: 'error', summary: 'Error', detail: 'UnAuthorization', life: 3000 });
            }
        } catch (error) {
            toast.current.show({ severity: 'error', summary: 'Error', detail: error.message, life: 3000 });
        }

    };

    return (
        <div className="modal-window">
            <div className="form-demo">
                <Toast ref={toast} />
                <div className="flex justify-content-center">
                    <div className="card">
                        <h4 className="text-center">Login Admin</h4>
                        <form onSubmit={handleSubmit(onSubmit)} className="p-fluid">
                            <div className="field">
                                <span id="email" className={errors.email ? 'p-error' : ''}>Email*</span>
                                <InputText id="email" aria-labelledby="email"
                                    className={errors.email ? 'p-invalid' : ''}
                                    placeholder="your-email@gmail.com" {...register('email', {
                                        required: 'Please enter email',
                                    })}
                                />
                                {
                                    errors.email && <small id="username2-help" className="p-error block"> {errors.email.message}</small>
                                }
                            </div>
                            <div className="field">
                                <span id="password" className={errors.password ? 'p-error' : ''} >Password*</span>
                                <InputText id="password" aria-labelledby="password" type={'password'}
                                    className={errors.password ? 'p-invalid' : ''}
                                    {...register("password", {
                                        required: "Please enter password",
                                    })}
                                />
                                {
                                    errors.password && <small id="username2-help" className="p-error block"> {errors.password.message}</small>
                                }
                            </div>
                            <Button type="submit" label="Login" className="mt-2" />
                        </form>
                    </div>
                </div>
            </div>

        </div>
    );
}

export default Login;