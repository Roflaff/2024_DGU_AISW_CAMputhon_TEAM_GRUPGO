import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import './payment.css';

interface CartItem {
  imgSrc: string;
  title: string;
  price: string;
}

const PaymentPage: React.FC = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { cartItems } = location.state as { cartItems: CartItem[] };

  const getTotalPrice = (items: CartItem[]) => {
    return items.reduce((total, item) => {
      const priceNumber = parseInt(item.price.replace(/[^0-9]/g, ''), 10);
      return total + priceNumber;
    }, 0);
  };

  const totalPrice = getTotalPrice(cartItems);

  // 사용자 정보 상수 선언
  const userInfo = {
    name: "류경록",
    phone: "010-1234-5678",
    email: "fbrudfhr@gmail.com"
  };

  const handlePayment = () => {
    navigate('/paymentcomplete');
  };

  return (
    <div className="payment-page">
      <div className="content">
        <div className="left-column">
          <div className="order-info">
            <h2>주문자 정보</h2>
            <p className='p'>{userInfo.name}</p>
            <p className='p'>{userInfo.phone}</p>
            <p className='p'>{userInfo.email}</p>
            <button>수정</button>
          </div>
          <div className="payment-method">
            <h2>결제 방법</h2>
            <label>
              <input type="radio" name="payment" value="kakao" /> <p className='p'>카카오페이</p>
            </label>
            <label>
              <input type="radio" name="payment" value="virtual" /> <p className='p'>가상계좌</p>
            </label>
            <label>
              <input type="radio" name="payment" value="bank" /> <p className='p'>무통장 입금</p>
            </label>
            <label>
              <input type="radio" name="payment" value="mobile" /> <p className='p'>핸드폰 결제</p>
            </label>
            <label>
              <input type="radio" name="payment" value="credit" /> <p className='p'>신용카드</p>
            </label>
            <div className='payment-division'>
              은행:
              <div className='payment-div-div'>
                <select className='payment-select'>
                  <option>우리</option>
                  <option>농협</option>
                  <option>신한</option>
                </select>
              </div>
              <input type="text" className='payment-text-input'/>  <br/>
              <button>결제</button>
            </div>
          </div>
        </div>
        <div className="right-column">
          <div className="total-summary">
            <h2>총 내역</h2>
            {cartItems.map((item, index) => (
              <div className="summary-item" key={index}>
                <img src={item.imgSrc} alt={item.title} />
                <p>{item.title}<br />{item.price}</p>
              </div>
            ))}
          </div>
        </div>
      </div>
      <div className="final-payment">
        <div className="final-payment-left">
          <h2>최종 결제</h2>
          {cartItems.map((item, index) => (
            <p key={index}>상품 가격: {item.price}</p>
          ))}
          <p>총 결제 금액: <span>{totalPrice}원</span></p>
        </div>
        <div className="final-payment-right">
          <label>
            <input type="checkbox" /> 전체 동의
          </label>
          <label>
            <input type="checkbox" /> 구매 조건 확인 및 결제 진행에 동의
          </label>
          <button onClick={handlePayment}>결제하기</button>
        </div>
      </div>
    </div>
  );
};

export default PaymentPage;
