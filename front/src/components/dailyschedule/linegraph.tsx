import React from 'react';
import './linegraph.css';

interface Event {
  starttime: string;
  endtime: string;
  label: string;
}

interface TimeSlot {
  start: number;
  end: number;
  label: string;
}

interface LineGraphProps {
  events: Event[];
}

const parseTime = (timeStr: string): number => {
  const [time, modifier] = timeStr.split(' ');
  let [hours, minutes] = time.split(':').map(Number);

  if (modifier === 'PM' && hours !== 12) {
    hours += 12;
  }
  if (modifier === 'AM' && hours === 12) {
    hours = 0;
  }
  return hours * 60 + minutes;
};

const formatTime = (minutes: number): string => {
  const hours = Math.floor(minutes / 60);
  const mins = minutes % 60;
  const period = hours >= 12 ? 'PM' : 'AM';
  const adjustedHours = hours % 12 || 12;
  return `${adjustedHours}:${mins.toString().padStart(2, '0')} ${period}`;
};

const calculateTimeSlots = (events: Event[]): TimeSlot[] => {
  const timeSlots: TimeSlot[] = [];
  let currentTime = parseTime(events[0].starttime);

  events.forEach(event => {
    const eventStart = parseTime(event.starttime);
    const eventEnd = parseTime(event.endtime);

    if (currentTime < eventStart) {
      timeSlots.push({
        start: currentTime,
        end: eventStart,
        label: 'Free Time'
      });
    }

    timeSlots.push({
      start: eventStart,
      end: eventEnd,
      label: event.label
    });

    currentTime = eventEnd;
  });

  return timeSlots;
};

const LineGraph: React.FC<LineGraphProps> = ({ events }) => {
  const sortedEvents = [...events].sort((a, b) => parseTime(a.starttime) - parseTime(b.starttime));
  const timeSlots = calculateTimeSlots(sortedEvents);

  const minTime = timeSlots[0].start;
  const maxTime = timeSlots[timeSlots.length - 1].end;

  let lastFreeTime = false;

  return (
    <div className="line-graph">
      {timeSlots.map((slot, index) => {
        let startBorderColor = '#cccccc';
        let endBorderColor = '#cccccc';

        if (lastFreeTime) {
          startBorderColor = '#FF8C8C';
        } else if (slot.label === 'Free Time') {
          startBorderColor = '#6CB2F2';
        }

        if (slot.label === 'Free Time') {
          endBorderColor = '#FF8C8C';
        }

        lastFreeTime = slot.label === 'Free Time';

        return (
          <React.Fragment key={index}>
            <div
              className="line-graph-slot"
              style={{
                left: `${(slot.start - minTime) / (maxTime - minTime) * 100}%`,
                width: `${(slot.end - slot.start) / (maxTime - minTime) * 100}%`
              }}
            >
              <div className={`line-graph-slot-bg ${slot.label === 'Free Time' ? 'free-time' : 'lecture-time'}`} />
            </div>
            <div
              className="line-graph-circle"
              style={{
                left: `${(slot.start - minTime) / (maxTime - minTime) * 100}%`,
                borderColor: startBorderColor
              }}
            >
              <div className="inner-circle" />
              <div className="line-graph-time">{formatTime(slot.start)}</div>
            </div>
            <div
              className="line-graph-circle"
              style={{
                left: `${(slot.end - minTime) / (maxTime - minTime) * 100}%`,
                borderColor: endBorderColor
              }}
            >
              <div className="inner-circle" />
              <div className="line-graph-time">{formatTime(slot.end)}</div>
            </div>
            {slot.label !== 'Free Time' && (
              <div
                className="line-graph-label"
                style={{ left: `${((slot.start + slot.end) / 2 - minTime) / (maxTime - minTime) * 100}%` }}
              >
                {slot.label}
              </div>
            )}
          </React.Fragment>
        );
      })}
    </div>
  );
};

export default LineGraph;
