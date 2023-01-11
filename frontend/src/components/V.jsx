import React, {useEffect, useState} from 'react';
import {useNavigate} from 'react-router-dom';
import axios from "axios";
import './css/reg.css';
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import {NavLink} from "react-router-dom";
function ViewCourse() {
    const [dept,setdept]=useState([{'departmentId':'','departmentName':'','capacity':''}]);
    const navigate = useNavigate();
    const handleSubmit=(event)=>{
        event.preventDefault()
        // console.log(event.target[6].value);
        const emp={

            "fname":event.target[1].value,
            "lname":event.target[2].value,
            "email":event.target[3].value,
            "password":event.target[4].value,
            "title":event.target[0].value,
            "photo_path":event.target[6].value,
            "departmentId":event.target[5].value
        }
        const addData = async ()=>{
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
            const response = await fetch(`http://localhost:8080/api/courseschedule/get`, {
                method: "GET",
                mode: "cors",
                headers: {
                    // Authorization: `Bearer: ${token}`,
                    "Content-Type": "application/json",
                },
                // body: JSON.stringify(data),

            });
            const newData = await response.json();
            const result = newData.map(c => ({ 'building': c.building, 'day': c.day,'time':c.time,'room':c.room,'course_code':c.course['course_code'] ,'course_name':c.course['course_name'],
                'course_term':c.course['term'],'course_year':c.course['year']

            }));
            setdept(result);
            console.log(result);
        };
        fetchData();
    }, [])




    return (

        <>
            <div className="container">

                <div className="title">Faculty Registration</div>
                <div className="content">
                    <form action="#"  onSubmit={handleSubmit}>
                        <table> <thead>

                        <tr>
                            <th>Couse Code</th>
                            <th>Course Name</th>
                            <th>Term</th>
                            <th>Year</th>
                            <th>Day</th>
                            <th>Time</th>
                            <th>Building</th>
                            <th>Room</th>
                        </tr>
                        </thead>
                            <tbody>
                                    {dept

                                        // render an option for each teacher
                                        .map((obj) => {

                                            return(<tr
                                            > <td>{obj.course_code}</td>
                                                <td>{obj.course_name}</td>
                                                <td>{obj.course_term}</td>
                                                <td>{obj.course_year}</td>
                                                <td>{obj.day}</td>
                                                <td>{obj.time}</td>
                                                <td>{obj.building}</td>
                                                <td>{obj.room}</td>
                                            </tr>)

                                        })}
                            </tbody>
                        </table>







                    </form>
                </div>
                <ToastContainer/>
            </div>


        </>
    );
}

export default ViewCourse;