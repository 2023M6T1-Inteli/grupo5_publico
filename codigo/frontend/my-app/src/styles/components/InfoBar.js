import * as React from "react";
import { useState } from "react";

function InfoBar(props) {
  const { input } = props;
  const NameInput=({
    width: "15rem",
    height: "2rem",
    borderRadius: "20px",
    backgroundColor: "#E8E8E8",
    textAlign: "center",
  });

  const [nome, setNome] = useState('');
  const handleInputChange = (event) => {
    const { value } = event.target;
    setNome(value);
  };

  return (
    <form onChange={input}>
        <input style={NameInput}
          placeholder="Nome…"
          name="nome"
          value={nome}  
          onChange={handleInputChange}
        >
        </input>
    </form>
  );
}


export default InfoBar;
