import React, {useEffect, useState} from 'react';

import Sidebar from "./Sidebar";

import MultiSelect from "./MultiSelect";
import Select from "./Select";
function AddCourses(){
    const id=JSON.parse(sessionStorage.getItem("id"));

    return(
        <>
            <Sidebar></Sidebar>
            <br/><br/><br/>
            <center><h3>Add Courses</h3></center>
            <hr/>
            <Select/>
        </>

    );
}

export default AddCourses;