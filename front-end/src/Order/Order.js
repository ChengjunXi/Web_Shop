import './Order.css'
import React from 'react'
import { useState } from "react";
import { Elements } from '@stripe/react-stripe-js';
import { loadStripe } from '@stripe/stripe-js';
import Payment from './Payment'

const stripePromise = loadStripe('pk_test_51MUg0aLEfiemJbGLYXl7m1fFVCuyag8CEo4LM2cLD6sAVO1ILNFmwvuBXk54EJC0kQLhKiuPnUlJk6Gk4Ge7Jzpt00wp81wFis');

export default function Order() {
    const [firstName, setFirstName] = useState()
    const [lastName, setLastName] = useState()
    const [email, setEmail] = useState()
    const [address, setAddress] = useState()
    const [city, setCity] = useState()
    const [postalCode, setPostalCode] = useState()
    const [clientSecret, setClientSecret] = useState()

    async function onSubmit(e) {
        let stored=JSON.parse(localStorage.getItem('product_count'))
        if (stored) {
            let order_items=[]
            for (let key in stored) {
                order_items.push({product_id:key,amount:stored[key]})
            }

            // let response = await fetch('http://127.0.0.1:8080/order', {
            let response = await fetch('http://Webshop-env.eba-jmyxmqhv.us-west-2.elasticbeanstalk.com/order', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    first_name: firstName,
                    last_name: lastName,
                    email: email,
                    uaddress: address,
                    city: city,
                    postal_code: postalCode,
                    order_items: order_items
                })
            })
            localStorage.setItem("product_count", null)
            response = await response.json()
            setClientSecret(response.client_secret)
        }
    }

    if(!clientSecret){
        return (
            <div className='OrderView' id='OrderView'>
                <form>
                    <div>
                        <div>
                            <label>First name</label>
                            <input type='text' name='first_name' id='first_name' onChange={e => setFirstName(e.target.value)}></input>
                        </div>
                        <div>
                            <label>Last name</label>
                            <input type='text' name='last_name' id='last_name' onChange={e => setLastName(e.target.value)}></input>
                        </div>
                    </div>
                    <div>
                        <div>
                            <label>Email</label>
                            <input type='text' name='email' id='email' onChange={e => setEmail(e.target.value)}></input>
                        </div>
                        <div></div>
                    </div>
                    <div>
                        <div>
                            <label>Address</label>
                            <input type='text' name='address' id='address' onChange={e => setAddress(e.target.value)}></input>
                        </div>
                        <div></div>
                    </div>
                    <div>
                        <div>
                            <label>City</label>
                            <input type='text' name='city' id='city' onChange={e => setCity(e.target.value)}></input>
                        </div>
                        <div>
                            <label>Postal code</label>
                            <input type='text' name='postal_code' id='postal_code' onChange={e => setPostalCode(e.target.value)}></input>
                        </div>
                    </div>
                    <div className='sub'>
                        <button type='button' name='order' onClick={onSubmit}>Place order</button>
                    </div>
                </form>
            </div>
        )
    }
    else {
        console.log(clientSecret)
        return (
            <form>
                <Elements stripe={stripePromise} options={{ clientSecret: clientSecret }}>
                    <Payment></Payment>
                </Elements>
            </form>
        )
    }

}
