import React, { useState } from 'react';
import axios from 'axios';
import LineGraph from './linegraph';
import './dailyschedule.css';

const events = [
  { starttime: '10:30 AM', endtime: '12:00 PM', label: '데이터베이스' },
  { starttime: '1:00 PM', endtime: '2:00 PM', label: '프로그래밍 언어 개념' },
  { starttime: '2:30 PM', endtime: '5:00 PM', label: '형식 언어' }
];

const calculateFreeTimeNotes = (events: { starttime: string; endtime: string; label: string }[]) => {
  return events.map((event, index, array) => {
    if (index === 0) return null;
    return {
      starttime: array[index - 1].endtime,
      endtime: event.starttime,
      label: 'Free Time'
    };
  }).filter(note => note !== null) as { starttime: string; endtime: string; label: string }[];
};

const freeTimeNotes = calculateFreeTimeNotes(events);

const DailySchedule: React.FC = () => {
  const [notes, setNotes] = useState(freeTimeNotes.map(() => ({ activity: '' })));

  const handleActivityChange = (index: number, value: string) => {
    const newNotes = [...notes];
    newNotes[index].activity = value;
    setNotes(newNotes);
  };

  const handlePostActivity = async (index: number) => {
    const note = freeTimeNotes[index];
    const activity = notes[index].activity;

    try {
      const response = await axios.post("http://localhost:8080/Behavior/add", {
        starttime: note.starttime,
        endtime: note.endtime,
        activity: activity
      }, {
        headers: {
          'Content-Type': 'application/json'
        }
      });
      console.log('Activity Posted:', response.data);
      alert(`Activity for #NOTE ${index + 1}: ${activity} has been posted successfully`);
    } catch (error) {
      console.error('Error posting activity:', error);
      alert('Error posting activity');
    }
  };

  return (
      <div className="daily-schedule">
        <h1>Today's Schedule</h1>
        <div className="line-graph-container">
          <LineGraph events={events} />
        </div>
        <div className="notes-container">
          {freeTimeNotes.map((note, index) => (
              <div key={index} className="note">
                <h2>#NOTE {index + 1}</h2>
                <p>Time: {note.starttime} - {note.endtime}</p>
                <select
                    value={notes[index].activity}
                    onChange={(e) => handleActivityChange(index, e.target.value)}
                >
                  <option value="">Select an activity</option>
                  <option value="rest">Rest</option>
                  <option value="meal">Meal</option>
                  <option value="study">Study</option>
                  <option value="exercise">Exercise</option>
                  <option value="hobby">Hobby</option>
                </select>
                <button onClick={() => handlePostActivity(index)}>Enter</button>
              </div>
          ))}
        </div>
      </div>
  );
};

export default DailySchedule;
