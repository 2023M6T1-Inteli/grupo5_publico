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

function TutorialHome(props) {
  const navigate = useNavigate();

  const goToPage = (page) => {
    navigate(page);
  };

  return (
    <LandingPageContainer>
      <NavBar defaultActiveIndex={0} />

      <Logo />

      <MainContainer>
        <LowerContainer>
          <UpperContainer>
            <Steps style={{height: '100%'}}>
              <Step>
                <StepTitle> Bem vindo</StepTitle>
                <StepDescription>
                  Esse é a tela de tutorial da nossa aplicação, aqui você aprenderá como usa-la de forma dinamica.
                
                </StepDescription>
              </Step>
            </Steps>
          </UpperContainer>
        </LowerContainer>
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

export default TutorialHome;
