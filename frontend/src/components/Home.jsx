import Register from "./Register";
import logo from "../hero-img.svg";
function Home() {
    return (
        <div className="App">
            <center><h3>Academia</h3></center>
            <hr/>
<div className="float-container">

    <div className="float-child green" >
        <div className="green"><header className="App-header">
            <Register/>
        </header></div>
    </div>

    <div className="float-child blue">
        <div className="blue"> <img src={logo} alt="logo"/> </div>
    </div>
</div>  </div>



    )};

export default Home;