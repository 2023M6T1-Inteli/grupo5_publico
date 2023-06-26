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
} from "../../styles/layouts/Requests.style";

import NavBar from "../../styles/components/NavBar";

function TutorialRequests(props) {
  const navigate = useNavigate();

  const goToPage = (page) => {
    navigate(page);
  };

  return (
    <LandingPageContainer>
      <NavBar defaultActiveIndex={2} />

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
                Clique no "+" para adicionar um pedido.
              </StepDescription>
            </Step>
            <Step>
              <StepTitle>PASSO 2</StepTitle>
              <StepDescription>
                Adicione os dados do pedido.
              </StepDescription>
            </Step>
            <Step>
              <StepTitle>PASSO 3</StepTitle>
              <StepDescription>
                clique em salvar para salvar os dados do pedido.
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

export default TutorialRequests;
