import React from 'react';
import './topbar.css';
import myImage from '../asset/profile.png';
import { scroller } from 'react-scroll';

interface TopBarProps {
  onMenuClick: () => void;
  isSideBarOpen: boolean;
  onProfileClick: () => void;
}

const TopBar: React.FC<TopBarProps> = ({ onMenuClick, isSideBarOpen, onProfileClick }) => {
  const scrollToSection = (section: string) => {
    scroller.scrollTo(section, {
      duration: 800,
      delay: 0,
      smooth: 'easeInOutQuart'
    });
  };

  return (
    <div className="topbar">
      {!isSideBarOpen && (
        <button className="menu-button" onClick={onMenuClick}>
          &#9776; {/* 햄버거 메뉴 아이콘 */}
        </button>
      )}
      
      <div className="button-container">
        <button className="topbar-button" onClick={() => scrollToSection('home')}>Home</button>
        <div className="nbsp">|</div>
        <button className="topbar-button" onClick={() => scrollToSection('func1')}>Func 1</button>
        <div className="nbsp">|</div>
        <button className="topbar-button" onClick={() => scrollToSection('func2')}>Func 2</button>
        <div className="nbsp">|</div>
        <button className="topbar-button" onClick={() => scrollToSection('team')}>Team</button>
        <div className="nbsp">|</div>
        <button className="topbar-button" onClick={() => scrollToSection('about')}>About us</button>
      </div>
      
      <button className="profile-button" onClick={onProfileClick}>
        <img src={myImage} alt="Profile" className="profile-image" />
      </button>
    </div>
  );
};

export default TopBar;
