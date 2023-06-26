import React, { useState } from "react";
import GradientBackground from "../../styles/components/GradientBackground";
import SearchBar from "../../styles/components/SearchBar";
import NewMachineButton from "../../styles/components/NewMachineButton";
import MachineBox from "../../styles/components/MachineBox";
import ModalMachine from "../../styles/components/ModalMachine";
import NavBar from "../../styles/components/NavBar";
import { LandingPageContainer } from "../../styles/layouts/LandingPage.style";
import { MachinesContainer } from "../../styles/components/MachinesContainer";

function Machines(props) {
  const { vetor, machines, setMachines } = props;
  const [pagina, setPagina] = useState("machines");

  return (
    <>
      <LandingPageContainer>
      <NavBar defaultActiveIndex={1} />
        <GradientBackground>
          <SearchBar />

          <MachinesContainer>
            {vetor.map((obj, index) => (
              <MachineBox
                key={index}
                vetor={obj}
                setMachines={setMachines}
                machines={machines}
              />
            ))}
          </MachinesContainer>
        </GradientBackground>

        <NewMachineButton pagina={pagina} />

        <ModalMachine />
      </LandingPageContainer>
    </>
  );
}

export default Machines;
