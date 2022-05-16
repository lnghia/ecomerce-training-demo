import React, { useState } from "react";
import PaginationWrapper from "@vlsergey/react-bootstrap-pagination";

const PageTraveller = (props) => {
    const [currPage, setCurrPage] = useState(1);

    const handleOnClick = ({ target: { name, value } }) => {
        // console.log(page.value);
        setCurrPage(value);
        props.onPageChange(value);
    }

    return (
        // <></> 
        <PaginationWrapper totalPages={props.totalPages} value={currPage} onChange={handleOnClick} showFirstLast={false} firstPageValue={1} />
    )
}

export default PageTraveller;

