import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faArrowUpWideShort } from '@fortawesome/free-solid-svg-icons';

function RequestRecentSortIcon() {
  const styles = {
    position: 'absolute',
    fontSize: '4vh',
    left: '12vw',
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
      <FontAwesomeIcon icon={faArrowUpWideShort} color='green'/>
      <p style={pStyles}>Recentes</p>
    </button>
  );
}

export default RequestRecentSortIcon;