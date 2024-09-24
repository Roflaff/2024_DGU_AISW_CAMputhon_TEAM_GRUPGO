import React, { useState } from 'react';
import './toggle.css';

interface ToggleProps {
  defaultText: string;
  hoverText: string;
  href: string;
}

const Toggle: React.FC<ToggleProps> = ({ defaultText, hoverText, href }) => {
  const [isHovered, setIsHovered] = useState(false);

  return (
    <div className="tgl">
      <a
        href={href}
        className={`toggle_btn ${isHovered ? 'hovered' : ''}`}
        onMouseEnter={() => setIsHovered(true)}
        onMouseLeave={() => setIsHovered(false)}
      >
        {isHovered ? hoverText : defaultText}
      </a>
    </div>
  );
};

export default Toggle;
