import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faFileCsv, faCalculator, faEye, faPenToSquare } from '@fortawesome/free-solid-svg-icons'
import { useParams } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';


function SideBar() {

    const [Info, setInfo] = useState(null); // State variable to store the received Info

    const navigate = useNavigate();

    let currentRequest = useParams(); // get the value of "id" from the URL

    useEffect(() => {
        fetch(`http://localhost:8080/pedidos/listar`)
            .then(retorno => retorno.json())
            .then(retorno_convertido => {
                console.log('Received Info:', retorno_convertido);
                setInfo(retorno_convertido); // Update the state variable with the received Info
            });
    }, []);
    
    let nome;
    let data; 
    let qtdeProdutos;
    let qtdeRolos;
    let pctgPerda;
    let mediaPonta;
    let downloadPath;

    if (Info) {
        for (let i = 0; i < Info.length; i++) {
            if (Info[i].id == currentRequest.id) {
                nome = Info[i].nome;
                data = Info[i].data; 
                qtdeProdutos = Info[i].quantidadeBobinas;
                qtdeRolos = Info[i].quantidadeRolosJumbo;
                pctgPerda = Info[i].porcentagemPerda;
                mediaPonta = Info[i].tamanhoMedioPonta;
                downloadPath = Info[i].resultPath;
            }
        }
    }

    const styles = {
        marginLeft: '2.7vw',
        top: '20vh',
        backgroundColor: 'white',
        width: '20vw',
        height: '65vh',
        fontFamily: 'Inter',
        borderRadius: '10px',
        fontSize: '2.3vh',
        position: 'absolute',
        textAlign: 'center',
    }

    const leftDiv = {
        width: '50%',
        height: '90%',
        position: 'absolute',
        marginTop: '0vh',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    }

    const rightDiv = {
        width: '50%',
        height: '90%',
        position: 'absolute',
        marginTop: '0vh',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        left: '50%',
    }

    const items = {
        marginTop: '8vh',
        color: '#009039',
        fontSize: '120%',
    }

    const topItems = {
        marginTop: '3vh',
        left: '35%',
        position: 'absolute',
        fontSize: '130%',
        cursor: 'pointer',
    }

    const bottom = {
        top: '47%',
        position: 'relative',
    }

    const buttons = {
        position: 'relative',
        top: '60%',
        marginLeft: '5vw',
        left: '-2.5vw',
        fontSize: '5vh',
        cursor: 'pointer',
        color: '#009039',
    }

    const mediaLabel = {
        top: '50%',
        position: 'relative',
        color: '#009039',
        fontSize: '120%',
    }

    const fontColor = {
        color: '#009039',
    }

    const handleCsvClick = (csvPath) => {
        fetch("http://localhost:8080/pedidos/download/" + currentRequest.id, {
            method: "get"
        }).then(response => {
            if (response.ok) {
                response.blob().then(blob => {
                    const url = URL.createObjectURL(blob);
                    const link = document.createElement('a');
                    link.href = url;
                    link.download = 'solution.csv';
                    link.click();
                });
            }
        })
    };
      
      

    console.log(currentRequest.id);

    const handleCalculateClick = (currentRequest) => {
        fetch("http://localhost:8080/pedidos/run/" + currentRequest, {
          method: "post"
        })  
        .then((response) => {
          if (response.ok) {
            alert("Calculado com sucesso, recarregando a página...")
            navigate(`/results/${currentRequest}`);
          } else {
            alert("Erro ao calcular!");
          }
        })
      };

    const handleEditNameClick = () => {
        navigate(`/requests`)
    }

    return (
        <div style={styles}>
            <label style={topItems} onClick={handleEditNameClick}> <FontAwesomeIcon style={fontColor} icon={faPenToSquare}/> {nome} </label> <br /> <br />

            <div style={leftDiv}>
                <label style={items}> Data </label>
                <label> {data} </label>
                <label style={items}> Qtd. Rolos </label>
                <label> {qtdeRolos} </label>
            </div>
            <div style={rightDiv}>
                <label style={items}> Qtd. Produtos </label>
                <label> {qtdeProdutos} </label>
                <label style={items}> % Perda </label>
                <label> {pctgPerda}% </label>
            </div>
            <label style={mediaLabel}> Média da Ponta </label>
            <br /> <br />
            <label style={bottom}> {mediaPonta} mm </label>
            <br /> <br />
            <label style={buttons} onClick={() => handleCsvClick("codigo\backend\planejador\src\main\java\inteli\cc6\planejador\algoritmos\csv")}> 
                <FontAwesomeIcon icon={faFileCsv} />  
            </label>
            <label style={buttons} onClick={() => handleCalculateClick(currentRequest.id)}> 
                <FontAwesomeIcon icon={faCalculator}/>  
            </label>
            <div>
            </div>
        </div>
    )
}

export default SideBar;