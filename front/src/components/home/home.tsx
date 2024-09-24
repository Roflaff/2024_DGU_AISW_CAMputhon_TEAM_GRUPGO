import React, { useState } from 'react';
import Toggle from '../toggle-button/toggle';
import LineAnimation from '../animations/lineanimation';
import './home.css';
import Schedule from '../asset/schedule.png';


const Home: React.FC = () => {
    const [defaultText, setDefaultText] = useState('text 1');
    const [hoverText, setHoverText] = useState('text 2');
  
    return (
      <div>
        <LineAnimation />
        <div className="main-home-page">
        <div className="title">
          <h1>Welcome to GRUPGO!</h1>
          <p>
          Discover the extraordinary free time management system offered by GRUPGO. With our innovative features and user-friendly interface, managing your schedule has never been easier. 
          </p>
          <div className="toggle-container">
            <Toggle defaultText={'Discord channel'} hoverText={'Join In Discord !'} href='https://discord.gg/hdc6fndc'/>
          </div>
          <div className='imagee'>
            <img src = {Schedule}/>
          </div>
        </div>
      </div>

      </div>
    );
  };
  
  export default Home;
  