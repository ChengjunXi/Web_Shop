import Product from '../Product/Product';
import React, { useState, useEffect } from "react";
import './Cart.css'

export default function Cart() {
    const [sub, setSub] = useState(0)

    useEffect(() => {
        let stored = JSON.parse(localStorage.getItem("product_count"))
        if (stored) {
            for (let key in stored) {
                fetch('http://127.0.0.1:8080/products/' + key)
                    .then(x => x.json())
                    .then(x => (+stored[key]) * x.price)
                    .then(setSub)
            }
        }
    }, [])
    

    return (
        <div>
            <h2>Cart:</h2>
            <div className='CartView'>
            {
                [1,2].map(id =>
                    <Product key={id} id={id} sub={sub} setSub={setSub}/>
                )
            }
            </div>
            <div className='subtotal'>
                Subtotal: ${sub}
            </div>
        </div>
    )
}