import React, { useEffect } from "react";
import Typography from "@mui/material/Typography";
import Modal from "@mui/material/Modal";
import InfoBar from "./InfoBar";
import NumberBar from "./NumberBar";
import Button from "@mui/material/Button";
import WarningIcon from "@mui/icons-material/Warning";
import MachineSize from "./MachineSize";
import JumboBar from "./JumboBar";
import Fade from "@mui/material/Fade";
import Backdrop from "@mui/material/Backdrop";
import { useState } from "react";
import NumberSlices from "./NumberSlices";
import UploadFileIcon from "@mui/icons-material/UploadFile";
import NumberBarOrder from "./NumberBarOrder";
import JumboBarOrder from "./JumboBarOrder";
import Box from '@mui/material/Box';
import FormControl from '@mui/material/FormControl';

const style = {
  position: "absolute",
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: "40rem",
  bgcolor: "background.paper",
  border: "2px solid #000",
  borderRadius: "10px",
  boxShadow: 24,
  padding: 4,
};

// Modal for the new machines
function ModalMachine({
  state,
  close,
  machines,
  setMachines,
  orders,
  data,
  setOrders,
  pagina,
}) {
  //Initial object for the API requisitions machines
  let machine = {
    id: data ? data.id : null,
    nome: data ? data.nome : "",
    tamanho: data ? data.tamanho : 0.0,
    quantidadeFacas: data ? data.quantidadeFacas : 0,
    larguraJumboMax: data ? data.larguraJumboMax : 0.0,
    larguraJumboMin: data ? data.larguraJumboMin : 0.0,
    multiploTiradas: data ? data.multiploTiradas : 0,
  };

  const [objMachine, setObjMachine] = useState(() => machine);

  const [chosenMachine, setChosenMachine] = useState('');

  const newChosenMachine = (event) => {
    console.log(event.target.value);
    const { value } = event.target.value;
    setChosenMachine(value);
  };

  //Getting the inputs of the new machines
  const inputMachine = (e) => {
    setObjMachine({ ...objMachine, [e.target.name]: e.target.value });
    console.log(e.target);
  };

  //Post the new created machine to the database
  const registerMachine = () => {
    fetch("http://localhost:8080/maquinas/cadastrar", {
      method: "post",
      body: JSON.stringify(objMachine),
      headers: {
        "Content-type": "application/json",
        Accept: "application/json",
      },
    })
      .then((retorno) => retorno.json())
      .then((retornoConvertido) => {
        console.log(retornoConvertido);
        if (retornoConvertido.mensagem !== undefined) {
          alert(retornoConvertido.mensagem);
        } else {
          alert("Máquina criada com sucesso!");
          // MachineCreated();
          window.location.reload();
        }
      });
  };

  //Initial object for the API requisitions orders
  const order = {
    maquina: 0,
    nome: "",
    data: "",
    quantidadeBobinas: 0,
    quantidadeRolosJumbo: 0,
    tamanhoMedioPonta: 0,
    porcentagemPerda: 0.0,
    csvPath: "",
  };

  const [availableMachines, setAvailableMachines] = useState([]);

  //Getting the available machines
  useEffect(() => {
    fetch("http://localhost:8080/maquinas/listar")
      .then((response) => response.json())
      .then((data) => {
        setAvailableMachines(data);
      });
  }, []);

  const [objOrder, setObjOrder] = useState(order);

  //Getting the inputs of the new orders
  const inputOrders = (e) => {
    setObjOrder({ ...objOrder, [e.target.name]: e.target.value });
    console.log(e.target);
    console.log(objOrder);
  };

  const onChangeValue = (name, value) => {
    setObjMachine({ ...objMachine, [name]: value });
  };

  //Initial object for file upload
  const [file, setFile] = useState(null);

  //Getting the file
  const handleFile = (e) => {
    setFile(e.target.files[0]);
  };

  const runAlgorithm = (id) => {
    fetch("http://localhost:8080/pedidos/run/" + id, {
      method: "post"
    })
  }

  const createOrder = () => {
    fetch("http://localhost:8080/pedidos/cadastrar", {
      method: "post",
      body: JSON.stringify(objOrder),
      headers: {
        "Content-type": "application/json",
        Accept: "application/json",
      },
    })
      .then((returnOrders) => returnOrders.json())
      .then((returnOrdersConverted) => {
        console.log(returnOrdersConverted);
        if (returnOrdersConverted.mensagem !== undefined) {
          alert(returnOrdersConverted.mensagem);
        } else {
          alert("Pedido criado com sucesso!");
          window.location.reload();
        }
        runAlgorithm(returnOrdersConverted.id);
      });
  };

  //Post the new created order to the database
  const uploadCSV = () => {

    const formData = new FormData();
    let path;
    formData.append("file", file);
    fetch("http://localhost:8080/pedidos/upload", {
      method: "post",
      body: formData,
    }).then((response) => {
      response.json().then((body) => {
        setTimeout(() => {
        path = body.mensagem;
        objOrder.csvPath = path;
        createOrder();
        }, 2000);
    })});
  };

  // Update Machine
  const updateMachine = () => {
    fetch("http://localhost:8080/maquinas/alterar", {
      method: "put",
      body: JSON.stringify(objMachine),
      headers: {
        "Content-type": "application/json",
        Accept: "application/json",
      },
    })
      .then((returnOrders) => returnOrders.json())
      .then((returnOrdersConverted) => {
        console.log(returnOrdersConverted);
        if (returnOrdersConverted.mensagem !== undefined) {
          alert(returnOrdersConverted.mensagem);
        } else {
          alert("Maquina alterada com sucesso!");
          window.location.reload();
        }
      });
  };

  // Delete machine from database
  const deleteMachine = () => {
    fetch("http://localhost:8080/maquinas/remover/" + objMachine.id, {
      method: "delete",
      body: JSON.stringify(objMachine),
      headers: {
        "Content-type": "application/json",
        Accept: "application/json",
      },
    })
      .then((returnOrders) => returnOrders.json())
      .then((returnOrdersConverted) => {
        console.log(returnOrdersConverted);
        if (returnOrdersConverted.mensagem !== undefined) {
          alert(returnOrdersConverted.mensagem);
        } else {
          alert("Maquina removida com sucesso!");
        }
        window.location.reload();
      });
  };

  return (
    <Modal
      open={state}
      onClose={close}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
      closeAfterTransition
      slots={{ backdrop: Backdrop }}
      slotProps={{
        backdrop: {
          timeout: 500,
        },
      }}
    >
      <Fade in={state}>
        <Box sx={style}>
          {pagina == "machines" ? (
            <>
              {/* <p> {JSON.stringify(objMachine)}</p> test*/}
              <div>
                <Typography
                  id="modal-mod al-title"
                  variant="h6"
                  component="h2"
                  color={"green"}
                  textAlign={"center"}
                >
                  MÁQUINA
                </Typography>
              </div>
              <div style={{ display: "flex" }}>
                <div style={{ width: "40%" }}>
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Nome da Máquina
                  </Typography>
                  <InfoBar input={inputMachine} />
                </div>
                <div
                  onChange={inputMachine}
                  style={{
                    width: "50%",
                    textAlign: "center",
                    marginLeft: "4em",
                  }}
                >
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Tamanho da Máquina
                  </Typography>
                  <MachineSize />
                </div>
              </div>
              <div style={{ display: "flex" }}>
                <div onChange={inputMachine} style={{ width: "50%" }}>
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Quantidade de Facas
                  </Typography>
                  <NumberBar />
                </div>
                <div
                  onChange={inputMachine}
                  style={{ width: "50%", textAlign: "center" }}
                >
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Largura do Jumbo
                  </Typography>
                  <JumboBar />
                </div>
                <div
                  onChange={inputMachine}
                  style={{ width: "50%", textAlign: "right" }}
                >
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Múltiplo de Tiradas
                  </Typography>
                  <NumberSlices />
                </div>
              </div>
              <div
                style={{
                  width: "100%",
                  textAlign: "right",
                  paddingTop: "2rem",
                }}
              >
                <ChildModalDiscard close={close} />
                <Button
                  onClick={registerMachine}
                  variant="contained"
                  color="success"
                  style={{
                    marginLeft: "1rem",
                    borderRadius: "20px",
                    paddingLeft: "2rem",
                    paddingRight: "2rem",
                  }}
                >
                  Salvar
                </Button>
              </div>
            </>
          ) : pagina == "requests" ? (
            <>
              <div>
                <Typography
                  id="modal-modal-title"
                  variant="h6"
                  component="h2"
                  color={"green"}
                  textAlign={"center"}
                >
                  NOVO PEDIDO
                </Typography>
              </div>
              <div style={{ display: "flex" }}>
                <div style={{ width: "100%" }}>
                  <div onChange={inputOrders}>
                    <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                      Nome do Pedido
                    </Typography>
                    <InfoBar />
                  </div>

                  <div style={{ display: "flex" }}>
                    <div onChange={inputOrders} style={{ width: "50%" }}>
                      <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                        Largura Min. Total
                      </Typography>
                      <NumberBarOrder />
                    </div>
                    <div
                      onChange={inputOrders}
                      style={{ width: "50%", textAlign: "right" }}
                    >
                      <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                        Largura Max. Ponta
                      </Typography>
                      <JumboBarOrder />
                    </div>
                  </div>
                  <div style={{margin: "10%"}} onChange={inputOrders}>
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Máquinas Disponíveis
                  </Typography>
                  <Box sx={{ minWidth: 120 }}>
                    <FormControl>
                      <select name="maquina" value={chosenMachine} id="demo-simple-select" onChange={newChosenMachine}>
                        <option value="" disabled>Selecione uma máquina</option>
                        {availableMachines.length > 0 ? (
                          availableMachines.map((machine) => (
                            <option value={machine.id}>{machine.nome} </option>
                          ))
                        ) : (
                          <option value="" disabled>Nenhuma máquina disponível</option>
                        )}
                      </select>
                    </FormControl>
                  </Box>
                  </div>
                </div>
                <div
                  style={{
                    width: "40%",
                    textAlign: "center",
                    marginLeft: "3em",
                    border: "2px solid green",
                    borderRadius: "10px",
                    paddingTop: "5%",
                    paddingBottom: "5%",
                  }}
                >
                  <div style={{ display: "flex", justifyContent: "center", flexDirection: "column", alignContents: "center"}}>
                  <div>
                    <UploadFileIcon style={{ fontSize: 80, color: "green" }} />
                  </div>
                  <div style={{ margin: "10%", textAlign: "center" }}>
                    <input
                      type="file"
                      id="file"
                      name="file"
                      onChange={handleFile}
                    />
                  </div>
            </div>
                </div>
              </div>
              <div
                style={{
                  width: "100%",
                  textAlign: "right",
                  paddingTop: "2rem",
                }}
              >
                <ChildModalDiscard close={close} />
                <Button
                  onClick={uploadCSV}
                  variant="contained"
                  color="success"
                  style={{
                    marginLeft: "1rem",
                    borderRadius: "20px",
                  }}
                >
                  Calcular
                </Button>
              </div>
            </>
          ) : (
            <>
              <div>
                <Typography
                  id="modal-mod al-title"
                  variant="h6"
                  component="h2"
                  color={"green"}
                  textAlign={"center"}
                >

                  {/* Edit Machine Modal */}
                  EDITAR MÁQUINA
                </Typography>
              </div>
              <div style={{ display: "flex" }}>
                <div style={{ width: "40%" }}>
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Nome da Máquina
                  </Typography>
                  <input
                    placeholder="name"
                    name="machine_name"
                    value={data ? objMachine.nome : ""}
                    onChange={(e) => onChangeValue("nome", e.target.value)}
                  />
                  {/* {vetor} */}
                  {/* <InfoBar input={inputMachine} /> */}
                </div>
                <div
                  style={{
                    width: "50%",
                    textAlign: "center",
                    marginLeft: "4em",
                  }}
                >
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Tamanho da Máquina
                  </Typography>
                  <input
                    id="1"
                    type="radio"
                    placeholder="name"
                    name="machine_sz"
                    checked={data ? Number(objMachine.tamanho) == 40 : false}
                    onChange={(e) => onChangeValue("tamanho", 40)}
                  />
                  <label for="1">Pequena</label>
                  <input
                    id="2"
                    type="radio"
                    placeholder="name"
                    name="machine_sz"
                    value={50}
                    checked={data ? Number(objMachine.tamanho) == 50 : false}
                    onChange={(e) => onChangeValue("tamanho", 50)}
                  />
                  <label for="2">Media</label>
                  <input
                    id="3"
                    type="radio"
                    placeholder="name"
                    name="machine_sz"
                    checked={data ? Number(objMachine.tamanho) == 60 : false}
                    onChange={(e) => onChangeValue("tamanho", 60)}
                  />
                  <label for="3">Grande</label>

                  {/* <MachineSize /> */}
                </div>
              </div>
              <div style={{ display: "flex" }}>
                <div onChange={inputMachine} style={{ width: "50%" }}>
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Quantidade de Facas
                  </Typography>
                  {/* <NumberBar /> */}

                  <select
                    name="amount_knife"
                    onChange={(e) =>
                      onChangeValue("quantidadeFacas", e.target.value)
                    }
                  >
                    {[1, 2, 3, 4, 5, 6, 7, 8].map((e) => (
                      <option
                        value={e}
                        selected={
                          data ? objMachine.quantidadeFacas == e : false
                        }
                      >
                        {e}
                      </option>
                    ))}
                  </select>
                </div>
                <div style={{ width: "50%", textAlign: "center" }}>
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Largura do Jumbo
                  </Typography>
                  <input
                    type="number"
                    name="paper_weight"
                    value={data ? objMachine.larguraJumboMax : ""}
                    onChange={(e) => onChangeValue("larguraJumboMax", e.target.value)}
                  />
                  {/* <JumboBar /> */}
                </div>
                <div style={{ width: "50%", textAlign: "right" }}>
                  <Typography id="modal-modal-description" sx={{ mt: 2 }}>
                    Múltiplo de Tiradas
                  </Typography>
                  {/* <NumberSlices /> */}
                  <select
                    name="times_slices"
                    onChange={(e) =>
                      onChangeValue("multiploTiradas", e.target.value)
                    }
                  >
                    {[1, 2, 3, 4, 5, 6, 7, 8].map((e) => (
                      <option
                        value={e}
                        selected={
                          data ? objMachine.multiploTiradas == e : false
                        }
                      >
                        {e}
                      </option>
                    ))}
                  </select>
                </div>
              </div>
              <div
                style={{
                  width: "100%",
                  textAlign: "right",
                  paddingTop: "2rem",
                }}
              >
                <Button
                  onClick={() => {
                    deleteMachine();
                    close(); // Fechar o modal após deletar
                  }}
                  variant="contained"
                  color="error"
                  type="submit"
                  style={{
                    marginLeft: "1rem",
                    borderRadius: "20px",
                    paddingLeft: "2rem",
                    paddingRight: "2rem",
                  }}
                >
                  Deletar
                </Button>
                <ChildModalDiscard close={close} />
                <Button
                  onClick={updateMachine}
                  type="submit"
                  variant="contained"
                  color="success"
                  style={{
                    marginLeft: "1rem",
                    borderRadius: "20px",
                    paddingLeft: "2rem",
                    paddingRight: "2rem",
                  }}
                >
                  Salvar
                </Button>
              </div>
            </>
          )}
        </Box>
      </Fade>
    </Modal>
  );
}

function ChildModalDiscard({ close }) {
  const [open, setOpen] = React.useState(false);

  // Function to open the modal
  const handleOpen = () => {
    setOpen(true);
  };

  // Function to close the modal
  const handleClose = () => {
    setOpen(false);
  };

  // Function to close the modal and perform additional close actions
  const handleBothClose = () => {
    setOpen(false);
    close(); // Call the 'close' function passed as a prop
  };

  return (
    <React.Fragment>
      {/* Button to open the modal */}
      <Button
        onClick={handleOpen}
        variant="contained"
        color="error"
        style={{
          borderRadius: "20px",
          color: "white",
          backgroundColor: "#A9A9A9",
          marginLeft: "1rem",
        }}
      >
        Cancelar
      </Button>

      {/* Modal component */}
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="child-modal-title"
        aria-describedby="child-modal-description"
      >
        <Fade in={open}>
          <Box
            sx={{
              ...style,
              width: 250,
              textAlign: "center",
              display: "flex",
              flexDirection: "column",
              alignItems: "center",
            }}
          >
            {/* Warning icon */}
            <WarningIcon color="warning" style={{ fontSize: 50 }} />

            {/* Modal title */}
            <Typography
              id="modal-modal-title"
              sx={{ mt: 1 }}
              style={{ paddingBottom: "16px" }}
            >
              Você deseja descartar a criação da máquina?
            </Typography>

            {/* Buttons for confirmation */}
            <div>
              <Button
                onClick={handleBothClose}
                variant="contained"
                color="success"
                style={{
                  marginRight: "1rem",
                  borderRadius: "20px",
                }}
              >
                Sim
              </Button>
              <Button
                onClick={handleClose}
                variant="contained"
                color="error"
                style={{ borderRadius: "20px" }}
              >
                Não
              </Button>
            </div>
          </Box>
        </Fade>
      </Modal>
    </React.Fragment>
  );
}

export default ModalMachine;
