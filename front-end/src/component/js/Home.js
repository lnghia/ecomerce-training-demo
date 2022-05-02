import React, { useState } from "react";
import TopBar from "./TopBar";
import NavBar from "./NavBar";
import Features from "./Features";
import Footer from "./Footer";

const Home = () => {
    return (
        <>
            <TopBar />
            <NavBar />
            <Features />
            <Footer />
        </>
    )
}

export default Home;