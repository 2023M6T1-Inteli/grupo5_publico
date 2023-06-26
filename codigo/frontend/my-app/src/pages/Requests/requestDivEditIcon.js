import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPenToSquare } from '@fortawesome/free-solid-svg-icons';

function RequestDivEditIcon() {
  const styles = {
    position: 'absolute',
    fontSize: '3vh',
    left: '80vw',
    top: '0.7vh',
    cursor: 'pointer',
    background: '0',
    border: '0',
  };

  const handleEditClick = () => {
    console.log('Edit clicked!');
  };

  return (
    <button style={styles} onClick={handleEditClick}>
      <FontAwesomeIcon icon={faPenToSquare} color='green'/>
    </button>
  );
}

export default RequestDivEditIcon;