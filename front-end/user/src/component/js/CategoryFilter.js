import React, { useEffect, useState } from "react";

import { useSelector, useDispatch } from "react-redux";
import { categoryListSelector, categoryFilterValuesSelector } from "../../redux/selectors";
import { updateCategoryFilterValueAction } from "../../redux/slices/categorySlice";

const CategoryFilter = (props) => {
    const categoryList = useSelector(categoryListSelector);
    const categoryFilterValues = useSelector(categoryFilterValuesSelector);
    const dispatch = useDispatch();

    const [categoryCheckedList, setcategoryCheckedList] = useState(
        categoryFilterValues.length === 0 ?
            new Array(categoryList.length).fill(false) :
            initcategoryCheckedList()
    );

    function initcategoryCheckedList() {
        let rs = [];

        for (let i = 0; i < categoryList.length; ++i) {
            rs.push(categoryFilterValues.indexOf(categoryList[i].id) !== -1 ? true : false);
        }

        return rs;
    }

    function handleOnChange(position) {
        let updateCheckedState = categoryCheckedList.map((item, index) => {
            return index === position ? !item : item;
        });
        setcategoryCheckedList(updateCheckedState);
        dispatch(updateCategoryFilterValueAction(categoryList[position].id));
    }

    useEffect(() => {

    }, [categoryCheckedList]);

    return (
        <div class="border-bottom mb-4 pb-4">
            <h5 class="font-weight-semi-bold mb-4">Filter by type</h5>
            <form>
                {/* <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                    <input type="checkbox" class="custom-control-input" checked id="price-all" />
                    <label class="custom-control-label" for="price-all">All Type</label>
                    <span class="badge border font-weight-normal">1000</span>
                </div> */}
                {categoryList !== null &&
                    categoryList.map((item, index) => {
                        return (
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input type="checkbox" class="custom-control-input" id={item.name} onChange={() => handleOnChange(index)} checked={categoryCheckedList[index] || (categoryFilterValues.indexOf(item.id) !== -1)} />
                                <label class="custom-control-label" for={item.name}>{item.name}</label>
                            </div>
                        )
                    })
                }
            </form>
        </div>
    )
}

export default CategoryFilter;