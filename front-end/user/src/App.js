import logo from './logo.svg';
import './App.css';
import Login from './component/js/Login';
import Home from './component/js/Home';
import TopBar from './component/js/TopBar';
import Footer from "./component/js/Footer";
import ProductDetail from "./component/js/ProductDetail";
import Shop from './component/js/Shop';
import ManageProducts from './component/js/ManageProducts';
import { Routes, Route, Link } from "react-router-dom";

function App() {
  return (
    <div>
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/login' element={<Login />} />
        <Route path='/product' element={<ProductDetail />} />
        <Route path='/shop' element={<Shop />} />
        <Route path='/manage_product' element={<ManageProducts />} />
      </Routes>
      {/* <TopBar /> */}
      {/* <Home /> */}
      {/* <Login /> */}
      {/* <Footer /> */}
    </div>
  );
}

export default App;
