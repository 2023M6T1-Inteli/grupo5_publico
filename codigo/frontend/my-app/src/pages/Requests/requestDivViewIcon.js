import React from 'react';
import PropTypes from 'prop-types';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEye } from '@fortawesome/free-solid-svg-icons';
import { useNavigate } from 'react-router-dom';

function RequestDivViewIcon({value}) {
  const navigate = useNavigate();

  const styles = {
    position: 'absolute',
    fontSize: '3vh',
    left: '84vw',
    top: '0.7vh',
    cursor: 'pointer',
    background: '0',
    border: '0',
  };

  const handleViewClick = () => {
    navigate(`/results/${value}`);
  };

  return (
    <button style={styles} onClick={handleViewClick}>
      <FontAwesomeIcon icon={faEye} color='green'/>
    </button>
  );
}

export default RequestDivViewIcon;