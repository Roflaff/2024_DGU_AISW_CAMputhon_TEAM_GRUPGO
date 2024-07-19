import React, { useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { scroller } from 'react-scroll';
import Home from '../home/home';
import Func1 from '../func1/func1';
import Func2 from '../func2/func2';
import TeamPage from '../teampage/teampage';
import AboutUs from '../aboutus/aboutus';
import './mainpage.css';

const MainPage: React.FC = () => {
  const location = useLocation();

  useEffect(() => {
    if (location.hash) {
      const section = location.hash.replace('#', '');
      scroller.scrollTo(section, {
        duration: 800,
        delay: 0,
        smooth: 'easeInOutQuart'
      });
    }
  }, [location]);

  return (
    <div className="main-page">
      <div id="home">
        <Home />
      </div>
      <div id="func1">
        <Func1 />
      </div>
      <div id="func2">
        <Func2 />
      </div>
      <div id="team">
        <TeamPage />
      </div>
      <div id="about">
        <AboutUs />
      </div>
    </div>
  );
};

export default MainPage;
