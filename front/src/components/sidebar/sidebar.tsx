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
          <li>Account</li>
          <ul>
              <li>Edit Infomation</li>
              <li>Delete Account</li>
              <li>Setting</li>
          </ul>

          <li>My Schedule</li>
          <ul>
            <li>Weekly Schedule</li>
            <li>Edit Schedule</li>
          </ul>
        </ul>
    </div>
  );
};

export default SideBar;
