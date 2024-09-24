import React, { useState } from 'react';
import './editschedule.css';

const EditSchedule: React.FC = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [date, setDate] = useState('');
  const [time, setTime] = useState('');

  const handleSave = () => {
    // 저장 로직 추가
    console.log('일정 저장:', { title, description, date, time });
  };

  const handleCancel = () => {
    // 취소 로직 추가
    console.log('취소');
  };

  return (
    <div className="edit-schedule-page">
      <h1>일정 편집</h1>
      <form className="schedule-form">
        <label>
          제목:
          <input 
            type="text" 
            value={title} 
            onChange={(e) => setTitle(e.target.value)} 
            className="input-field"
          />
        </label>
        <label>
          설명:
          <textarea 
            value={description} 
            onChange={(e) => setDescription(e.target.value)} 
            className="textarea-field"
          />
        </label>
        <label>
          날짜:
          <input 
            type="date" 
            value={date} 
            onChange={(e) => setDate(e.target.value)} 
            className="input-field"
          />
        </label>
        <label>
          시간:
          <input 
            type="time" 
            value={time} 
            onChange={(e) => setTime(e.target.value)} 
            className="input-field"
          />
        </label>
        <div className="button-group">
          <button type="button" onClick={handleSave} className="save-button">저장</button>
          <button type="button" onClick={handleCancel} className="cancel-button">취소</button>
        </div>
      </form>
    </div>
  );
};

export default EditSchedule;
