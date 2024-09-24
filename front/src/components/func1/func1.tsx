import React from 'react';
import { useNavigate } from 'react-router-dom';
import './func1.css';
import earthImage from '../asset/Earth.png'; // 이미지 파일 경로
import Ac from '../asset/아코우주짤.png';
import Step from '../asset/3step.png';

const Func1Page: React.FC = () => {
  const navigate = useNavigate();

  const handleButtonClick = () => {
    navigate('/menu');
  };
  

  return (
    <div className="page" id="func1-page">
      <div className="divcontainer">
        <h1>Order without waiting</h1>
        <p>With GRUPGO Ordering, choose your menu and make a payment without waiting! Stop wasting time in line!</p>
      </div>

      <div className="image">
        <img src={Ac} alt="Ac" />
      </div>

      <div className="image-stopped">
        <img src={Step} alt="Step" />
      </div>

      <button className="button-button" onClick={handleButtonClick}>
        Order Now
      </button>

      <img src={earthImage} alt="Earth" className="earth-image" />
    </div>
  );
};

export default Func1Page;
