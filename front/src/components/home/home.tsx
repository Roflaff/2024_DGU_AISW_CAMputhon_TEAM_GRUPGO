import React, { useState } from 'react';
import Toggle from '../toggle-button/toggle';
import LineAnimation from '../animations/lineanimation';
import './home.css';

const Home: React.FC = () => {
    const [defaultText, setDefaultText] = useState('text 1');
    const [hoverText, setHoverText] = useState('text 2');
  
    return (
      <div>
        <LineAnimation />
        <div className="main-page">
        <div className="title">
          <h1>GRUPGO CAMPUTHON</h1>
          <p>
            Join in our Project. "Success is not final, failure is not fatal: <br/>
            It is the courage to continue that counts." - Winston Churchill :)
          </p>
          <div className="toggle-container">
            <Toggle defaultText={'왼쪽버튼 1'} hoverText={'왼쪽버튼 2'} />
            <Toggle defaultText={'오른쪽 버튼1'} hoverText={'경록형'} />
          </div>
          <div className='image'>
            
          </div>
        </div>
      </div>

      </div>
    );
  };
  
  export default Home;
  