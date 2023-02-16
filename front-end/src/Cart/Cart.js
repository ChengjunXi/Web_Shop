import Product from '../Product/Product';
import React, { useState, useEffect } from "react";
import './Cart.css'
import { Link } from 'react-router-dom';

export default function Cart() {
    const [sub, setSub] = useState(0)

    useEffect(() => {
        async function syncFetch(stored, setSub) {
            let sum = 0
            for (let key in stored) {
                if ((+stored[key]) !== 0) {
                    // sum += await fetch('http://127.0.0.1:8080/products/' + key)
                    sum += await fetch('http://Webshop-env.eba-jmyxmqhv.us-west-2.elasticbeanstalk.com/products/' + key)
                            .then(x => x.json())
                            .then(x => (+stored[key]) * x.price)
                }
            }
            setSub(sum)
        }

        let stored = JSON.parse(localStorage.getItem("product_count"))
        if (stored) {
            syncFetch(stored, setSub)
        }
    }, [])
    
    let objs = JSON.parse(localStorage.getItem('product_count'))
    let ids=[]
    for (let key in objs) {
        if (objs[key] !== 0) {
            ids.push(key)
        }
    }

    return (
        <div>
            <h2>Cart:</h2>
            <div className='CartView'>
            {ids.length===0?<h3>Nothing in Cart</h3>
                :ids.map(id =>
                    <Product key={id} id={id} sub={sub} setSub={setSub}/>
                )
            }
            </div>
            <div className='subtotal'>
                Subtotal: ${sub}
            </div>
            <div className='checkout'>
                <Link to='../order' name='checkout'>Checkout</Link>
            </div>
        </div>
    )
}