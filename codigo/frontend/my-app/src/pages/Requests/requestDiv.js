import React from 'react';
import RequestIndex from './requestIndex'
import Data from './requestData'
import RequestProductAmmount from './requestProductAmmount'
import RequestReelAmmount from './requestReelAmmount'
import RequestMeanEdge from './requestMeanEdge'
import RequestLossPercentage from './requestLossPercentage'
import RequestDivEditIcon from './requestDivEditIcon'
import RequestDivViewIcon from './requestDivViewIcon'
import RequestDivCalculateIcon from './requestDivCalculateIcon'
import RequestDivDeleteIcon from './requestDivDeleteIcon'

function RequestDiv({nome, data, quantidadeBobinas, quantidadeRolosJumbo, tamanhoMedioPonta, porcentagemPerda, id}) {
    const styles = {
        background: 'linear-gradient(to bottom, white, white)',
        display: 'flex',
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

  return (
    <div style={styles}>
        <RequestIndex value = {nome}/>
        <Data value = {data} />
        <RequestProductAmmount value = {quantidadeBobinas} />
        <RequestReelAmmount value = {quantidadeRolosJumbo} />
        <RequestMeanEdge value = {tamanhoMedioPonta} />
        <RequestLossPercentage value = {porcentagemPerda} />
        <RequestDivEditIcon value = {id} />
        <RequestDivViewIcon value = {id}/>
        <RequestDivCalculateIcon value = {id} />
        <RequestDivDeleteIcon value = {id} />
    </div>
  );
}

export default RequestDiv;