import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCalendarDays } from '@fortawesome/free-solid-svg-icons';

function RequestCalendarSortIcon() {
  const styles = {
    position: 'absolute',
    fontSize: '4vh',
    left: '5vw',
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
      <FontAwesomeIcon icon={faCalendarDays} color='green'/>
      <p style={pStyles}>Período</p>
    </button>
  );
}

export default RequestCalendarSortIcon;