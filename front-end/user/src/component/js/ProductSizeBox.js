import React, { useState } from "react";

const ProductSizeBox = (props) => {
    return (
        <div class="d-flex mb-3">
            <p class="text-dark font-weight-medium mb-0 mr-3">Sizes:</p>
            <form>
                {
                    props.sizes.map(size => {
                        return (
                            <div class="custom-control custom-radio custom-control-inline">
                                <input type="radio" disabled={size.inStock <= 0} class="custom-control-input" name="size" id={size.size.id} />
                                <label class="custom-control-label" for={size.size.id}>{size.size.name}</label>
                            </div>
                        )
                    })
                }
            </form>
        </div>
    )
}

export default ProductSizeBox;