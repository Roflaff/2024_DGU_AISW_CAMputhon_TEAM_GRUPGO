import React, { useEffect } from 'react';
import './lineanimation.css';

const LineAnimation: React.FC = () => {
  useEffect(() => {
    const container = document.querySelector('.animation-container') as HTMLElement;

    function createLine() {
      try { //오류가 스발 존나 많이 발생해서 그냥 try catch 사용했음
        const line = document.createElement('div');
        line.classList.add('line');

        // 랜덤한 시작 위치와 길이 설정
        const startX = Math.random() * 100;
        const startY = Math.random() * 100;
        const length = 20 + Math.random() * 50; // 최소 20%에서 최대 70% 길이

        line.style.top = `${startY}%`;
        line.style.right = `${startX}%`;
        line.style.setProperty('--length', `${length}%`);
        container.appendChild(line);

        setTimeout(() => {
          if (line.parentNode === container) {
            container.removeChild(line);
          }
        }, 1000);
        const nextTimeout = 500 + Math.random() * 600; // 최소 500ms에서 최대 1100ms 간격
        setTimeout(createLine, nextTimeout);
      } catch (error) {
        console.error('Error creating line:', error);
      }
    }

    // 초기 호출
    createLine();

    // 컴포넌트가 언마운트될 때 인터벌 제거
    return () => {
      container.innerHTML = '';
    };
  }, []);

  return <div className="animation-container"></div>;
};

export default LineAnimation;
