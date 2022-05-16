import React, { useEffect, useState } from "react";
import Features from "./Features";
import Footer from "./Footer";
import GlobalFilter from "./GlobalFilter";
import Header from "./Header";
import NavBar from "./NavBar";
import ProductsInShop from "./ProductsInShop";

const Shop = (props) => {
    const [categories, setCategories] = useState([]);

    useEffect(() => {

    }, []);

    return (
        <>
            <NavBar />
            <Header pageName="PRODUCT DETAIL" />
            <div class="container-fluid pt-5">
                <div class="row px-xl-5">
                    <GlobalFilter />
                    <ProductsInShop />
                </div>
            </div>
            <Features />
            <Footer />
        </>
    )
}

export default Shop;