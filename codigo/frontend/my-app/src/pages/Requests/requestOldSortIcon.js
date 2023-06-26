import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowDownWideShort } from '@fortawesome/free-solid-svg-icons';

function RequestRecentSortIcon() {
  const styles = {
    position: 'absolute',
    fontSize: '4vh',
    left: '20vw',
    top: '4vh',
    cursor: 'pointer',
    background: '0',
    border: '0',
  };

  const pStyles = {
    fontSize: '2vh',
  };

  const handleCalculateClick = () => {
    console.log('Period sort clicked!');
  };

  return (
    <button style={styles} onClick={handleCalculateClick}>
      <FontAwesomeIcon icon={faArrowDownWideShort} color='green'/>
      <p style={pStyles}>Antigos</p>
    </button>
  );
}

export default RequestRecentSortIcon;