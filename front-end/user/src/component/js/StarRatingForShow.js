import React, { useState } from 'react';
import StarRatings from 'react-star-ratings';

const StarRatingForShow = (props) => {
    const setNewRating = (rating) => {
        console.log(rating);
    }

    return (
        <StarRatings
            rating={props.rating}
            // changeRating={setNewRating}
            starRatedColor="orange"
            name='rating'
            starDimension='15px'
            starSpacing='0px'
        />
    )
}

export default StarRatingForShow;