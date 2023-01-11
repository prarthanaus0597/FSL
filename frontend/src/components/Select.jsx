import React, {useEffect, useState} from 'react';
import {useNavigate} from "react-router-dom";
import './css/select.css'
import {toast, ToastContainer} from "react-toastify";

function Select(){
    const [courses,setCourses]=useState([]);
    const id=JSON.parse(sessionStorage.getItem("id"));
    const navigate=useNavigate();

    //----------Handle submit -----------------------------------------------------------
    const handleSubmit=(event)=> {
        event.preventDefault()
        // console.log(event.target[6].value);
        const data = {
            "course_id": event.target[0].value,
            "emp_id": id,
        }
        const fetchCourse = async () => {
            const response = await fetch(`http://localhost:8080/api/courseschedule/istaken/${data.emp_id}/${data.course_id}`, {
                method: "GET",
                mode: "cors",
                headers: {
                    // Authorization: `Bearer: ${token}`,
                    "Access-Control-Allow-Headers": "Accept",
                    "Access-Control-Allow-Origin": "*",

                    "Content-Type": "application/json",
                },

            });

            const newData = await response;
            // console.log(newData.status);
            if(response.status==200){
                toast("Conflicting with one of your other subjects!! Cannot be assigned!", {
                    position: "bottom-center",
                    autoClose: 5000,
                    hideProgressBar: false,
                    closeOnClick: true,
                    pauseOnHover: true,
                    draggable: true,
                    progress: undefined,
                    width:20,
                    theme: "dark",
                });
                const sleep = ms => new Promise(r => setTimeout(r, ms));
                await sleep(5000)


                return;


            }
            else{
                toast("No conflict!! Assigning the course!!", {
                    position: "bottom-center",
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

                const assignCourse = async () => {

                    const response = await fetch(`http://localhost:8080/api/course/assign/${data.emp_id}/${data.course_id}`, {
                        method: "GET",
                        mode: "cors",
                        headers: {
                            // Authorization: `Bearer: ${token}`,
                            "Access-Control-Allow-Headers": "Accept",
                            "Access-Control-Allow-Origin": "*",

                            "Content-Type": "application/json",
                        },
                        // body: JSON.stringify(data),

                    });

                    const newData = await response;
                    // console.log(newData);
                    if (response.status == 200) {
                        toast("Updation done successfully!!", {
                            position: "bottom-center",
                            autoClose: 5000,
                            hideProgressBar: false,
                            closeOnClick: true,
                            pauseOnHover: true,
                            draggable: true,
                            progress: undefined,
                            width: 20,
                            theme: "dark",
                        });
                        const sleep = ms => new Promise(r => setTimeout(r, ms));
                        await sleep(5000)


                        return;


                    } else {
                        toast("Error while assigning the course!!", {
                            position: "bottom-center",
                            autoClose: 5000,
                            hideProgressBar: false,
                            closeOnClick: true,
                            pauseOnHover: true,
                            draggable: true,
                            progress: undefined,
                            theme: "dark",
                        });


                    }
                }









                assignCourse();
                const sleep1 = ms => new Promise(r => setTimeout(r, ms));
                await sleep(5000)
                navigate(`/login/viewdetails/${id}`);

            }
        };
        fetchCourse();


    }

    //load the courses which dont have faculty--------------------------------------------------------------------------------------
    useEffect(() =>{


        const fetchData = async ()=>{
            const response = await fetch(`http://localhost:8080/api/course/get`, {
                method: "GET",
                mode: "cors",
                headers: {
                    // Authorization: `Bearer: ${token}`,
                    "Content-Type": "application/json",
                },
                // body: JSON.stringify(data),

            });
            const newData = await response.json();
            setCourses(newData);
            console.log(newData);
        };
        fetchData();
    }, [])
    return(
        <>
<form onSubmit={handleSubmit} >
            <div className="box">
                <select required>
                    <option value="" >Select the course</option>
                    {courses

                        // render an option for each teacher
                        .map((value) => {

                            return(<option
                                // map needs a unique key, so use the teacher id
                                key={value.course_id}
                                // this will be e.target.value if this option is selected
                                value={value.course_id}
                                // print the teacher name as the option text
                            >{value.course_name}

                            </option>);

                        })}
                </select>
            </div>
    <div className="button-1">
        <input type="submit" value="Add"  />
    </div>
</form>
            <ToastContainer/>
        </>


    );
}

export default Select;