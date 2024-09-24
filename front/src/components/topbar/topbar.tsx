import React from 'react';
import { useNavigate } from 'react-router-dom';
import './topbar.css';
import myImage from '../asset/profile.png';
import { scroller } from 'react-scroll';

interface TopBarProps {
  onMenuClick: () => void;
  isSideBarOpen: boolean;
  onProfileClick: () => void;
}

const TopBar: React.FC<TopBarProps> = ({ onMenuClick, isSideBarOpen, onProfileClick }) => {
  const navigate = useNavigate();

  const handleButtonClick = (section: string) => {
    navigate('/'); // 메인 페이지로 이동
    setTimeout(() => { // 이동 후 해당 섹션으로 스크롤
      scroller.scrollTo(section, {
        duration: 800,
        delay: 0,
        smooth: 'easeInOutQuart'
      });
    }, 100); // navigate 완료 후 약간의 지연을 줌
  };

  return (
    <div className="topbar">
      {!isSideBarOpen && (
        <button className="menu-button" onClick={onMenuClick}>
          &#9776; {/* 햄버거 메뉴 아이콘 */}
        </button>
      )}
      
      <div className="button-container">
        <button className="topbar-button" onClick={() => handleButtonClick('home')}>Home</button>
        <div className="nbsp">|</div>
        <button className="topbar-button" onClick={() => handleButtonClick('func1')}>Order</button>
        <div className="nbsp">|</div>
        <button className="topbar-button" onClick={() => handleButtonClick('func2')}>Schedule</button>
        <div className="nbsp">|</div>
        <button className="topbar-button" onClick={() => handleButtonClick('team')}>Team</button>
      </div>
      
      <button className="profile-button" onClick={onProfileClick}>
        <img src={myImage} alt="Profile" className="profile-image" />
      </button>
    </div>
  );
};

export default TopBar;
