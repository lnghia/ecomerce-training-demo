import React, { useState } from 'react';
import StarRatings from 'react-star-ratings';

const StarRatingForRating = (props) => {
    const [rating, setRating] = useState(props.currRating);

    const setNewRating = (newRating) => {
        setRating(newRating);
        props.onChangeRating(newRating);
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