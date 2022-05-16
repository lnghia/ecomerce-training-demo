import React, { useState, useEffect } from "react";
import Pagination from './Pagination';
import { Button, Table } from "react-bootstrap";
import StarRatingForShow from "./StarRatingForShow";

import { fetchProductList } from "../../api/product";

const ManageProducts = (props) => {
    const [productList, setProductList] = useState([]);
    const [totalPages, setTotalPages] = useState(0);
    const [currPage, setCurrPage] = useState(1);
    const [keyword, setKeyword] = useState("");

    useEffect(() => {
        async function fetchProducts() {
            let result = await fetchProductList([], [], [], [], keyword);
            setProductList(result.content);
            setTotalPages(result.totalPages);
        }

        fetchProducts();
    }, [currPage]);

    const travelToPage = (page) => {
        setCurrPage(page);
    }

    function handleOnEdit(id) {

    }

    function handleOnDelete(id) {

    }

    const ActionsColumn = (props) => {
        return (
            <td>
                <Button variant="primary" onClick={() => handleOnEdit(props.productId)}>Edit</Button><br />
                <Button variant="danger" onClick={() => handleOnDelete(props.productId)}>Delete</Button><br />
            </td>
        )
    }

    return (
        <Table striped bordered hover size="sm">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Reviews</th>
                    <th>Created date</th>
                    <th>Updated date</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                {
                    productList.map(product => {
                        return (
                            <tr>
                                <td>{product.name}</td>
                                <td>{product.thumbnail}</td>
                                <td>${product.price}</td>
                                <td><StarRatingForShow rating={product.averageRating} /></td>
                                <td>{product.createdDate}</td>
                                <td>{product.updatedDate}</td>
                                <td><ActionsColumn productId={product.id} /></td>
                            </tr>
                        )
                    })
                }
            </tbody>
            <div>
                <Pagination showMax={10} totalPages={totalPages} onPageChange={travelToPage} />
            </div>
        </Table>
    )
}

export default ManageProducts;