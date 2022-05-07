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
import ProductSpecifications from "./ProductSpecifications";
import StarRatingForRating from "./StarRatingForRating";
import ReviewBoard from "./ReviewBoard";

import { fetchProductDetail, rateProduct, findUserReviewOnProduct } from "../../api/product";
import { loginSuccessSelector } from "../../redux/selectors";
import { useForm } from "react-hook-form";
import toastr from "toastr";
import FormErrorMsg from "./ErrorMessageInForm";

const ProductDetail = (props) => {
    const { register, formState: { errors }, handleSubmit } = useForm();
    const [product, setProduct] = useState(null);
    const [rating, setRating] = useState(0);
    const [currUserReview, setCurrUserReview] = useState({
        rating: 0,
        comment: ""
    });
    let id = useSelector(selectedProductIdSelector);
    let loginSuccess = useSelector(loginSuccessSelector);

    useEffect(() => {
        async function fetchProduct() {
            let response = await fetchProductDetail(parseInt(id));
            setProduct(response);
        }

        async function fetchUserReviewOnProduct() {
            let response = await findUserReviewOnProduct(parseInt(id));
            setCurrUserReview(response);
        }

        fetchProduct();

        if (loginSuccess) {
            fetchUserReviewOnProduct();
            setRating(currUserReview.rating);
        }

        console.log(id);
    }, []);

    const handleLeaveReview = async (data) => {
        let result = await rateProduct(product.id, data.comment, rating || currUserReview.rating);

        if (result) {
            toastr.success("You have rated product successfully", "Thank you!");
        }
    }

    const changeRating = (stars) => setRating(stars);

    return (
        <>
            {/* <TopBar /> */}
            <NavBar />
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
                                    <p>{product.description}</p><br />
                                    <ProductSpecifications sport={product.sport.name} year={product.year} technologies={product.technologies} />
                                </div>
                                <div class="tab-pane fade" id="tab-pane-3">
                                    <div class="row">
                                        <ReviewBoard productId={id} />
                                        {loginSuccess &&
                                            <div class="col-md-6">
                                                <h4 class="mb-4">Leave a review</h4>
                                                <small>Your email address will not be published. Required fields are marked *</small>
                                                <div class="d-flex my-3">
                                                    <p class="mb-0 mr-2 text-center">Your Rating * :</p>
                                                    <div class="text-primary">
                                                        <StarRatingForRating onChangeRating={changeRating} currRating={currUserReview.rating} />
                                                    </div>
                                                </div>
                                                <form onSubmit={handleSubmit(handleLeaveReview)}>
                                                    <div class="form-group">
                                                        <label for="message">Your Review *</label>
                                                        <textarea {...register("comment", { required: true })} id="message" cols="30" rows="5" class="form-control">{currUserReview.comment}</textarea>
                                                        {errors.comment?.type === 'required' && <FormErrorMsg message="Please be happy to leave some verbal review" />}
                                                    </div>
                                                    <div class="form-group mb-0">
                                                        <input type="submit" value={currUserReview.comment === "" ? "Leave Your Review" : "Update Your Review"} class="btn btn-primary px-3" />
                                                    </div>
                                                </form>
                                            </div>}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            }
            <Features />
            <Footer />
        </>
    )
}

export default ProductDetail;