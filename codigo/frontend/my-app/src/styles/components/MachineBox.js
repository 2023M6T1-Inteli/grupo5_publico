import * as React from "react";
import { styled, alpha } from "@mui/material/styles";
import MenuIcon from "@mui/icons-material/Menu";
import { IconButton } from "@mui/material";
import ContentCutIcon from "@mui/icons-material/ContentCut";
import StraightenIcon from "@mui/icons-material/Straighten";
import ModalMachine from "./ModalMachine";
import DeleteIcon from "@mui/icons-material/Delete"; // Ícone da lixeira

function MachineBox(props) {
  const [open, setOpen] = React.useState(false);
  const [selected, setSelected] = React.useState();

  const handleClose = () => setOpen(false);
  const { vetor, machines, setMachines } = props;
  const handleOpen = () => {
    setSelected(vetor);
    console.log(vetor);
    setOpen(true);
  };

  const Box = styled("div")(({ theme }) => ({
    backgroundColor: "rgba(255, 255, 255, 0.78)",
    width: "150px",
    height: "150px",
    margin: "2vw",
    position: "relative",
    borderRadius: "10px",
  }));

  return (
    <Box>
      {/* Menu de edição */}
      <div>
        <IconButton
          style={{ display: "flex", marginLeft: "auto" }}
          onClick={handleOpen}
        >
          <MenuIcon style={{ color: "green" }} />
        </IconButton>
        <ModalMachine
          state={open}
          close={handleClose}
          data={vetor}
          machines={machines}
          setMachines={setMachines}
          vetor={selected}
        />
      </div>

      <div
        style={{
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <ContentCutIcon style={{ fontSize: 55 }} />
        <div
          style={{ display: "block", textAlign: "center", fontWeight: "600" }}
        >
          <StraightenIcon style={{ marginRight: "1vh", fontSize: 22 }} />
          {vetor.tamanho}
        </div>

        <span
          style={{ display: "block", textAlign: "center", fontWeight: "350" }}
        >
          {vetor.nome}
        </span>
      </div>
    </Box>
  );
}

export default MachineBox;
