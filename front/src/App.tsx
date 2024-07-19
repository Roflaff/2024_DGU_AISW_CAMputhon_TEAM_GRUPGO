import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import TopBar from './components/topbar/topbar';
import SideBar from './components/sidebar/sidebar';
import MainPage from './components/mainpage/mainpage';
import Profile from './components/user/Profile';
import Modal from './components/modal/modal';
import './App.css';

const App: React.FC = () => {
  const [isSideBarOpen, setSideBarOpen] = useState(false);
  const [isModalOpen, setModalOpen] = useState(false);

  const handleMenuClick = () => {
    setSideBarOpen(!isSideBarOpen);
  };

  const handleSideBarClose = () => {
    setSideBarOpen(false);
  };

  const handleProfileClick = () => {
    setModalOpen(true);
  };

  const handleModalClose = () => {
    setModalOpen(false);
  };

  return (
    <Router>
      <div className={`app ${isSideBarOpen ? 'shift' : ''}`}>
        <TopBar onMenuClick={handleMenuClick} isSideBarOpen={isSideBarOpen} onProfileClick={handleProfileClick} />
        <SideBar open={isSideBarOpen} onClose={handleSideBarClose} />
        <div className="content">
          <Routes>
            <Route path="/" element={<MainPage />} />
          </Routes>
        </div>
        <Modal isOpen={isModalOpen} onClose={handleModalClose}>
          <Profile />
        </Modal>
      </div>
    </Router>
  );
};

export default App;
