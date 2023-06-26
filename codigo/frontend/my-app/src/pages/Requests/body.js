import React, { useState, useEffect } from "react";
import RequestDiv from './requestDiv'
import RequestDivNames from './requestDivNames'
import RequestCalendarSortIcon from './requestCalendarSortIcon'
import RequestRecentSortIcon from './requestRecentSortIcon'
import RequestOldSortIcon from './requestOldSortIcon'
import NewMachineButton from '../../styles/components/NewMachineButton';

function Body(props) {

  const [pagina, setPaginas] = useState("requests");
  const [lista, setLista] = useState([]);
  const { vetorOrders, orders, setOrders } = props;

  useEffect(() => {
    fetch("http://localhost:8080/pedidos/listar")
    .then(retorno => retorno.json())
    .then(retorno_convertido => setLista(retorno_convertido))
  }, [])

  const styles = {
    background: "linear-gradient(to bottom, #88BB9C, white)",
    position: "absolute",
    width: "100vw",
    height: "80vh",
    left: "0",
    top: "15vh",
    borderRadius: "60px",
  };

  return (
    <>
    <div style={styles}>
      <RequestCalendarSortIcon />
      <RequestRecentSortIcon />
      <RequestOldSortIcon />
      <RequestDivNames />
      {
        lista.map((obj, indice) => (
          <RequestDiv nome={obj.nome} data={obj.data} quantidadeBobinas={obj.quantidadeBobinas} quantidadeRolosJumbo={obj.quantidadeRolosJumbo} tamanhoMedioPonta={obj.tamanhoMedioPonta} porcentagemPerda={obj.porcentagemPerda} id={obj.id} />
        ))
      }
    </div>
      <NewMachineButton pagina={pagina} orders={orders} setOrders={setOrders} />
    </>
  );
}

export default Body;