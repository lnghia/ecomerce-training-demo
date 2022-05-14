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
import { ProductService } from '../service/ProductService';
import uploadImage from '../firebase/upload';

import {
    fetchCategoriesByType,
    updateCategoryApi,
    createCategoryApi
} from '../axios/api/category';
import { Dropdown } from 'primereact/dropdown';

const ManageCategories = () => {
    let emptyProduct = {
        id: null,
        name: '',
        description: ''
    };

    const [products, setProducts] = useState(null);
    const [imgUrl, setImgUrl] = useState(null);
    const [categoryFilter, setCategoryFilter] = useState('category');
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

    const categoriesTypeItems = [
        { label: 'Category', value: 'category' },
        { label: 'Technology', value: 'technology' },
        { label: 'Sport', value: 'sport' },
        { label: 'Size', value: 'size' }
    ];

    useEffect(() => {
        async function fetchData() {
            let rs = await fetchCategoriesByType(categoryFilter);

            setProducts(rs);
        }

        fetchData();
    }, [categoryFilter]);

    console.log(products);

    const formatCurrency = (value) => {
        return value.toLocaleString('en-US', { style: 'currency', currency: 'USD' });
    }

    const openNew = () => {
        let _product = emptyProduct;

        setUpdateProduct(false);
        setProduct(_product);
        setSubmitted(false);
        setProductDialog(true);
    }

    const hideDialog = () => {
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

        if (product.name.trim()) {
            let _products = [...products];
            let _product = { ...product };

            if (product.id) {

                let result = await updateCategoryApi(categoryFilter, product.id, product.name, product.description);

                console.log('update ' + result);

                if (result !== null) {
                    const index = findIndexById(product.id);

                    _products[index] = result;
                    toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Product Updated', life: 3000 });
                } else {
                    toast.current.show({ severity: 'error', summary: 'Error', detail: 'Failed to update product', life: 3000 });
                }
            }
            else {
                let result = await createCategoryApi(categoryFilter, product.name, product.description);

                console.log('create ' + result);

                if (result !== null) {
                    _product.id = result.id;
                    _products.push(result);
                    toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Product has been created', life: 3000 });
                } else {
                    toast.current.show({ severity: 'error', summary: 'Error', detail: 'Failed to create product', life: 3000 });
                }
            }

            setProducts(_products);
            setProductDialog(false);
            setProduct(emptyProduct);
        }
    }

    const editProduct = (product) => {
        setUpdateProduct(true);
        setProduct({ ...product });
        setProductDialog(true);
    }

    const confirmDeleteProduct = (product) => {
        setProduct(product);
        setDeleteProductDialog(true);
    }

    const deleteProduct = () => {
        let _products = products.filter(val => val.id !== product.id);
        setProducts(_products);
        setDeleteProductDialog(false);
        // async function _deleteProduct() {
        //     let rs = await deleteProductApi(product.id);

        //     if (rs) {
        //         toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Product Deleted', life: 3000 });
        //     } else {
        //         toast.current.show({ severity: 'error', summary: 'Error', detail: 'Failed to delete product', life: 3000 });
        //     }
        // }
        // _deleteProduct();
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

    const createId = () => {
        let id = '';
        let chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
        for (let i = 0; i < 5; i++) {
            id += chars.charAt(Math.floor(Math.random() * chars.length));
        }
        return id;
    }

    const exportCSV = () => {
        dt.current.exportCSV();
    }

    const confirmDeleteSelected = () => {
        setDeleteProductsDialog(true);
    }

    const deleteSelectedProducts = () => {
        let _products = products.filter(val => !selectedProducts.includes(val));
        setProducts(_products);
        setDeleteProductsDialog(false);
        setSelectedProducts(null);
        toast.current.show({ severity: 'success', summary: 'Successful', detail: 'Products Deleted', life: 3000 });
    }

    const onCategoryChange = (e) => {
        let _product = { ...product };
        let index = _product['categories'].indexOf(e.value);

        const hasCategoryBeenChecked = () => {
            for (let index in _product['categories']) {
                if (_product['categories'][index].id === e.value) {
                    return index;
                }
            }

            return -1;
        }

        if (index !== -1) {
            _product['categories'].splice(index, 1);
        } else {
            let _index = hasCategoryBeenChecked();

            if (_index !== -1) {
                _product['categories'].splice(_index, 1);
            } else {
                _product['categories'].push(e.value);
            }
        }

        setProduct(_product);
    }

    const onTechnologyChange = (e) => {
        let _product = { ...product };
        let index = _product['technologies'].indexOf(e.value);

        const hasBeenChecked = () => {
            for (let index in _product['technologies']) {
                if (_product['technologies'][index].id === e.value) {
                    return index;
                }
            }

            return -1;
        }

        if (index !== -1) {
            _product['technologies'].splice(index, 1);
        } else {
            let _index = hasBeenChecked();

            if (_index !== -1) {
                _product['technologies'].splice(_index, 1);
            } else {
                _product['technologies'].push(e.value);
            }
        }
        setProduct(_product);
    }

    const onGenderChange = (e) => {
        let _product = { ...product };

        _product['gender'] = _product['gender'] === e.value ? null : e.value;
        setProduct(_product);
    }

    const onSportChange = (e) => {
        let _product = { ...product };

        _product['sport'] = _product['sport'] === e.value ? null : e.value;
        setProduct(_product);
    }

    const onInputChange = (e, name) => {
        const val = (e.target && e.target.value) || '';
        let _product = { ...product };
        _product[`${name}`] = val;

        setProduct(_product);
    }

    const onSizeValueChange = (e, _sizeId) => {
        let _product = { ...product };
        let index = -1;

        for (let ind in _product.productSizeDtoList) {
            if (updateProduct && _product.sizes[ind].size.id === _sizeId) {
                index = ind;
                break;
            } else if (_product.productSizeDtoList[ind].sizeId === _sizeId) {
                index = ind;
                break;
            }
        }

        if (updateProduct) {
            if (index !== -1) {
                _product.sizes[index].inStock = e.value;
            } else {
                _product.sizes.push({
                    size: {
                        id: _sizeId
                    },
                    inStock: e.value
                })
            }
        } else {
            if (index !== -1) {
                _product.productSizeDtoList[index].number = e.value;
            } else {
                _product.productSizeDtoList.push({
                    sizeId: _sizeId,
                    number: e.value
                });
            }
        }

        setProduct(_product);
    }

    const myUploader = (e) => {
        let imgName = createId();
        setSelectedFile(e.files[0]);
    }

    const getProductSizeInStock = (sizeId) => {
        let sizes = product.sizes;

        if (sizes !== null) {
            for (let item in sizes) {
                if (sizes[item].size.id === sizeId) {
                    return sizes[item].inStock;
                }
            }
        }

        return 0;
    }

    const getProductCategory = (categoryId) => {
        let categories = product.categories;

        if (categories !== null) {
            for (let ind in categories) {
                if (categories[ind].id === categoryId) {
                    return true;
                }
            }
        }

        return false;
    }

    const getProductTechnology = (technologyId) => {
        let technologies = product.technologies;

        if (technologies !== null) {
            for (let index in technologies) {
                if (technologies[index].id === technologyId) {
                    return true;
                }
            }
        }

        return false;
    }

    const getProductGender = (genderId) => {
        let gender = product.gender;

        if (gender !== null) {
            return gender.id === genderId;
        }

        return false;
    }

    const getProductSport = (sportId) => {
        let sport = product.sport;

        if (sport !== null) {
            return sport.id === sportId;
        }

        return false;
    }

    const onInputNumberChange = (e, name) => {
        const val = e.value || 0;
        let _product = { ...product };
        _product[`${name}`] = val;

        setProduct(_product);
    }

    const leftToolbarTemplate = () => {
        return (
            <React.Fragment>
                <div className="my-2">
                    <Button label="New" icon="pi pi-plus" className="p-button-success mr-2" onClick={openNew} />
                    <Dropdown value={categoryFilter} options={categoriesTypeItems} onChange={(e) => setCategoryFilter(e.value)} />
                    {/* <Button label="Delete" icon="pi pi-trash" className="p-button-danger" onClick={confirmDeleteSelected} disabled={!selectedProducts || !selectedProducts.length} /> */}
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
                {rowData.name}
            </>
        );
    }

    const descriptionBodyTemplate = (rowData) => {
        return (
            <>
                <span className="p-column-title">Description</span>
                {rowData.description}
            </>
        )
    }

    const actionBodyTemplate = (rowData) => {
        return (
            <div className="actions">
                <Button icon="pi pi-pencil" className="p-button-rounded p-button-success mr-2" onClick={() => editProduct(rowData)} />
                {/* <Button icon="pi pi-trash" className="p-button-rounded p-button-warning mt-2" onClick={() => confirmDeleteProduct(rowData)} /> */}
            </div>
        );
    }

    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">Manage </h5>
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
                        <Column field="description" header="Description" body={descriptionBodyTemplate} headerStyle={{ width: '60%', minWidth: '10rem' }}></Column>
                        <Column body={actionBodyTemplate}></Column>
                    </DataTable>

                    <Dialog visible={productDialog} style={{ width: '450px' }} header="Product Details" modal className="p-fluid" footer={productDialogFooter} onHide={hideDialog}>
                        {/* {product.thumbnail && <img src={product.thumbnail} alt={product.thumbnail} width="150" className="mt-0 mx-auto mb-5 block shadow-2" />} */}
                        <div className="field">
                            <label htmlFor="name">Name</label>
                            <InputText id="name" value={product.name} onChange={(e) => onInputChange(e, 'name')} required autoFocus className={classNames({ 'p-invalid': submitted && !product.name })} />
                            {submitted && !product.name && <small className="p-invalid">Name is required.</small>}
                        </div>
                        <div className="field">
                            <label htmlFor="description">Description</label>
                            <InputTextarea id="description" value={product.description} onChange={(e) => onInputChange(e, 'description')} required rows={3} cols={20} />
                        </div>
                    </Dialog>

                    <Dialog visible={deleteProductDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteProductDialogFooter} onHide={hideDeleteProductDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {product && <span>Are you sure you want to delete <b>{product.name}</b>?</span>}
                        </div>
                    </Dialog>

                    <Dialog visible={deleteProductsDialog} style={{ width: '450px' }} header="Confirm" modal footer={deleteProductsDialogFooter} onHide={hideDeleteProductsDialog}>
                        <div className="flex align-items-center justify-content-center">
                            <i className="pi pi-exclamation-triangle mr-3" style={{ fontSize: '2rem' }} />
                            {product && <span>Are you sure you want to delete the selected products?</span>}
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

export default React.memo(ManageCategories, comparisonFn);