import React, { useState, useEffect, useMemo } from "react";
import { Link } from 'react-router-dom';
import "./Product.css"

export default function Product({ id, sub=null, setSub=f=>f}) {
    // Product data
    const [data, setData] = useState({
        id: null,
        product_name: null,
        price: null,
        descri: null,
        stock: null
    });

    // Stored amount
    let initial;
    let storedCount = JSON.parse(localStorage.getItem("product_count"))
    if (storedCount && id in storedCount) {
        initial=+storedCount[id]
    }
    else {
        initial=0
    }
    const [count, setCount] = useState(initial)
    
    const memoStoredCount=useMemo(()=>storedCount,[storedCount])

    // Image
    const [img, setImg] = useState()
    useEffect(() => {
        fetch('http://127.0.0.1:8080/product_img/' + id)
            .then(x => x.blob())
            .then(x => URL.createObjectURL(x))
            .then(setImg)
    },[id])

    // Product data
    useEffect(() => {
        fetch('http://127.0.0.1:8080/products/'+id)
            .then(x => x.json())
            .then(setData);
    }, [id]);
    
    // Stored amount
    useEffect(() => {
        if (memoStoredCount) {
            memoStoredCount[id] = count
            localStorage.setItem("product_count", JSON.stringify(memoStoredCount))
        }
        else {
            let newP = {}
            newP[id] = count
            localStorage.setItem("product_count", JSON.stringify(newP))
        }
    }, [id,count,memoStoredCount]);

    // Render
    if (data) {
            return (
                <div className="product">
                    <Link to={"/products/"+id}>
                        <div><img src={img} alt="Noimage"></img></div>
                    </Link>
                    <Link to={"/products/"+id}>
                        <div>{data.product_name}</div>
                    </Link>
                    <div>${data.price}</div>
                    <div>{data.descri}</div>
                    <div>
                        {count
                        ?<div>
                                <button onClick={() => {
                                    setCount(count - 1);
                                    setSub(sub-data.price)
                                }}> - </button>
                                {count}
                                <button onClick={() => {
                                    setCount(count + 1);
                                    setSub(sub+data.price)
                                }}> + </button>
                        </div>
                            : <button onClick={() => { setCount(count + 1); setSub(sub + data.price) }}>Add to cart</button>
                        }
                    </div>
                </div>
            );
    }
    else {
        // return null;
        return <p> No data </p>
    }
  }