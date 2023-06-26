import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

function NewSketch() {
  const { id } = useParams(); // get the value of "id" from the URL
  const [data, setData] = useState(null); // State variable to store the received data

  useEffect(() => {
    fetch(`http://localhost:8080/resultados/listar/${id}`)
      .then(retorno => retorno.json())
      .then(retorno_convertido => {
        console.log('Received data:', retorno_convertido);
        setData(retorno_convertido); // Update the state variable with the received data
      })
      .catch(error => {
        console.error('Error:', error);
      });
  }, [id]);

  const divideRectangle = (i) => {
    const divisions = [];

    const cuttingPattern = data[i];
    const { qtdeCortes, tamanhoCortes, perdaCortes } = cuttingPattern;

    const countDivisionStyle = {
      width: "8%",
      minWidth: "8%",
      height: "100%",
      backgroundColor: "#88BB9C",
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      color: "black",
      fontWeight: "bold",
      fontSize: "20px",
    };

    divisions.push(
      <div key={`count-${i}`} style={countDivisionStyle}>
        {qtdeCortes}x
      </div>
    );

    const sizes = tamanhoCortes.split(' ').map(size => parseInt(size));
    let cumulativeWidth = 10;

    for (let j = 0; j < sizes.length; j++) {
      const size = sizes[j];
      const backgroundColor =
        j === sizes.length - 1 ? "#FF0000" : j % 2 === 0 ? "rgba(0, 144, 57, 1)" : "rgba(0, 144, 57, 0.8)";

      const divisionStyle = {
        width: `${size}%`,
        height: "100%",
        backgroundColor,
        borderLeft: "4px dashed #88BB9C",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        fontSize: "0.8rem",
        color: "#000000",
        fontWeight: "bold",
      };

      if (!isNaN(size)) {
        divisions.push(
          <div key={`division-${i}-${j}`} style={divisionStyle}>
            {size} mm
          </div>
        );
      }
      cumulativeWidth += size;
    }

    const wasteStyle = {
      height: "100%",
      backgroundColor: "#FF0000",
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      textAlign: "center",
      fontSize: "0.8rem",
      color: "#000000",
      fontWeight: "bold",
    };
    
    console.log(perdaCortes);
  
    if (perdaCortes > 0) {
      divisions.push(
        <div key={`waste-${i}`} style={wasteStyle}>
          {perdaCortes} mm
        </div>
      );
    }

    return divisions;
  };

  if (!data) {
    // Data is still being fetched, return loading state or null
    return <div>Loading...</div>;
  }

  const rectangles = [];

  for (let i = 0; i < data.length; i++) {
    rectangles.push(
      <div key={`rectangle-${i}`} style={{
        width: "90%",
        height: "15vh",
        minHeight: "15vh",
        backgroundColor: "#105020",
        border: "1px solid #88BB9C",
        marginTop: "5vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}>
        {divideRectangle(i)}
      </div>
    );
  }

  return (
    <>
      {rectangles}
    </>
  );
}

export default NewSketch;
