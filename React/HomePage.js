import './HomePage.css';
import Product from './Product/Product';
import { Link } from 'react-router-dom';

export default function HomePage() {
    console.log("Rendering HomePage")
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
                    [1,2].map(id =>
                        <Product key={id} id={id} />
                    )
                }
                </main>
            </div>
        </div>
    )
}