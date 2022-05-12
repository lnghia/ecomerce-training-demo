import React, { useState, useEffect, useRef } from 'react';
import classNames from 'classnames';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Toast } from 'primereact/toast';
import { Button } from 'primereact/button';
import { FileUpload } from 'primereact/fileupload';
import { Rating } from 'primereact/rating';
import { Toolbar } from 'primereact/toolbar';
import { InputTextarea } from 'primereact/inputtextarea';
import { RadioButton } from 'primereact/radiobutton';
import { InputNumber } from 'primereact/inputnumber';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { ToggleButton } from 'primereact/togglebutton';
import { ProductService } from '../service/ProductService';
import uploadImage from '../firebase/upload';

import {
    fetchProductAllApi,
    deleteProductApi,
    fetchCategories,
    fetchGenders,
    fetchTechnologies,
    fetchSports,
    fetchSizesApi,
    createProductApi,
    updateProductApi
} from '../axios/api/product';

import {
    fetchActiveUsers,
    fetchInActiveUsers,
    fetchRoles,
    disableUser,
    assignUserRole,
    activateUser
} from '../axios/api/user';


const ManageUser = () => {
    let emptyProduct = {
        id: null,
        name: '',
        thumbnail: null,
        description: '',
        categories: [],
        technologies: [],
        sport: null,
        gender: null,
        price: 0,
        averageRating: 0,
        productSizeDtoList: [],
        year: 2022
    };

    const [products, setProducts] = useState(null);
    const [roleList, setRoleList] = useState([]);
    const [imgUrl, setImgUrl] = useState(null);
    const [preChanged, setPreChanged] = useState(null);
    const [activeFilter, setActiveFilter] = useState(true);
    const [selectedFile, setSelectedFile] = useState(null);
    const [categoryList, setCategoryList] = useState([]);
    const [technologyList, setTechnogyList] = useState([]);
    const [genderList, setGenderList] = useState([]);
    const [sizeList, setSizeList] = useState([]);
    const [sportList, setSportList] = useState([]);
    const [productDialog, setProductDialog] = useState(false);
    const [deleteProductDialog, setDeleteProductDialog] = useState(false);
    const [deleteProductsDialog, setDeleteProductsDialog] = useState(false);
    const [product, setProduct] = useState(emptyProduct);
    const [selectedProducts, setSelectedProducts] = useState(null);
    const [submitted, setSubmitted] = useState(false);
    const [globalFilter, setGlobalFilter] = useState(null);
    const [updateProduct, setUpdateProduct] = useState(false);
    const [preState, setPreState] = useState(emptyProduct);
    const toast = useRef(null);
    const dt = useRef(null);

    useEffect(() => {
        async function fetchData() {
            let productList = (activeFilter) ? await fetchActiveUsers() : await fetchInActiveUsers();
            let roles = await fetchRoles();

            setProducts(productList);
            setRoleList(roles);
        }

        fetchData();
    }, [activeFilter]);

    console.log(products);

    const hideDialog = () => {
        if (updateProduct) {
            setProduct(preChanged);
            setPreChanged(null);
        }

        setSubmitted(false);
        setProductDialog(false);
    }

    const hideDeleteProductDialog = () => {
        setDeleteProductDialog(false);
    }

    const hideDeleteProductsDialog = () => {
        setDeleteProductsDialog(false);
    }

    const saveProduct = async () => {
        setSubmitted(true);

        let _products = [...products];
        let _product = { ...product };

        let result = await assignUserRole(product.id, product.roles[0].id);

        if (result !== null) {
            const index = findIndexById(product.id);

            _products[index] = result;
            toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Product Updated', life: 3000 });
        } else {
            toast.current.show({ severity: 'error', summary: 'Error', detail: 'Failed to update product', life: 3000 });
        }

        setProducts(_products);
        setProductDialog(false);
        setProduct(emptyProduct);
    }

    const editProduct = (product) => {
        setUpdateProduct(true);
        setProduct({ ...product });
        setPreChanged({ ...product });
        setProductDialog(true);
    }

    const confirmDeleteProduct = (product) => {
        setProduct(product);
        setDeleteProductDialog(true);
    }

    const deleteProduct = () => {
        let _products = products.filter(val => val.id !== product.id);
        setDeleteProductDialog(false);
        async function _deleteProduct() {
            let rs = activeFilter ? await disableUser(product.id) : await activateUser(product.id);

            if (rs !== null) {
                setProducts(_products);
                toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Product Deleted', life: 3000 });
            } else {
                toast.current.show({ severity: 'error', summary: 'Error', detail: 'Failed to delete product', life: 3000 });
            }
        }
        _deleteProduct();
        setProduct(emptyProduct);
    }

    const findIndexById = (id) => {
        let index = -1;
        for (let i = 0; i < products.length; i++) {
            if (products[i].id === id) {
                index = i;
                break;
            }
        }

        return index;
    }

    const deleteSelectedProducts = () => {
        let _products = products.filter(val => !selectedProducts.includes(val));
        setProducts(_products);
        setDeleteProductsDialog(false);
        setSelectedProducts(null);
        toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Products Deleted', life: 3000 });
    }

    const findRoleById = (id) => {
        let rs = null;

        for (let index in roleList) {
            if (roleList[index].id === id) {
                rs = roleList[index].name;
                break;
            }
        }

        return rs;
    }

    const onRoleChange = (e) => {
        let _product = { ...product };
        let roleName = findRoleById(e.value);

        console.log(e.value + ' ' + roleName);

        _product['roles'].splice(0, 1);
        _product['roles'].push({
            id: e.value,
            name: roleName
        });

        setProduct(_product);
    }

    const getProductRole = (roleId) => {
        let roles = product.roles;

        if (roles !== null) {
            for (let ind in roles) {
                if (roles[ind].id === roleId) {
                    return true;
                }
            }
        }

        return false;
    }

    const leftToolbarTemplate = () => {
        return (
            <React.Fragment>
                <div className="my-2">
                    <ToggleButton checked={activeFilter} onChange={(e) => setActiveFilter(e.value)} onLabel="Active" offLabel="InActive" />
                </div>
            </React.Fragment>
        )
    }

    const rightToolbarTemplate = () => {
        return (
            <React.Fragment>
                {/* <FileUpload mode="basic" accept="image/*" maxFileSize={1000000} label="Import" chooseLabel="Import" className="mr-2 inline-block" />
                <Button label="Export" icon="pi pi-upload" className="p-button-help" onClick={exportCSV} /> */}
            </React.Fragment>
        )
    }

    const codeBodyTemplate = (rowData) => {
        return (
            <>
                <span className="p-column-title">Code</span>
                {rowData.id}
            </>
        );
    }

    const nameBodyTemplate = (rowData) => {
        return (
            <>
                <span className="p-column-title">Name</span>
                {rowData.firstName + ' ' + rowData.lastName}
            </>
        );
    }

    const emailBodyTemplate = (rowData) => {
        return (
            <>
                <span className="p-column-title">Email</span>
                {rowData.email}
            </>
        );
    }

    const imageBodyTemplate = (rowData) => {
        return (
            <>
                <span className="p-column-title">Avatar</span>
                <img src={rowData.avatar} alt={rowData.avatar} className="shadow-2" width="100" />
            </>
        )
    }

    const createdDateBodyTemplate = (rowData) => {
        return (
            <>
                <span className="p-column-title">Created date</span>
                {rowData.createdDate}
            </>
        );
    }

    const roleBodyTemplate = (rowData) => {
        return (
            <>
                <span className="p-column-title">Roles</span>
                {rowData.roles.length > 0 && rowData.roles[0].name}
            </>
        );
    }

    const updatedDateBodyTemplate = (rowData) => {
        return (
            <>
                <span className="p-column-title">Updated date</span>
                <span>{rowData.lastModifiedDate}</span>
            </>
        )
    }

    const actionBodyTemplate = (rowData) => {
        return (
            <div className="actions">
                <Button icon="pi pi-pencil" className="p-button-rounded p-button-success mr-2" onClick={() => editProduct(rowData)} disabled={!activeFilter || rowData.roles[0].name === 'ROLE_ADMIN'} />
                {activeFilter ? <Button icon="pi pi-times" className="p-button-rounded p-button-warning mt-2" onClick={() => confirmDeleteProduct(rowData)} disabled={rowData.roles[0].name === 'ROLE_ADMIN'} /> :
                    <Button icon="pi pi-check" className="p-button-rounded p-button-success mt-2" onClick={() => confirmDeleteProduct(rowData)} />}
            </div>
        );
    }

    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">Manage Users</h5>
            <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search" />
                <InputText type="search" onInput={(e) => setGlobalFilter(e.target.value)} placeholder="Search..." />
            </span>
        </div>
    );

    const productDialogFooter = (
        <>
            <Button label="Cancel" icon="pi pi-times" className="p-button-text" onClick={hideDialog} />
            <Button label="Save" icon="pi pi-check" className="p-button-text" onClick={saveProduct} />
        </>
    );
    const deleteProductDialogFooter = (
        <>
            <Button label="No" icon="pi pi-times" className="p-button-text" onClick={hideDeleteProductDialog} />
            <Button label="Yes" icon="pi pi-check" className="p-button-text" onClick={deleteProduct} />
        </>
    );
    const deleteProductsDialogFooter = (
        <>
            <Button label="No" icon="pi pi-times" className="p-button-text" onClick={hideDeleteProductsDialog} />
            <Button label="Yes" icon="pi pi-check" className="p-button-text" onClick={deleteSelectedProducts} />
        </>
    );

    return (
        <div className="grid crud-demo">
            <div className="col-12">
                <div className="card">
                    <Toast ref={toast} />
                    <Toolbar className="mb-4" left={leftToolbarTemplate} right={rightToolbarTemplate}></Toolbar>

                    <DataTable ref={dt} value={products} selection={selectedProducts} onSelectionChange={(e) => setSelectedProducts(e.value)}
                        dataKey="id" paginator rows={10} rowsPerPageOptions={[5, 10, 25]} className="datatable-responsive"
                        paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                        currentPageReportTemplate="Showing {first} to {last} of {totalRecords} products"
                        globalFilter={globalFilter} emptyMessage="No products found." header={header} responsiveLayout="scroll">
                        {/* <Column selectionMode="multiple" headerStyle={{ width: '3rem' }}></Column> */}
                        <Column field="code" header="Id" sortable body={codeBodyTemplate} headerStyle={{ width: '14%', minWidth: '10rem' }}></Column>
                        <Column field="name" header="Name" sortable body={nameBodyTemplate} headerStyle={{ width: '14%', minWidth: '10rem' }}></Column>
                        <Column field="email" header="Email" body={emailBodyTemplate} sortable headerStyle={{ width: '14%', minWidth: '8rem' }}></Column>
                        <Column header="Image" body={imageBodyTemplate} headerStyle={{ width: '14%', minWidth: '10rem' }}></Column>
                        <Column field="createdDate" header="Created date" sortable body={createdDateBodyTemplate} headerStyle={{ width: '14%', minWidth: '10rem' }}></Column>
                        <Column field="updatedDate" header="Updated date" body={updatedDateBodyTemplate} sortable headerStyle={{ width: '14%', minWidth: '10rem' }}></Column>
                        <Column field="role" header="Role" body={roleBodyTemplate} sortable headerStyle={{ width: '14%', minWidth: '10rem' }}></Column>
                        <Column body={actionBodyTemplate}></Column>
                    </DataTable>

                    <Dialog visible={productDialog} style={{ width: '450px' }} header="User Role" modal className="p-fluid" footer={productDialogFooter} onHide={hideDialog}>
                        {product.avatar && <img src={product.avatar} alt={product.avatar} width="150" className="mt-0 mx-auto mb-5 block shadow-2" />}
                        <div className="field">
                            <label className="mb-3">Role</label>
                            <div className="formgrid grid">
                                {
                                    roleList.map(role => {
                                        return (
                                            <div className="field-radiobutton col-6" key={role.name}>
                                                <RadioButton inputId={role.name} name="category" value={role.id} onChange={onRoleChange} checked={getProductRole(role.id)} />
                                                <label htmlFor="category1">{role.name}</label>
                                                {/* <RadioButton inputId={role.name} name="category" value={role.id} onChange={onCategoryChange} /> // checked={getProductCategory(category.id) || product.categories.indexOf(category.id) !== -1} */}
                                            </div>
                                        )
                                    })
                                }
                            </div>
                        </div>
                    </Dialog>

                    <Dialog visible={deleteProductDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteProductDialogFooter} onHide={hideDeleteProductDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {product && <span>Are you sure you want to disable <b>{product.email}</b>?</span>}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteProductsDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteProductsDialogFooter} onHide={hideDeleteProductsDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {product && <span>Are you sure you want to disable the selected user?</span>}
                        </div>
                    </Dialog>
                </div>
            </div>
        </div>
    );
}

const comparisonFn = function (prevProps, nextProps) {
    return prevProps.location.pathname === nextProps.location.pathname;
};

export default React.memo(ManageUser, comparisonFn);