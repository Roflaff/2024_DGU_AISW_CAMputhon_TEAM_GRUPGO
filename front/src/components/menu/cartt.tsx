import React from 'react';
import { useNavigate } from 'react-router-dom';
import './cartt.css';

interface CartItem {
  imgSrc: string;
  title: string;
  price: string;
}

interface CartProps {
  cartItems: CartItem[];
}

const Cart: React.FC<CartProps> = ({ cartItems }) => {
  const navigate = useNavigate();

  const handleCheckout = () => {
    console.log("장바구니 목록:", cartItems); // 결제 페이지로 이동하면서 상태를 전달
    navigate('/paymentpage', { state: { cartItems } });
  };

  return (
    <div className="cart-container">
      <h2>장바구니</h2>
      {cartItems.length === 0 ? (
        <p>장바구니가 비어있습니다.</p>
      ) : (
        <div>
          <ul>
            {cartItems.map((item, index) => (
              <li key={index}>
                <img src={item.imgSrc} alt={item.title} />
                <div>
                  <h3>{item.title}</h3>
                  <p>{item.price}</p>
                </div>
              </li>
            ))}
          </ul>
          <button onClick={handleCheckout} className="checkout-button">결제하기</button>
        </div>
      )}
    </div>
  );
};

export default Cart;
