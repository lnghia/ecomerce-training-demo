import React, { useState } from "react";

const ProductSpecifications = (props) => {
    return (
        <table class="table table-striped">
            <thead>
                <p>Specifications</p>
            </thead>
            <tbody>
                <tr>
                    <td>Sport</td>
                    <td>{props.sport}</td>
                </tr>
                <tr>
                    <td>Technology</td>
                    <td>
                        {
                            props.technologies.map(tech => {
                                return (
                                    <>
                                        <p>{tech.name}</p><br />
                                    </>
                                )
                            })
                        }
                    </td>
                </tr>
                <tr>
                    <td>Year</td>
                    <td>{props.year}</td>
                </tr>
            </tbody>
        </table>
    )
}

export default ProductSpecifications;