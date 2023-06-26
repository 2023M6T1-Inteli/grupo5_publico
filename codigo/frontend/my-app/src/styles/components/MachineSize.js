import * as React from "react";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";

export default function MachineSize() {
  return (
    <FormControl>
      <RadioGroup
        aria-labelledby="demo-radio-buttons-group-label"
        name="tamanho"
        row
      >
        <FormControlLabel
          value="40"
          control={
            <Radio
              size="small"
              sx={{ "& .MuiSvgIcon-root": { fontSize: 15 } }}
            />
          }
          label="Pequena"
        />
        <FormControlLabel
          value="50"
          control={
            <Radio
              size="small"
              sx={{ "& .MuiSvgIcon-root": { fontSize: 15 } }}
            />
          }
          label="Média"
        />
        <FormControlLabel
          value="60"
          control={
            <Radio
              size="small"
              sx={{ "& .MuiSvgIcon-root": { fontSize: 15 } }}
            />
          }
          label="Grande"
        />
      </RadioGroup>
    </FormControl>
  );
}
