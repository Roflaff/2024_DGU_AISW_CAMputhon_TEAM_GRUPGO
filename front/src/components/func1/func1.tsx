import React from 'react';
import './func1.css';
import earthImage from '../asset/Earth.png'; // 이미지 파일 경로

const Func1Page: React.FC = () => {
  return (
    <div className="page" id="func1-page">
      <img src={earthImage} alt="Earth" className="earth-image" />
      <div className="left-side">
        블라블라블라. 그냥 아무 내용이나 적을래. 여기다가 그냥 대따 큰 이미지 입력하면 됩니당 ~~~ 
      </div>
      <div className="right-side">
        <h1>This is revolution.</h1>
        <p>sd</p>
      </div>
    </div>
  );
};

export default Func1Page;
