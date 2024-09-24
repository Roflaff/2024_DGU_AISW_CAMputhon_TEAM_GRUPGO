import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './login.css';
import { useAuth } from '../../auth/authcontext';
import axios from 'axios';

const Login: React.FC = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const { login } = useAuth();
  const navigate = useNavigate();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    // console.log('Email:', email, 'Password:', password);
    //데베랑 비교해서 같으면 통과 아니면 뭐... 아니라고 메시지 띄우기?
    try {
      const response = await axios.post(`http://localhost:8080/User/login`, {
        username,
        password
      }, {
        headers: {
          'Content-Type': 'application/json'
        }
      });

      // 로그인 성공 시
      if(response.data){
        login();
        navigate('/'); // 로그인 후 초기 페이지로
      }
      else{
        alert("아이디 또는 비밀번호가 잘못되었습니다.");
      }
    } catch(error){
      console.error("로그인 오류:", error);
      alert("로그인에 실패했습니다. 다시 시도해 주세요.");
    }
  };

  return (
    <div className="login-container">
      <form onSubmit={handleSubmit} method="POST" className="login-form">
        <h2>Login</h2>

        {/* 아이디 */}
        <div className="form-group">
          <label htmlFor="userID">id</label>
          <input
              type="text"
              id="username"
              value={username}
              placeholder="아이디를 입력하세요."
              onChange={(e) => setUsername(e.target.value)}
              required
          />
        </div>

        {/* 비밀번호 */}
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
              type="password"
              id="password"
              value={password}
              placeholder="비밀번호를 입력하세요."
              onChange={(e) => setPassword(e.target.value)}
              required
          />
        </div>

        <button type="submit" className="login-button">Login</button>
      </form>
    </div>
  );
};

export default Login;