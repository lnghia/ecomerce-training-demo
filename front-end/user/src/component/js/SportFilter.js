import React, { useEffect, useState } from 'react';

import { useSelector, useDispatch } from 'react-redux';
import { sportListSelector, sportFilterValuesSelector } from '../../redux/selectors';
import { updateSportFilterValueAction } from '../../redux/slices/categorySlice';

const SportFilter = (props) => {
    const sportFilterValues = useSelector(sportFilterValuesSelector);
    const sportList = useSelector(sportListSelector);
    const dispatch = useDispatch();

    const [sportCheckedList, setSportCheckedList] = useState(
        sportFilterValues.length === 0 ?
            new Array(sportList.length).fill(false) :
            initSportCheckedList()
    );

    function initSportCheckedList() {
        let rs = [];

        for (let i = 0; i < sportList.length; ++i) {
            rs.push(sportFilterValues.indexOf(sportList[i].id) !== -1 ? true : false);
        }

        return rs;
    }

    function handleOnChange(position) {
        let updateCheckedState = sportCheckedList.map((item, index) => {
            return index === position ? !item : item;
        });
        setSportCheckedList(updateCheckedState);
        dispatch(updateSportFilterValueAction(sportList[position].id));
    }

    useEffect(() => {

    }, [sportCheckedList]);

    return (
        <div class="border-bottom mb-4 pb-4">
            <h5 class="font-weight-semi-bold mb-4">Filter by sport</h5>
            <form>
                {/* <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                    <input type="checkbox" class="custom-control-input" checked id="color-all" />
                    <label class="custom-control-label" for="price-all">All Sport</label>
                    <span class="badge border font-weight-normal">1000</span>
                </div> */}
                {sportList !== 'array' &&
                    sportList.map((sport, index) => {
                        return (
                            <div class="custom-control custom-checkbox d-flex align-items-center justify-content-between mb-3">
                                <input type="checkbox" class="custom-control-input" id={sport.name} onChange={() => handleOnChange(index)} checked={sportCheckedList[index] || (sportFilterValues.indexOf(sport.id) !== -1)} />
                                <label class="custom-control-label" for={sport.name}>{sport.name}</label>
                            </div>
                        )
                    })
                }
            </form>
        </div>
    )
}

export default SportFilter;