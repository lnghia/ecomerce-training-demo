import React, { useState } from "react";
import TopBar from "./TopBar";
import NavBar from "./NavBar";
import Features from "./Features";
import Footer from "./Footer";
import FeaturedProducts from "./FeaturedProducts";

const Home = () => {
    return (
        <>
            <TopBar />
            <NavBar />
            <FeaturedProducts />
            <Features />
            <Footer />
        </>
    )
}

export default Home;