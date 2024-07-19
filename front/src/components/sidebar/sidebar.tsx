import React from 'react';
import './sidebar.css';

interface SideBarProps {
  open: boolean;
  onClose: () => void;
}

const SideBar: React.FC<SideBarProps> = ({ open, onClose }) => {
  return (
    <div className={`sidebar ${open ? 'open' : ''}`}>
        <div className = "side-top">
            <button className="close-button" onClick={onClose}>&#9776;</button>
        </div>
        <ul>
            <li>Home</li>
            <li>About</li>
            <li>Contact</li>
        </ul>
    </div>
  );
};

export default SideBar;
