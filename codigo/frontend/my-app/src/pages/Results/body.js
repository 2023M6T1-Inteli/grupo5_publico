import React from 'react';

function Background() {
  const styles = {
    background: 'linear-gradient(to bottom, #88BB9C, white)',
    position: 'absolute',
    width: '100vw',
    height: '80vh',
    top: '15vh',
    borderRadius: '60px',
    zIndex: '-1'
  };

  return (
    <div style={styles}>
    </div>
  );
}

export default Background;