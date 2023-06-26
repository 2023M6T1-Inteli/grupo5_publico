import * as React from 'react';
import Box from '@mui/material/Box';
import FormControl from '@mui/material/FormControl';

export default function JumboBar() {
  const [knives, setKnives] = React.useState('');

  const handleChange = (event) => {
    setKnives(event.target.value);
  };

  return (
    <Box sx={{ minWidth: 120 }}>
      <FormControl>
        <input
          type="number"
          name="larguraJumboMax"
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
        />
      </FormControl>
    </Box>
  );
}