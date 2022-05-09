import React, { useState } from "react";
import CategoryFilter from "./CategoryFilter";
import TechnologyFilter from "./TechnologyFilter";
import SportFilter from "./SportFilter";
import GenderFilter from "./GenderFilter";

const GlobalFilter = (props) => {

    return (
        <div class="col-lg-3 col-md-12">
            <CategoryFilter />
            <GenderFilter />
            <SportFilter />
            <TechnologyFilter />
        </div>
    )
}

export default GlobalFilter;