import React, { useState } from 'react';
import './toggle.css';

interface ToggleProps {
  defaultText: string;
  hoverText: string;
}

const Toggle: React.FC<ToggleProps> = ({ defaultText, hoverText }) => {
  const [isHovered, setIsHovered] = useState(false);

  return (
    <div className="tgl">
      <button
        className={`toggle_btn ${isHovered ? 'hovered' : ''}`}
        onMouseEnter={() => setIsHovered(true)}
        onMouseLeave={() => setIsHovered(false)}
      >
        {isHovered ? hoverText : defaultText}
      </button>
    </div>
  );
};

export default Toggle;
