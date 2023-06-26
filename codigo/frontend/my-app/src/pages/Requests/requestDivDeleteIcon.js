import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faTrash } from '@fortawesome/free-solid-svg-icons';

function RequestDivDeleteIcon({value}) {
  const styles = {
    position: 'absolute',
    fontSize: '3vh',
    left: '91.5vw',
    top: '0.7vh',
    cursor: 'pointer',
    background: '0',
    border: '0',
  };
  const id = value;

  const handleDeleteClick = (id) => {
    fetch(`http://localhost:8080/pedidos/remover/${id}`, {
      method: 'DELETE',
    }).then((response) => {
      if (response.ok) {
        alert('Pedido removido com sucesso!');
        window.location.reload();
      } else {
        alert('Erro ao remover pedido!');
      }
    })
  };

  return (
    <button style={styles} onClick={() => handleDeleteClick(id)}>
      <FontAwesomeIcon icon={faTrash} color='green' />
    </button>
  );
}

export default RequestDivDeleteIcon;