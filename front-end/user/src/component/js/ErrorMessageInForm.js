import React from "react";

const FormErrorMsg = (props) => {
    return (
        <p style={{color: "red"}}>{props.message}</p>
    )
}

export default FormErrorMsg;