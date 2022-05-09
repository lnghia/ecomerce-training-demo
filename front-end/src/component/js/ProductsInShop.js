import React, { useEffect, useState } from "react";

import ProductCard from "./ProductCard";
import PageTraveller from './Pagination';

import { selectProductAction } from '../../redux/slices/productSlice';
import { useDispatch, useSelector } from "react-redux";
import {
    genderFilterValuesSelector,
    sportFilterValuesSelector,
    categoryFilterValuesSelector,
    technologyFilterValuesSelector
} from "../../redux/selectors";

import { fetchProductList } from "../../api/product";

import '../css/ProductsInShop.css';

const ProductsInShop = (props) => {
    const dispatch = useDispatch();
    const [productList, setProductList] = useState([]);
    const [currPage, setCurrPage] = useState(1);
    const [totalPages, setTotalPages] = useState(0);
    const [keyword, setKeyword] = useState("");
    const [sortType, setSortType] = useState("");
    const [sortBy, setSortBy] = useState("");

    const genderFilters = useSelector(genderFilterValuesSelector);
    const sportFilters = useSelector(sportFilterValuesSelector);
    const typeFilters = useSelector(categoryFilterValuesSelector);
    const technologyFilters = useSelector(technologyFilterValuesSelector);

    useEffect(() => {
        const fetchProducts = async () => {
            console.log(technologyFilters);
            let result = await fetchProductList(genderFilters, sportFilters, technologyFilters, typeFilters, keyword);

            setProductList(result.content);
            setTotalPages(result.totalPages);
        }

        fetchProducts();
    }, [currPage, genderFilters, sportFilters, typeFilters, technologyFilters, keyword]);

    function handleOnSearchChange(event) {
        setKeyword(event.target.value);
    }

    const travelToPage = (page) => {
        setCurrPage(page);
    }

    console.log(productList);

    return (
        <div className="col-lg-9 col-md-12">
            <div className="row pb-3">
                <div className="col-12 pb-1">
                    <div className="d-flex align-items-center justify-content-between mb-4">
                        <form action>
                            <div className="input-group">
                                <input type="text" className="form-control" placeholder="Search by name" onChange={handleOnSearchChange} />
                                <div className="input-group-append">
                                    <span className="input-group-text bg-transparent text-primary">
                                        <i className="fa fa-search" />
                                    </span>
                                </div>
                            </div>
                        </form>
                        {/* <div className="dropdown ml-4">
                            <button className="btn border dropdown-toggle" type="button" id="triggerId" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Sort by
                            </button>
                            <div className="dropdown-menu dropdown-menu-right" aria-labelledby="triggerId">
                                <a className="dropdown-item" href="#">Latest</a>
                                <a className="dropdown-item" href="#">Popularity</a>
                                <a className="dropdown-item" href="#">Best Rating</a>
                            </div>
                        </div> */}
                    </div>
                </div>
                {
                    productList.map(product => {
                        return (
                            <div className="col-lg-4 col-md-6 col-sm-12 pb-1">
                                <ProductCard key={product.id} productId={product.id} name={product.name} price={product.price} img={product.thumbnail} />
                            </div>
                        )
                    })
                }

            </div>
            <PageTraveller showMax={3} totalPages={totalPages} onPageChange={travelToPage} />
        </div>
    )
}

export default ProductsInShop;