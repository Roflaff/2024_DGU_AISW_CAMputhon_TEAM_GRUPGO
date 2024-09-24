import React from 'react';
import './teampage.css';
import X from '../asset/x.png';
import Dev from '../asset/dev.png';
import Git from '../asset/git.png';
import Discord from '../asset/discord.png';
import Insta from '../asset/insta.png';

interface TeamMemberProps {
  name: string;
  role: string;
  image: string;
}

const TeamMember: React.FC<TeamMemberProps> = ({ name, role, image }) => {
  return (
    <div className="team-member">
      <img src={image} alt={`${name}`} className="team-member-image"/>
      <h3>{name}</h3>
      <p>{role}</p>
    </div>
  );
};

const TeamPage: React.FC = () => {
  const teamMembers = [
    { name: '김선표', role: 'back-end', image: 'image1.jpg' },
    { name: '염경민', role: 'back-end', image: 'image2.jpg' },
    { name: '지종우', role: 'back-end', image: 'image3.jpg' },
    { name: '류경록', role: 'front-end', image: 'image4.jpg' },
    { name: '야기시타쇼키', role: 'front-end', image: 'image3.jpg' },
  ];

  return (
      <div className="page" id="team-page">
        <h1>Team-member</h1>
          <div className="team-grid">
            {teamMembers.map((member, index) => (
              <TeamMember
                key={index}
                name={member.name}
                image={member.image}
                role={member.role}
              />
            ))}
          </div>
          <div className="sns-container">
            Follow us on social media for updates
            <div className="sns-icons">
              <img src={Discord} alt="Discord" />
              <img src={Dev} alt="Dev" />
              <img src={X} alt="X" />
              <img src={Insta} alt="Instagram" />
              <img src={Git} alt="GitHub" />
            </div>
        </div>
      </div>
  );
};

export default TeamPage;
