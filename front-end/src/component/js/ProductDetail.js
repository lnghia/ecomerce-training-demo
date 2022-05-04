import React, { useEffect, useState } from "react";
import TopBar from "./TopBar";
import NavBar from "./NavBar";
import Header from "./Header";
import Footer from "./Footer";
import Features from "./Features";
import ProductDetailImgs from "./ProductDetailImgs";
import ProductInfo from "./ProductInfo";
import { useSelector } from "react-redux";
import { selectedProductIdSelector } from "../../redux/selectors";

import { fetchProductDetail } from "../../api/product";

const ProductDetail = (props) => {
    const [product, setProduct] = useState(null);
    let id = useSelector(selectedProductIdSelector);

    useEffect(() => {
        async function fetchProduct() {
            let response = await fetchProductDetail(parseInt(id));
            setProduct(response);
        }

        fetchProduct();
    }, []);

    console.log(product);

    return (
        <>
            {/* <TopBar />
            <NavBar /> */}
            <Header pageName="PRODUCT DETAIL" />
            {
                product != null &&
                <div class="container-fluid py-5">
                    <div class="row px-xl-5">
                        <ProductDetailImgs img={product.thumbnail} />
                        <ProductInfo product={product} />
                    </div>
                    <div class="row px-xl-5">
                        <div class="col">
                            <div class="nav nav-tabs justify-content-center border-secondary mb-4">
                                <a class="nav-item nav-link active" data-toggle="tab" href="#tab-pane-1">Description</a>
                                <a class="nav-item nav-link" data-toggle="tab" href="#tab-pane-3">Reviews</a>
                            </div>
                            <div class="tab-content">
                                <div class="tab-pane fade show active" id="tab-pane-1">
                                    <h4 class="mb-3">Product Description</h4>
                                    <p>{product.description}</p><br/>
                                    
                                </div>
                                {/* <div class="tab-pane fade" id="tab-pane-3">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h4 class="mb-4">1 review for "Colorful Stylish Shirt"</h4>
                                            <div class="media mb-4">
                                                <img src="img/user.jpg" alt="Image" class="img-fluid mr-3 mt-1" style="width: 45px;" />
                                                <div class="media-body">
                                                    <h6>John Doe<small> - <i>01 Jan 2045</i></small></h6>
                                                    <div class="text-primary mb-2">
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star"></i>
                                                        <i class="fas fa-star-half-alt"></i>
                                                        <i class="far fa-star"></i>
                                                    </div>
                                                    <p>Diam amet duo labore stet elitr ea clita ipsum, tempor labore accusam ipsum et no at. Kasd diam tempor rebum magna dolores sed sed eirmod ipsum.</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <h4 class="mb-4">Leave a review</h4>
                                            <small>Your email address will not be published. Required fields are marked *</small>
                                            <div class="d-flex my-3">
                                                <p class="mb-0 mr-2">Your Rating * :</p>
                                                <div class="text-primary">
                                                    <i class="far fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                    <i class="far fa-star"></i>
                                                </div>
                                            </div>
                                            <form>
                                                <div class="form-group">
                                                    <label for="message">Your Review *</label>
                                                    <textarea id="message" cols="30" rows="5" class="form-control"></textarea>
                                                </div>
                                                <div class="form-group">
                                                    <label for="name">Your Name *</label>
                                                    <input type="text" class="form-control" id="name" />
                                                </div>
                                                <div class="form-group">
                                                    <label for="email">Your Email *</label>
                                                    <input type="email" class="form-control" id="email" />
                                                </div>
                                                <div class="form-group mb-0">
                                                    <input type="submit" value="Leave Your Review" class="btn btn-primary px-3" />
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div> */}
                            </div>
                        </div>
                    </div>
                </div>
            }
            {/* <Features />
            <Footer /> */}
        </>
    )
}

export default ProductDetail;