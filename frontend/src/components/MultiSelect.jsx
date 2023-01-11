import React, {useEffect, useState} from "react";
import "./css/select.css";
import Select from "react-select";


export default function MultiSelect() {
    // React state to manage selected options
    const [selectedOptions, setSelectedOptions] = useState();
    const [course,setCourse]=useState([]);
    const [courseList,setCourseList]=useState([]);

    // Array of all options
    let optionList = [];


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
            setCourse(newData);
           // console.log(newData);


            optionList = [];
            newData.forEach((value) =>{
                let singleObj = {};

                singleObj['value'] = value['course_id'];
                singleObj['label'] = value['course_name'];
                optionList.push(singleObj);
            });
            // console.log(optionList)
            setCourseList(optionList)
            setCourseList(...optionList)
        };
        fetchData();

        console.log(courseList)
    }, [])

    // Function triggered on selection
    function handleSelect(data) {
        setSelectedOptions(data);

    }
    return (
        <div className="apps">

            <div className="dropdown-container">
                <Select
                    options={optionList}
                    placeholder="Select Courses"
                    value={optionList}
                    onChange={handleSelect}
                    isSearchable={true}
                    isMulti
                />
            </div>
        </div>
    );
}