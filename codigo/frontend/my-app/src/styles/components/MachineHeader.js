import React from 'react';
import { Link } from 'react-router-dom'


function Header(props) {
  const styles = {
    background: 'white',
    position: 'absolute',
    width: '100vw',
    height: '15vh',
    left: '0',
    top: '0vh',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
  };

  const links = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    margin: '5vw',
    textDecoration: 'none',
    color: 'black',
  };

  const circle = {
    width: '3vh',
    height: '3vh',
    borderRadius: '50%',
    background: 'green',
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    marginTop: '6vh',
  };

  const text = {
    marginBottom: '16vh',
    fontSize: '1.5vw',
  };

  const back = {
    background: '0',
    position: 'absolute',
    left: '4vw',
    textDecoration: 'none',
    color: 'black',
  };

  const line = {
    position: 'absolute',
    height: '2px',
    background: 'green',
    width: '35vw',
    top: '10.3vh',
    left: '33vw',
  };


  return (
    <div style={styles}>
      <div style={line}></div>
      <Link to="/" style={back}>
        <div style={circle}><p style={text}>VOLTAR</p></div>
      </Link>
      <Link to="/home" style={links}>
        <div style={circle}><p style={text}>INÍCIO</p></div>
      </Link>
      <Link to="/machines" style={links}>
        <div style={circle}><p style={text}>MÁQUINAS</p></div>
      </Link>
      <Link to="/requests" style={links}>
        <div style={circle}><p style={text}>PEDIDOS</p></div>
      </Link>
      <Link to="/results" style={links}>
        <div style={circle}><p style={text}>RESULTADO</p></div>
      </Link>
    </div>
  );
}

export default Header;