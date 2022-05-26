import React, { useEffect, useState } from "react";

import { fetchProductImages } from "../../api/product";

const ProductDetailImgs = (props) => {

    return (
        <div className="col-lg-5 pb-5">
            <div id="product-carousel" className="carousel slide" data-ride="carousel">
                <div className="carousel-inner border">
                    {/* <div className="carousel-item active">
                        <img className="w-100 h-100" src="img/product-1.jpg" alt="Image" />
                    </div>
                    <div className="carousel-item">
                        <img className="w-100 h-100" src="img/product-2.jpg" alt="Image" />
                    </div> */}
                    {/* {
                        imageList.map(image => {
                            return (
                                <div className="carousel-item">
                                    <img className="w-100 h-100" src={image.img} alt="Image" />
                                </div>
                            )
                        })
                    } */}
                    <div className="carousel-item active">
                        <img className="w-100 h-100" src={props.img} alt="Image" />
                    </div>
                </div>
                {/* <a className="carousel-control-prev" href="#product-carousel" data-slide="prev">
                    <i className="fa fa-2x fa-angle-left text-dark" />
                </a>
                <a className="carousel-control-next" href="#product-carousel" data-slide="next">
                    <i className="fa fa-2x fa-angle-right text-dark" />
                </a> */}
            </div>
        </div>
    )
}

export default ProductDetailImgs;