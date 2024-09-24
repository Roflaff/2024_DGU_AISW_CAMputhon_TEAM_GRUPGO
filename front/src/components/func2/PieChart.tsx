import React from 'react';
import { PieChart, Pie, Cell, Tooltip, Legend } from 'recharts';

const behavior = [
  { rest: 13.5, meal: 1.5, study: 2.5, exercise: 1.5, hobby: 4.0 },
];

const data = [
  { name: 'Rest', value: behavior[0].rest },
  { name: 'Meal', value: behavior[0].meal },
  { name: 'Study', value: behavior[0].study },
  { name: 'Exercise', value: behavior[0].exercise },
  { name: 'Hobby', value: behavior[0].hobby },
];

const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042', '#FF6384'];

const CustomPieChart: React.FC = () => {
  return (
    <PieChart width={400} height={400}>
      <Pie
        data={data}
        cx={200}
        cy={200}
        innerRadius={60}
        outerRadius={120}
        fill="#8884d8"
        paddingAngle={5}
        dataKey="value"
      >
        {data.map((entry, index) => (
          <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
        ))}
      </Pie>
      <Tooltip />
      <Legend />
    </PieChart>
  );
};

export default CustomPieChart;
