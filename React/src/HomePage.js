import './HomePage.css';
import Product from './Product/Product';
import { Link } from 'react-router-dom';
import { useState, useEffect } from "react";

const productsPerPage = 6

export default function HomePage() {
    const [numProducts,setNumProducts]=useState(0)
    const [numPages, setNumPages] = useState(0)
    const [curPage, setCurPage] = useState(1)


    useEffect(() => {
        fetch('http://127.0.0.1:8080/products/meta/num_products')
            .then(x=>x.json())
            .then(setNumProducts)
        setNumPages(Math.ceil(numProducts / productsPerPage))
    }, [numProducts])
    

    return (
        <div>
            <div className='cart'>
                <Link to='/cart'>Cart</Link>
            </div>
            <div className='main-layout'>
                <aside className='sidebar'>
                    Sidebar.
                </aside>
                <main className='products'>
                {
                    // [...Array(productsPerPage+1).keys()].slice(1).map(x=>x+(curPage-1)*productsPerPage).map(id =>
                    [1,2].map(x=>x+(curPage-1)*productsPerPage).map(id =>
                        <Product key={id} id={id} />
                    )
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