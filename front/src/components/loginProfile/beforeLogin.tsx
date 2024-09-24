import React from 'react';
import { useNavigate } from 'react-router-dom';
import Profile from '../asset/BasicProfile.png';
import './loginProfile.css';
import people from '../asset/people.png';

const BeforeLogin = () => {
  const navigate = useNavigate();

  const handleLoginClick = () => {
    navigate('/login'); // 로그인 페이지로 이동
  }

  const handleSignupClick = () => {
    navigate('/signup'); // 회원가입 페이지로 이동
  }

  return (
    <div className="usersetting">
      <div className="beforelogin">
        <img src={Profile} alt="profile" className="beforeprofile" />
      </div>
      <h2 className='gradientbackground' style={{ textAlign: "center" }}>Guest</h2>
      <div>
        <button className="logincontrol" onClick={handleLoginClick}>
          <div className="loginimage">🔑</div>
          <div className="useraccount">Login</div>
        </button>
        <br /> <br />
        <button className="logincontrol" onClick={handleSignupClick}>
          <img src={people} className="loginimage" alt="signup" />
          <div className="useraccount">Sign Up</div>
        </button>
        <br /> <br />
      </div>
    </div>
  );
}

export default BeforeLogin;
