import './App.css';
import { Routes, Route } from "react-router-dom";
import ProductDetail from "./Product/ProductDetail";
import HomePage from './HomePage';
import Cart from "./Cart/Cart";
import Order from "./Order/Order";
import OrderSuccess from "./Order/OrderSuccess";

function App() {
  return (
      <div>
        <h1>Web Shop</h1>
        <Routes>
          <Route
            path="/"
            element={<HomePage />}
          />
          <Route
            path="/products/:id"
            element={<ProductDetail />}
          />
          <Route
            path="/cart"
            element={<Cart />}
          />
          <Route
            path="/order/success"
            element={<OrderSuccess />}
          />
          <Route
            path="/order"
            element={<Order />}
          />
        </Routes>
        <p>This is footnote</p>
      </div>
    );
}

export default App;
