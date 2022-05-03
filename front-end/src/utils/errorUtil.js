import toastr from "toastr";

function raiseErrorMessages(errorResponse) {
    let errors = errorResponse.response.data.errors;

    Object.keys(errors).forEach(key => {
        toastr.error(errors[key], key);
    });
}

export { raiseErrorMessages };