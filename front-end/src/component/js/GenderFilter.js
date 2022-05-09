import React, { useEffect, useState } from 'react';

import { useSelector, useDispatch } from 'react-redux';
import { genderListSelector, genderFilterValuesSelector } from '../../redux/selectors';
import { updateGenderFilterValueAction } from '../../redux/slices/categorySlice';

const GenderFilter = (props) => {
    const genderList = useSelector(genderListSelector);
    const genderFilterValues = useSelector(genderFilterValuesSelector);
    const dispatch = useDispatch();

    const [genderCheckedList, setGenderCheckedList] = useState(
        genderFilterValues.length === 0 ?
            new Array(genderList.length).fill(false) :
            initGenderCheckedList()
    );

    function initGenderCheckedList() {
        let rs = [];

        for (let i = 0; i < genderList.length; ++i) {
            rs.push(genderFilterValues.indexOf(genderList[i].id) !== -1 ? true : false);
        }

        return rs;
    }

    function handleOnChange(position) {
        let updateCheckedState = genderCheckedList.map((item, index) => {
            return index === position ? !item : item;
        });
        setGenderCheckedList(updateCheckedState);
        dispatch(updateGenderFilterValueAction(genderList[position].id));
    }

    useEffect(() => {

    }, [genderCheckedList]);

    return (
        <div class="border-bottom mb-4 pb-4">
            <h5 class="font-weight-semi-bold mb-4">Filter by gender</h5>
            <form>
                {/* <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                    <input type="checkbox" class="custom-control-input" checked id="color-all" />
                    <label class="custom-control-label" for="price-all">All Gender</label>
                    <span class="badge border font-weight-normal">1000</span>
                </div> */}
                {genderList !== null &&
                    genderList.map((gender, index) => {
                        return (
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input type="checkbox" class="custom-control-input" id={gender.name} onChange={() => handleOnChange(index)} checked={genderCheckedList[index] || (genderFilterValues.indexOf(gender.id) !== -1)} />
                                <label class="custom-control-label" for={gender.name}>{gender.name}</label>
                            </div>
                        )
                    })
                }
            </form>
        </div>
    )
}

export default GenderFilter;