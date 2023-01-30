import { useParams } from "react-router-dom";
import './ProductDetail.css'

export default function ProductDetail() {
  let { id } = useParams();

  return (
    <div>
      <p>aha</p> 
      <p>{id}</p>
    </div>
  );
}