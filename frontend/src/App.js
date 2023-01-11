
import './components/css/App.css';
import Home from "./components/Home";
import {BrowserRouter,Route,Routes} from 'react-router-dom';

import LoginPage from "./components/LoginPage";
import ViewDetails from "./components/ViewDetails";

import AddCourses from "./components/AddCourse";
import ViewCourses from "./components/ViewCourses";



function App() {
  return (
      <>
          <BrowserRouter>
        <Routes>
          <Route exact path="/" element={<Home/>}/>
          <Route exact path="/login" element={<LoginPage />} />
            {/*<Route exact path="/login/:id" element={<Dashboard />} />*/}
            <Route exact path="/login/addcourses/:id" element={<AddCourses />} />
            <Route exact path="/login/:id/" element={<ViewCourses/>}/>
            <Route exact path="/login/viewdetails/:id/" element={<ViewDetails />} />
        </Routes>
              </BrowserRouter>
      </>





  );
}

export default App;
