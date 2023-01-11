import React, {useEffect, useState} from 'react';
import Sidebar from "./Sidebar";
import {useParams} from "react-router-dom";



function ViewCourses(){
    const {id}=useParams();

    const [courses,setCourses]=useState([
        {
            'building': '',
            'day': '',
            'time': '',
            'room': '',
            'course_code': '',
            'course_name': '',
            'course_term': '',
            'course_year':''}
    ]);

    sessionStorage.setItem("id", JSON.stringify(id));



        //load the courses which dont have faculty--------------------------------------------------------------------------------------
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

            setCourses(result);



        };
        // console.log(courses)
        fetchData();

    }, [])

//
let options=courses;
    // console.log(options)
    return(
        <>
<Sidebar/>
            <br/>  <br/>  <br/>
            <div>
            <center><h3>View Course Schedule</h3></center>

            <hr/>
            </div>

            <div className="container-1">
                <table>
                    <thead>
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

                    {courses.map((obj,i)=> {
                      return ( <tr>
                            <td>{obj.course_code}</td>
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
            </div>

        </>

    );
}

export default ViewCourses;