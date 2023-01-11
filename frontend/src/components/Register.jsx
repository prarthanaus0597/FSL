import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axios from "axios";
import './css/reg.css';
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import {NavLink} from "react-router-dom";
function Register() {

    const [dept,setdept]=useState([{
        'building': '',
        'day': '',
        'time': '',
        'room': '',
        'course_code': '',
        'course_name': '',
        'course_term': '',
        'course_year':''}]);
    const navigate = useNavigate();
    const handleSubmit=(event)=>{
        localStorage.removeItem("images");
        // console.log(event.target[6].files[0])
        let file=event.target[6].files[0]
        let reader = new FileReader();
        function addImage(imgData){
            let imagesObject=[]
            imagesObject.push(imgData);
            localStorage.setItem("images", JSON.stringify(imagesObject));
        }

        // Closure to capture the file information.
        reader.onload = function(e) {

            addImage(e.target.result);
            // console.log(e.target.result)
        };

        reader.readAsDataURL(file);


        // function displayImgData(imgData){
        //     let span = document.createElement('span');
        //     span.innerHTML = '<img class="thumb" src="' + imgData + '"/>';
        //     document.getElementById('list').insertBefore(span, null);
        // }
        // function loadFromLocalStorage(){
        //     let images = JSON.parse(localStorage.getItem("images"))
        //
        //     if(images && images.length > 0){
        //        let imagesObject = images;
        //
        //         imagesObject.forEach(displayImgData);
        //     }
        // }



    event.preventDefault()


console.log(event.target[6].files[0].name)
        const emp={

            "fname":event.target[1].value,
            "lname":event.target[2].value,
            "email":event.target[3].value,
            "password":event.target[4].value,
            "title":event.target[0].value,
            "photo_path":`localstorage/${event.target[6].files[0].name}`,
            "departmentName":event.target[5].value
        }

        const addData = async ()=>{
            const regex = new RegExp("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}");
            const reg_email = /\S+@\S+\.\S+/;
            if (!reg_email.test(emp.email)) {
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
            if (!regex.test(emp.password)) {
                toast.warn(
                    "Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters",
                    {
                        position: "bottom-right",
                        autoClose: 5000,
                        hideProgressBar: false,
                        closeOnClick: true,
                        pauseOnHover: true,
                        draggable: true,
                        progress: undefined,
                    }
                );
                return;

            }



            const response = await fetch(`http://localhost:8080/api/employee/add`, {
                method: "POST",
                mode: "cors",
                headers: {
                    // Authorization: `Bearer: ${token}`,
                    "Access-Control-Allow-Headers":"Accept",
                   "Access-Control-Allow-Origin": "*",

                    "Content-Type": "application/json",
                },
                body: JSON.stringify(emp),

            });
            console.log(response.status);
            if(response.status==200){
                toast("Registration Successful! Please login Now!", {
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
            else{
                toast.warn("Employee Already Exist!! ", {
                    position: "top-center",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    theme:"dark",
                });

                return;
            }

        };
        addData();


    }





    const header={"Access-Control-Allow-Origin": "*",};
    useEffect(() =>{


        const fetchData = async ()=>{
            const response = await fetch(`http://localhost:8080/api/department/get`, {
                        method: "GET",
                        mode: "cors",
                        headers: {
                            // Authorization: `Bearer: ${token}`,
                            "Content-Type": "application/json",
                        },
                        // body: JSON.stringify(data),

                    });
            const newData = await response.json();
            setdept(newData);
            // console.log(newData);
        };
        fetchData();
    }, [])




    return (

        <>
        <div className="container">

            <div className="title">Faculty Registration</div>
            <div className="content">
                <form action="#"  onSubmit={handleSubmit}>
                    <div className="user-details">
                        <div className="input-box">
                            <span className="details">Title</span>
                            <select name="title" id="title" required>
                                <option value="">Select the Title </option>
                                {/*<option value="assistant">Assistant Professor</option>*/}
                                {/*<option value="associate">Associate Professor</option>*/}
                                {/*<option value="professor">Professor</option>*/}
                                <option value="Mr">Mr</option>
                                <option value="Ms">Ms</option>
                                <option value="Mrs">Mrs</option>

                            </select>
                        </div>
                        <div className="input-box">
                            <span className="details">First Name</span>
                            <input type="text" placeholder="Enter your first name" autocomplete="off" required/>
                        </div>
                        <div className="input-box">
                            <span className="details">Last Name</span>
                            <input type="text"   autocomplete="off"placeholder="Enter your last name "/>
                        </div>
                        <div className="input-box">
                            <span className="details">Email</span>
                            <input type="email" autocomplete="off" placeholder="Enter your email" required/>
                        </div>
                        <div className="input-box">
                            <span className="details">Password</span>
                            <input type="password" pautocomplete="off" placeholder="Enter your password" required/>
                        </div>


                        <div className="input-box">
                            <span className="details">Department</span>
                            <select name="department" id="department" required>
                                <option>Select the department</option>
                                {dept

                                    // render an option for each teacher
                                    .map((value) => {

                                        return(<option
                                            // map needs a unique key, so use the teacher id
                                            key={value.departmentID}
                                            // this will be e.target.value if this option is selected
                                            value={value.departmentName}
                                            // print the teacher name as the option text
                                        >{value.departmentName}

                                        </option>);

                                    })}



                            </select>
                        </div>
                        <div className="input-box" >
                            <span className="details">Photograph</span>
                            <input type="file" id="files" name="filename" className="photo" accept="image/gif, image/jpeg, image/png" required/>
                        </div>

                    </div>


                    {/*<div className="course-details">*/}

                    {/*    <span className="details">Preferred Courses</span>*/}
                    {/*                <div className="my-multi-lines-item">*/}
                    {/*                    <select name="course"  id="course" multiple>*/}
                    {/*                        <option className='my-multi-lines-text'  value="">Select the course preferred </option>*/}
                    {/*                        <option className='my-multi-lines-text' value="volvo">Volvo</option>*/}
                    {/*                        <option className='my-multi-lines-text' value="saab">Saab</option>*/}
                    {/*                        <option className='my-multi-lines-text' value="mercedes">Mercedes</option>*/}
                    {/*                        <option className='my-multi-lines-text' value="audi">Audi</option>*/}
                    {/*                    </select>*/}


                    {/*                </div>*/}
                    {/*    </div>*/}

                    <div className="button">
                        <input type="submit" value="Register"  />
                        <br/>
                        <br/>
                       <NavLink to='/login' style={{'textDecoration':'None'}}><center>Already Registered?</center></NavLink>

                    </div>
                    <br/>

                </form>

            </div>
            <ToastContainer/>
        </div>


        </>
    );
}

export default Register;