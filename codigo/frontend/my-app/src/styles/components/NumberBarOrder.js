import React from 'react';
import Box from '@mui/material/Box';
import FormControl from '@mui/material/FormControl';
import { useState } from 'react';

export default function NumberBarOrder(props) {
  const [knives, setKnives] = useState('');

  const handleChange = (event) => {
    const { value } = event.target;
    setKnives(value);
  };

  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl
      >
        <select
          name="quantidadeBobinas"
          id="demo-simple-select"
          value={knives}
          onChange={handleChange}
          style={{
            width: "10rem",
            height: "2rem",
            borderRadius: "20px",
            backgroundColor: "#E8E8E8",
            textAlign: "center",
          }}
        >
          <option value={1}>1</option>
          <option value={2}>2</option>
          <option value={3}>3</option>
          <option value={4}>4</option>
          <option value={5}>5</option>
          <option value={6}>6</option>
          <option value={7}>7</option>
          <option value={8}>8</option>
        </select>
      </FormControl>
    </Box>
  );
}