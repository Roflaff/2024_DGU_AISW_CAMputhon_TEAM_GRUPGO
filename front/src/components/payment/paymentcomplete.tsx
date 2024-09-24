import React from 'react';
import { useNavigate } from 'react-router-dom';
import './paymentcomplete.css';
import QRPage from '../qr/qr';

const PaymentComplete: React.FC = () => {
  const navigate = useNavigate();

  const handleBackToHome = () => {
    navigate('/');
  };

  return (
    <div className="payment-complete-page">
      <h1>결제 완료</h1>
      <p>결제가 성공적으로 완료되었습니다. 감사합니다!</p>
      <div className="qr-section">
        <div className="qr-placeholder">
          <QRPage/>
        </div>
      </div>
      <button onClick={handleBackToHome} className="back-to-home-button">홈으로 돌아가기</button>
    </div>
  );
};

export default PaymentComplete;
