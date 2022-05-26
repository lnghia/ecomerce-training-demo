import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import { selectProductAction } from '../../redux/slices/productSlice';
import { useNavigate } from 'react-router-dom';

const ProductCard = (props) => {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    function handleProductCardClick() {
        dispatch(selectProductAction(props.productId));
        navigate("/product");
    }

    return (
        <div className="card product-item border-0 mb-4">
            <div className="card-header product-img position-relative overflow-hidden bg-transparent border p-0">
                <img className="img-fluid w-100" src={props.img} alt="" />
            </div>
            <div className="card-body border-left border-right text-center p-0 pt-4 pb-3">
                <h6 className="text-truncate mb-3">{props.name}</h6>
                <div className="d-flex justify-content-center">
                    <h6>${props.price}</h6>
                    {/* <h6 className="text-muted ml-2"><del>{props.price}</del></h6> */}
                </div>
            </div>
            <div className="card-footer d-flex justify-content-between bg-light border">
                <button onClick={handleProductCardClick} className="btn btn-sm text-dark p-0"><i className="fas fa-eye text-primary mr-1" />View Detail</button>
                <a href='#' className="btn btn-sm text-dark p-0"><i className="fas fa-shopping-cart text-primary mr-1" />Add To Cart</a>
            </div>
        </div>
    )
}

export default ProductCard;