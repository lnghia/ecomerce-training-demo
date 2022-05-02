import logo from './logo.svg';
import './App.css';
import Login from './component/js/Login';
import Home from './component/js/Home';
import TopBar from './component/js/TopBar';
import Footer from "./component/js/Footer";
import { Routes, Route, Link } from "react-router-dom";

function App() {
  return (
    <div>
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/login' element={<Login />} />
      </Routes>
      {/* <TopBar /> */}
      {/* <Home /> */}
      {/* <Login /> */}
      {/* <Footer /> */}
    </div>
  );
}

export default App;
