import React, { useEffect, useState } from "react";

import { useSelector, useDispatch } from "react-redux";
import { technologyListSelector, technologyFilterValuesSelector } from "../../redux/selectors";
import { updateTechnologyFilterValueAction } from "../../redux/slices/categorySlice";

const TechnologyFilter = (props) => {
    const technologyFilterValues = useSelector(technologyFilterValuesSelector);
    const technologyList = useSelector(technologyListSelector);
    const dispatch = useDispatch();

    const [technologyCheckedList, setTechnologyCheckedList] = useState(
        technologyFilterValues.length === 0 ?
            new Array(technologyList.length).fill(false) :
            initTechnologyCheckedList()
    );

    function initTechnologyCheckedList() {
        let rs = [];

        for (let i = 0; i < technologyList.length; ++i) {
            rs.push(technologyFilterValues.indexOf(technologyList[i].id) !== -1 ? true : false);
        }

        return rs;
    }

    function handleOnChange(position) {
        let updateCheckedState = technologyCheckedList.map((item, index) => {
            return index === position ? !item : item;
        });
        setTechnologyCheckedList(updateCheckedState);
        dispatch(updateTechnologyFilterValueAction(technologyList[position].id));
    }

    useEffect(() => {

    }, [technologyCheckedList]);

    return (
        <div class="border-bottom mb-4 pb-4">
            <h5 class="font-weight-semi-bold mb-4">Filter by technology</h5>
            <form>
                {/* <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                    <input type="checkbox" class="custom-control-input" checked id="color-all" />
                    <label class="custom-control-label" for="price-all">All Technology</label>
                    <span class="badge border font-weight-normal">1000</span>
                </div> */}
                {technologyList !== null &&
                    technologyList.map((technology, index) => {
                        return (
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input type="checkbox" class="custom-control-input" id={technology.name} onChange={() => handleOnChange(index)} checked={technologyCheckedList[index] || (technologyFilterValues.indexOf(technology.id) !== -1)} />
                                <label class="custom-control-label" for={technology.name}>{technology.name}</label>
                            </div>
                        )
                    })
                }
            </form>
        </div>
    )
}

export default TechnologyFilter;