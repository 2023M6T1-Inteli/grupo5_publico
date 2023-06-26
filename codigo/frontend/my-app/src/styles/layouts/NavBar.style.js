import { Link } from "react-router-dom";

import styled from "styled-components";

const BreadCrumbSize = 4;
const BreadCrumbActiveDotsSize = 12;
const BreadCrumbInactiveDotsSize = 6;

export const NavContainer = styled.nav`
  min-width: 500px;

  width: 40%;
  height: 64px;

  position: relative;

  display: flex;
  flex-direction: column;
  justify-content: space-between;

  a.active {
    color: #fff;
  }

  a.inactive {
    filter: brightness(0.8);
  }

  z-index: 10;
`;

export const NavButtons = styled.div`
  width: 100%;
  height: 100%;

  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
`;

export const NavItem = styled(Link)`
  width: 100px;
  color: #fff;
  text-align: center;
  text-decoration: none;
  font-size: 18px;
  font-family: "Roboto", sans-serif;
  font-weight: lighter;

  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;

  transition: filter ease-in-out 0.2s;

  &:hover {
    filter: brightness(1) !important;
  }
`;

export const BreadCrumbDot = styled.div`
  position: relative;
  border-radius: 100%;
`;

export const BreadCrumb = styled.div`
  width: 83%;
  height: ${BreadCrumbSize}px;

  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;

  padding-left: 50px;
  padding-right: 50px;
  gap: 45px;

  margin: auto;

  padding: 1px;

  background-color: #fff;
  background-clip: content-box;

  .active {
    width: ${BreadCrumbActiveDotsSize}px;
    height: ${BreadCrumbActiveDotsSize}px;
    background-color: #fff;
    border: 1px solid #01673b;
    box-shadow: 0 0 10px rgba(255, 255, 255, 0.5);
    transition: all ease-in-out 0.2s;
  }

  .inactive {
    width: ${BreadCrumbInactiveDotsSize}px;
    height: ${BreadCrumbInactiveDotsSize}px;
    background-color: #fff;
    filter: brightness(0.8);
    transition: all ease-in-out 0.2s;
  }
`;
