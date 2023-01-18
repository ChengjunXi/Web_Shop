import './App.css';
import { Routes, Route } from "react-router-dom";
import ProductDetail from "./Product/ProductDetail";
import HomePage from './HomePage';
import Cart from "./Cart/Cart";

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
      </Routes>


      <p>This is footnote</p>
    </div>
  );
}

export default App;
