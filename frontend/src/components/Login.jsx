import React, {useEffect, useState} from 'react';
import {NavLink, useNavigate} from 'react-router-dom';
import axios from "axios";
import './css/reg.css';
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function Login() {
    // const [dept,setdept]=useState([{'departmentId':'','departmentName':'','capacity':''}]);
    const navigate = useNavigate();
    const header={"Access-Control-Allow-Origin": "*",};

    const handleSubmit=(event)=> {
        event.preventDefault()
        // console.log(event.target[6].value);
        const data = {
            "email": event.target[0].value,
            "password": event.target[1].value,
        }
        const fetchLogin = async () => {
            const regex = new RegExp("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}");
            const reg_email = /\S+@\S+\.\S+/;
            if (!reg_email.test(data.email)) {
                toast.warn("Enter valid email...", {
                    position: "bottom-right",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                });
                return;
            }
            // if (!regex.test(data.password)) {
            //     toast.warn(
            //         "Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters",
            //         {
            //             position: "bottom-right",
            //             autoClose: 5000,
            //             hideProgressBar: false,
            //             closeOnClick: true,
            //             pauseOnHover: true,
            //             draggable: true,
            //             progress: undefined,
            //         }
            //     );
            //     return;
            //
            // }


            const response = await fetch(`http://localhost:8080/api/employee/login/`, {
                method: "POST",
                mode: "cors",
                headers: {
                    // Authorization: `Bearer: ${token}`,
                    "Access-Control-Allow-Headers": "Accept",
                    "Access-Control-Allow-Origin": "*",

                    "Content-Type": "application/json",
                },
                body: JSON.stringify(data),

            });
try{
            const newData = await response.json();
            console.log(JSON.stringify(newData));
            if(response.status==200){
                toast("Login Successful!!", {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme: "dark",
                });

                const sleep = ms => new Promise(r => setTimeout(r, ms));
                await sleep(5000)
                // const empid=event.
                navigate(`/login/${newData.employeeID}`);
                return;


            }}
            catch (e) {

                    toast("Invalid details!!", {
                        position: "top-center",
                        autoClose: 5000,
                        hideProgressBar: false,
                        closeOnClick: true,
                        pauseOnHover: true,
                        draggable: true,
                        progress: undefined,
                        theme: "dark",
                    });

                    const sleep = ms => new Promise(r => setTimeout(r, ms));
                    await sleep(5000)
                    navigate('/login');
                    return;

            }
        };
        fetchLogin();


    }






    return (

        <>
            <div className="container">

                <div className="title">Faculty Login</div>
                <div className="content">
                    <form action="#" onSubmit={handleSubmit} >
                        <div className="user-details">

                            <div className="input-box">
                                <span className="details">Email</span>
                                <input type="email" placeholder="Enter your email" autocomplete="off" required/>
                            </div>
                            <div className="input-box">
                                <span className="details">Password</span>
                                <input type="password" placeholder="Enter your password" autocomplete="off" required/>
                            </div>





                        </div>


                        <div className="button">
                            <input type="submit" value="Login"/>
                            <br/>
                            <br/>
                         <NavLink style={{'textDecoration':'None'}} to='/'> <center>Not Registered yet?</center></NavLink>

                        </div>
                        <br/>

                    </form>
                </div>
                <ToastContainer/>
            </div>


        </>
    );
}

export default Login;