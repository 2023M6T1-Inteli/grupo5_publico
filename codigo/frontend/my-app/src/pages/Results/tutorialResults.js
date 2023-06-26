import React from "react";
import { useNavigate } from "react-router-dom";
import { LandingPageContainer } from "../../styles/layouts/LandingPage.style";
import {
  Logo,
  TemporaryContainer,
  MainContainer,
  LowerContainer,
  UpperContainer,
  Container,
  Steps,
  Step,
  StepTitle,
  StepDescription,
  GitHubReposContainer,
  GitHubReposWrapper,
  GitHubReposLogo,
  GitHubReposArrow,
  GitHubReposText,
  StartButton,
} from "../../styles/layouts/resultsp2.style";

import NavBar from "../../styles/components/NavBar";

function TutorialResults(props) {
  const navigate = useNavigate();

  const goToPage = (page) => {
    navigate(page);
  };
  return (
    <LandingPageContainer>
      <NavBar defaultActiveIndex={3} />

      <Logo />

      <MainContainer>
        <TemporaryContainer>
          <Steps
            style={{
              position: "absolute",
              bottom: 0,
              left: "50%",
              transform: "translate(-50%, 30%)",
              width: "95%",
              borderBottomLeftRadius: 48,
              borderBottomRightRadius: 48,
            }}
          >
            <Step style={{ borderBottomLeftRadius: "16px" }}>
              <StepTitle>PASSO 1</StepTitle>
              <StepDescription>
                Nessa tela os resultados do algoritmo ficam disponiveis.
              </StepDescription>
            </Step>
            <Step>
              <StepTitle>PASSO 2</StepTitle>
              <StepDescription>
              É possivel imprimir a página apentando "ctrl+P".
              </StepDescription>
            </Step>
            <Step>
              <StepTitle>PASSO 3</StepTitle>
              <StepDescription>
                É possivel retornar a tela de pedidos para visualizar os resultados de outros pedidos.
              </StepDescription>
            </Step>
          </Steps>
        </TemporaryContainer>
      </MainContainer>

      <GitHubReposContainer>
        <GitHubReposLogo />
      </GitHubReposContainer>

      <StartButton onClick={() => goToPage("/machines")}>
        <div>INICIAR</div>
      </StartButton>
    </LandingPageContainer>
  );
}

export default TutorialResults;
