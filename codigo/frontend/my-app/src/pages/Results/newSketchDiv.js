import React from 'react';
import NewSketch from './newSketch';

function NewSketchDiv(props) {

  const styles = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    marginLeft: '25vw',
    top: '20vh',
    backgroundColor: 'white',
    width: '73vw',
    height: '65vh',
    borderRadius: '10px',
    position: 'absolute',
    overflow: 'auto',
  };


  return (
    <div style={styles}>
      <NewSketch/>
    </div>
  );
}

export default NewSketchDiv;