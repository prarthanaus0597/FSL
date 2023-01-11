import React, {useEffect, useState} from 'react';
import Sidebar from "./Sidebar";
import {useParams} from "react-router-dom";
import  img from '../img.png'


function ViewDetails(){
    let images = JSON.parse(localStorage.getItem("images"))
    let image=images[0]
    const [emp,setEmp]=useState(
        {}
    );
    const [courses,setcourses]=useState([]);

    const id=JSON.parse(sessionStorage.getItem("id"));



    //load the courses which dont have faculty--------------------------------------------------------------------------------------
    useEffect(() =>{


        const fetchData = async ()=>{
            const response = await fetch(`http://localhost:8080/api/employee/getDetails/${id}`, {
                method: "GET",
                mode: "cors",
                headers: {
                    // Authorization: `Bearer: ${token}`,
                    "Content-Type": "application/json",
                },
                // body: JSON.stringify(data),

            });
            const newData = await response.json();
            console.log(newData)

            setEmp(newData);
            const response1 = await fetch(`http://localhost:8080/api/course/courseadded/${id}`, {
                method: "GET",
                mode: "cors",
                headers: {
                    // Authorization: `Bearer: ${token}`,
                    "Content-Type": "application/json",
                },
                // body: JSON.stringify(data),

            });
try {
    const newData1 = await response1.json();
    console.log(newData1)
    const result = newData1.map(c => ({'course_code':c.course_code,'course_name':c.course_name

    }));
    setcourses(result);

}
catch(e){
    console.log("no courses available")

}



        };
        // console.log(courses)
        fetchData();

    }, [])


    return(
        <>
            <Sidebar/>
            <br/>  <br/>  <br/>
            <div>
                <center><h3>View Details</h3></center>

                <hr/>
            </div>
<br/>

            <div className="container-2">
                <img className="img" src={image} alt={img}/>
                <table className="detail">
                    <tbody>

                    <tr><th>Employee ID :</th><td>{emp.employeeID}</td></tr>
                    <tr><th>Name :</th><td>{emp.title}. {emp.fname} {emp.lname}</td></tr>
                    <tr><th>Email ID :</th><td>{emp.email}</td></tr>
                    <tr><th>Department:</th><td>{emp.departmentName}</td></tr>
                    </tbody>
                </table>
                {courses.length>0?

                    (<><br/>
                       <center> <h4><u>Courses Handled</u></h4></center><table className="detail">
                    <tbody>
                    {courses.map((val)=> {

                        return(<tr>
                            <th>{val.course_code}</th>
                            <td>{val.course_name}</td>
                        </tr>)
                    })}

                    </tbody>
                    </table></>):(<></>)
                }



                {/*<img src={image}/>*/}


            </div>

        </>

    );
}

export default ViewDetails;