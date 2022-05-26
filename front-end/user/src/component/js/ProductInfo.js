import React, { useState } from "react";
import ProductSizeBox from "./ProductSizeBox";
import StarRatingForShow from "./StarRatingForShow";

const ProductInfo = (props) => {
    return (
        <div class="col-lg-7 pb-5">
            <h3 class="font-weight-semi-bold">{props.product.name}</h3>
            <div class="d-flex mb-3">
                <div class="text-primary mr-2">
                    <StarRatingForShow rating={props.product.averageRating} />
                    {/* <small class="fas fa-star"></small>
                        <small class="fas fa-star"></small>
                        <small class="fas fa-star"></small>
                        <small class="fas fa-star-half-alt"></small>
                        <small class="far fa-star"></small> */}
                </div>
                <small class="pt-1">{props.product.countRating} reviews</small>
            </div>
            <h3 class="font-weight-semi-bold mb-4">${props.product.price}</h3>
            <p class="mb-4">{props.product.gender.name}</p>
            {/*size*/}
            <ProductSizeBox sizes={props.product.sizes} />
            <div class="d-flex align-items-center mb-4 pt-2">
                <div class="input-group quantity mr-3" style={{ width: '130px' }}>
                    <div class="input-group-btn">
                        <button class="btn btn-primary btn-minus" >
                            <i class="fa fa-minus"></i>
                        </button>
                    </div>
                    <input type="text" class="form-control bg-secondary text-center" value="1" />
                    <div class="input-group-btn">
                        <button class="btn btn-primary btn-plus">
                            <i class="fa fa-plus"></i>
                        </button>
                    </div>
                </div>
                <button class="btn btn-primary px-3"><i class="fa fa-shopping-cart mr-1"></i> Add To Cart</button>
            </div>
        </div>
    )
}

export default ProductInfo;