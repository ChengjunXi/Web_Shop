import './HomePage.css';
import Product from './Product/Product';
import { Link } from 'react-router-dom';
import { useState, useEffect, useMemo } from "react";

const productsPerPage = 6

export default function HomePage() {
    const [numPages, setNumPages] = useState(0)
    const [curPage, setCurPage] = useState(1)
    const [curCategory, setCurCategory] = useState()
    const [categoryListString, setCategoryListString] = useState('')
    const [curProductIds, setCurProductIds] = useState([])
    const categoryList=useMemo(()=>categoryListString.split(' '),[categoryListString])

    useEffect(() => {
        async function fetchData() {
            await fetch('http://127.0.0.1:8080/products/meta/category_list')
                .then(x => x.json())
                .then(x => x.map(obj => obj.categoryName))
                .then(x => x.join(' '))
                .then(setCategoryListString)
            if (!curCategory) {
                setCurCategory(categoryList[0])
            }
            else {
                await fetch('http://127.0.0.1:8080/products/by_category/' + curCategory)
                .then(x=>x.json())
                .then(setCurProductIds)
                setNumPages(Math.ceil(curProductIds.length / productsPerPage))
            }
        }
        fetchData()
    }, [curCategory, categoryList, curProductIds.length])

    console.log()
    return (
        <div>
            <div className='cart'>
                <Link to='/cart'>Cart</Link>
            </div>
            <div className='main-layout'>
                <aside className='sidebar'>
                {
                    numPages
                        ? categoryList.map(x =>
                            <p onClick={()=>setCurCategory(x)} key={x}>{x}</p>
                        )
                        : null
                }
                </aside>
                <main className='products'>
                {
                    numPages
                        ? curPage !== numPages
                            ? [...Array(productsPerPage).keys()].map(x => x + (curPage - 1) * productsPerPage).map(index =>
                                <Product key={curProductIds[index]} id={curProductIds[index]}></Product>
                            )
                            : [...Array(curProductIds.length % productsPerPage).keys()].map(x => x + (curPage - 1) * productsPerPage).map(index =>
                                <Product key={curProductIds[index]} id={curProductIds[index]}></Product>
                            )
                        : null
                }
                </main>
            </div>
            <div className='pagination'>
                <button onClick={() => {
                    if (curPage > 1) {
                        setCurPage(curPage - 1)
                    }
                }}> &lt; </button>
                {curPage} / {numPages}
                <button onClick={() => {
                    if (curPage < numPages) {
                        setCurPage(curPage + 1)
                    }
                }}> &gt; </button>
            </div>
        </div>
    )
}