import React, { useState } from 'react';
import StarRatings from 'react-star-ratings';

const StarRatingForRating = (props) => {
    const [rating, setRating] = useState(0);

    const setNewRating = (newRating) => {
        setRating(newRating);
        console.log(rating);
    }

    return (
        <StarRatings
            rating={rating}
            changeRating={setNewRating}
            starRatedColor="orange"
            name='rating'
            starDimension='25px'
            starSpacing='0px'
        />
    )
}

export default StarRatingForRating;