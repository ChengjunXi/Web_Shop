import './Payment.css'
import React from 'react'
import { useState } from "react";

import { useStripe, useElements, PaymentElement } from '@stripe/react-stripe-js';

export default function Payment() {
    const stripe = useStripe()
    const elements = useElements()
    const [errMsg,setErrMsg]=useState()

    async function onSubmit(e) {
        if (!stripe || !elements) {
            return
        }
        console.log('Running')
        
        const { error } = await stripe.confirmPayment({
            elements,
            confirmParams: {
                return_url:'http://localhost:3000/order/success'
            }
        })

        if (error) {
            console.log('Error')
            setErrMsg(errMsg)
        }
        else {
            console.log('Success')
        }
    }

    return (
        <div className='PaymentView'>
            <div className='stripe'>
                <PaymentElement />
            </div><br></br>
            <div className='sub'>
                <button type='button' name='pay' onClick={onSubmit}>Pay</button>
            </div>
            {errMsg && <div>{errMsg}</div>}
        </div>
    )
}
