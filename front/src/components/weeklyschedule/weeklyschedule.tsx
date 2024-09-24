import React, { useEffect, useState } from 'react';
import './weeklyschedule.css';

// JSON 데이터를 불러오기 위해 import합니다.
import scheduleData from '../../subject.json';

interface ScheduleItem {
    subjectNum: string;
    subjectName: string;
    professor: string;
    dayOfWeek: string;
    startTime: string;
    endTime: string;
}

const WeeklySchedule: React.FC = () => {
    const [schedule, setSchedule] = useState<ScheduleItem[]>([]);

    useEffect(() => {
        // JSON 데이터를 상태로 설정합니다.
        setSchedule(scheduleData);
    }, []);

    const daysOfWeek = ['월', '화', '수', '목', '금'];

    return (
        <div className="weekly-schedule-page">
            <h1>주간 일정</h1>
            <div className="week-container">
                {daysOfWeek.map((day) => (
                    <div key={day} className="day-column">
                        <h2>{day}</h2>
                        <div className="schedule-items">
                            {schedule
                                .filter((item) => item.dayOfWeek === day)
                                .map((item, index) => (
                                    <div key={index} className="schedule-item">
                                        <h3>{item.subjectName}</h3>
                                        <p>{item.professor}</p>
                                        <p>
                                            {item.startTime} - {item.endTime}
                                        </p>
                                    </div>
                                ))}
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default WeeklySchedule;
