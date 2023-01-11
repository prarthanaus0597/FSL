import React, {useEffect, useState} from 'react';
import './css/sidebar.css';

import {NavLink, useParams} from "react-router-dom";
function Sidebar(){

    const id=JSON.parse(sessionStorage.getItem("id"));

// console.log();
    return(

        <>
            {/*<div className="App">*/}


            <div className="sidebar">


                <NavLink to={`/login/${id}`} > View Course Schedule</NavLink>
                <NavLink to={`/login/addcourses/${id}`}>Add Courses</NavLink>
                <NavLink to={`/login/viewdetails/${id}`}>View Details</NavLink>

                <NavLink to='/'>Logout</NavLink>
                {/*<a href="#news">News</a>*/}
                {/*<a href="#contact">Contact</a>*/}
                {/*<a href="#about">About</a>*/}
            </div>





            {/*</div>*/}



        </>

    );
}

export default Sidebar;