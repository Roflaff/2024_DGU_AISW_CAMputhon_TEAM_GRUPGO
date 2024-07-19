import React from 'react';
import './func2.css';

const Func2Page: React.FC = () => {
  return (
    <div className="page" id="func2-page">
      <h1>Function 2 Page</h1>
      <p>오로라 이뿌덩.</p>
      <div className='box'>
        <div className='wave -one'></div>
        <div className='wave -two'></div>
        <div className='wave -three'></div>
      </div>
    </div>
  );
};

export default Func2Page;
