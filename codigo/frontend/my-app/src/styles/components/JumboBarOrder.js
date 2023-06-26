import * as React from 'react';
import Box from '@mui/material/Box';
import FormControl from '@mui/material/FormControl';

export default function JumboBarOrder() {
  const [knives, setKnives] = React.useState('');

  const handleChange = (event) => {
    setKnives(event.target.value);
  };

  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl>
        <select
          name="tamanhoMedioPonta"
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
          <option value={4000}>4000mm</option>
          <option value={4500}>4500mm</option>
          <option value={5000}>5000mm</option>
          <option value={5500}>5500mm</option>
          <option value={6000}>6000mm</option>
        </select>
      </FormControl>
    </Box>
  );
}