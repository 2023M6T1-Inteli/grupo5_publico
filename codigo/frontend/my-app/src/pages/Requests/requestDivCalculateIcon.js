import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCalculator } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';


function RequestDivCalculateIcon({value}) {
  const navigate = useNavigate();

  const styles = {
    position: 'absolute',
    fontSize: '3vh',
    left: '88vw',
    top: '0.7vh',
    cursor: 'pointer',
    background: '0',
    border: '0',
  };

  let currentRequest = value;

  const handleCalculateClick = (currentRequest) => {
    fetch("http://localhost:8080/pedidos/run/" + currentRequest, {
      method: "post"
    })  
    .then((response) => {
      if (response.ok) {
        alert("Calculado com sucesso, navegando para os resultados!")
        navigate(`/results/${currentRequest}`);
      } else {
        alert("Erro ao calcular!");
      }
    })
  };

  return (
    <button style={styles} onClick={() => handleCalculateClick(currentRequest)}>
      <FontAwesomeIcon icon={faCalculator} color='green'/>
    </button>
  );
}

export default RequestDivCalculateIcon;