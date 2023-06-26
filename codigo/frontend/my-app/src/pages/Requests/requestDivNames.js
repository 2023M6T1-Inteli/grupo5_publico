import React from 'react';

function RequestDivNames() {
    const styles = {
        position: 'relative',
        width: '95vw',
        height: '5vh',
        left: '2.5vw',
        top: '15vh',
        marginTop: '2vh',
        borderRadius: '10px',
        alignItems: 'center',
        justifyContent: 'center',
        fontSize: '2.5vh',
      };

      const items = {
        position: 'relative',
        marginLeft: '4vw',
        color: 'white',
        fontStyle: 'normal',
        fontFamily: 'Inter',
      };

  return (
    <div style={styles}>
      <label style={items}>Nome</label>
      <label style={items}>Qtd. de rolos Produzidos</label>
      <label style={items}>Qtd. de Rolos Jumbo Usados</label>
      <label style={items}>Tamanho Médio da Ponta</label>
      <label style={items}>% Perda</label>
      <label style={items}>Data</label>
    </div>
  );
}

export default RequestDivNames;