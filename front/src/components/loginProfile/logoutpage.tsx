import React from 'react';
import { useNavigate } from 'react-router-dom';
import './logoutpage.css';

const LogoutPage: React.FC = () => {
  const navigate = useNavigate();

  const handleLoginClick = () => {
    navigate('/'); // 로그인 페이지로 이동
  };

  return (
    <div className="logout-container">
      <h2>You have been logged out</h2>
      <button onClick={handleLoginClick} className="logout-button">Main-Page</button>
    </div>
  );
};

export default LogoutPage;
