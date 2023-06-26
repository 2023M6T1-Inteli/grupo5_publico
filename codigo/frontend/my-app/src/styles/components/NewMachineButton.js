import { styled, alpha } from "@mui/material/styles";
import AddIcon from "@mui/icons-material/Add";
import * as React from "react";
import { IconButton } from "@mui/material";
import ModalMachine from "./ModalMachine";

function NewMachineButton({ pagina }) {

  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const AddMachineButton = styled("div")(({ theme }) => ({
    position: "absolute",
    top: "3.2em",
    right: "10vw",
    borderRadius: "50%",
    border: "solid 2px black",
    backgroundColor: "#00673B",
  }));
 
  return (
    <>
      <AddMachineButton>
        <IconButton onClick={handleOpen}>
          <AddIcon style={{ color: "white", fontSize: 64 }} />
        </IconButton>
        <ModalMachine state={open} close={handleClose} pagina={pagina}/>
      </AddMachineButton>
    </>
  );
}
export default NewMachineButton;
