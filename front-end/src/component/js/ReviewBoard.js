import React, { useEffect, useState } from "react";
import StarRatingForShow from './StarRatingForShow';
import Pagination from './Pagination'

import { fetchReviewsOnProduct } from '../../api/product';

import "../css/ReviewBoard.css";

const ReviewBoard = (props) => {
    const [currPage, setCurrPage] = useState(1);
    const [reviews, setReviews] = useState([]);
    const [totalPages, setTotalPages] = useState(0);

    useEffect(() => {
        const fetchReviews = async () => {
            let result = await fetchReviewsOnProduct(props.productId, currPage - 1, 3);
            setReviews(result.content);
            setTotalPages(result.totalPages);
        }

        fetchReviews();
    }, [currPage]);

    const travelToPage = (page) => {
        setCurrPage(page);
    }

    return (
        <div className="col-md-6">
            {/* <h4 className="mb-4">1 review for "Colorful Stylish Shirt"</h4> */}
            {
                reviews.map(review => {
                    return (
                        <div className="media mb-4">
                            <img src="img/user.jpg" alt="Image" className="img-fluid mr-3 mt-1" style={{ width: '45px' }} />
                            <div className="media-body">
                                <h6>{review.user.firstName + ' ' + review.user.lastName}<small> - <i>{review.lastModifiedDate}</i></small></h6>
                                <div className="text-primary mb-2">
                                    <StarRatingForShow rating={review.rating} />
                                </div>
                                <p>{review.comment}</p>
                            </div>
                        </div>
                    )
                })
            }
            <div>
                <Pagination showMax={3} totalPages={totalPages} onPageChange={travelToPage} />
            </div>
        </div>
    )
}

export default ReviewBoard;