import styled from "styled-components";

import TemporaryContainerImage from "../../assets/machines.png";
import { ReactComponent as LogoSVG } from "../../assets/Logo.svg";
import { ReactComponent as GitHubSVG } from "../../assets/GitHubRep.svg";

export const Logo = styled(LogoSVG)`
  width: fit-content;
  height: fit-content;

  transform: scale(0.8);

  position: absolute;
  left: 48px;
  top: 32px;
`;

export const TemporaryContainer = styled.div`
  width: 60vw;
  height: 60vh;

  position: relative;

  background-image: url(${TemporaryContainerImage});
  background-size: cover;
`;

export const MainContainer = styled.div`
  width: 60vw;
  height: 60vh;

  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
`;

export const LowerContainer = styled.div`
  width: 100%;
  height: 100%;
  background-color: gray;
  overflow: show;

  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
`;

export const UpperContainer = styled.div`
  width: 100%;
  height: 100%;
  background-color: gray;
  margin-top: 24%;
  filter: brightness(0.8);
  border-radius: 48px;
  overflow: hidden;
`;

export const Container = styled.div`
  width: 100%;
  height: 60%;
`;

export const Steps = styled.div`
  width: 100%;
  height: 40%;

  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;

  background-color: white;
`;

export const Step = styled.div`
  width: 100%;
  height: 60%;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  &:first-of-type {
    border-right: 1px solid gray;
  }

  &:last-of-type {
    border-left: 1px solid gray;
  }
`;

export const StepTitle = styled.h3`
  text-align: center;
`;

export const StepDescription = styled.p`
  padding: 16px;
  text-align: center;
`;

export const GitHubReposContainer = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;

  position: absolute;

  gap: 12px;

  left: 48px;
  bottom: 32px;
`;

export const GitHubReposLogoComponent = styled(GitHubSVG)`
  width: fit-content;
  height: fit-content;
`;

export const GitHubReposLogo = styled.a.attrs(({ children }) => ({
  href: "https://github.com",
  children: (
    <>
      {children} <GitHubReposLogoComponent />
    </>
  ),
}))``;

export const StartButton = styled.button`
  width: 146px;
  height: 52px;

  position: absolute;

  bottom: 16px;

  background: linear-gradient(98.88deg, #009039 -2.99%, #000000 102.28%);
  border: 1px solid #009039;
  border-radius: 36px;

  border-radius: 32px;
  border: none;

  color: white;

  z-index: 10;

  transition: all ease-in-out 0.5s;

  cursor: pointer;

  &:hover {
    transform: scale(1.05);
    filter: brightness(1.1);
  }
`;
