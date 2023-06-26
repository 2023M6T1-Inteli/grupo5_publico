import React from "react";

import {
  NavContainer,
  BreadCrumb,
  BreadCrumbDot,
  NavButtons,
  NavItem,
} from "../src/styles/layouts/NavBar.style";

function NavBar(props = {}) {
  const { defaultActiveIndex = 0 } = props;

  const navItems = [
    { to: "/machines", label: "Máquinas" },
    { to: "/requests", label: "Pedidos" },
    { to: "/results", label: "Resultado" },
  ];

  const [activeIndexes, setActiveIndexes] = React.useState([
    defaultActiveIndex,
  ]);

  function addActiveIndex(index) {
    if (index === defaultActiveIndex || activeIndexes.includes(index)) {
      return;
    }
    const newActiveIndexes = [...activeIndexes];
    newActiveIndexes.push(index);
    setActiveIndexes(newActiveIndexes);
  }

  function removeActiveIndex(index) {
    if (index === defaultActiveIndex || !activeIndexes.includes(index)) {
      return;
    }
    const newActiveIndexes = [...activeIndexes];
    const indexToRemove = newActiveIndexes.indexOf(index);
    newActiveIndexes.splice(indexToRemove, 1);
    setActiveIndexes(newActiveIndexes);
  }

  const styles = {
    marginTop: '2vh',
  };

  return (
    <center style={styles}>
      <NavContainer>
      <NavButtons>
        {navItems.map((item, index) => (
          <NavItem
            key={`nav-item-${index}`}
            onMouseEnter={() => addActiveIndex(index)}
            onMouseLeave={() => removeActiveIndex(index)}
            to={item.to}
            className={activeIndexes.includes(index) ? "inactive" : "inactive"}
          >
            {item.label}
          </NavItem>
        ))}
      </NavButtons>

      <BreadCrumb>
        {Array.from({ length: 3 }).map((_, i) => (
          <BreadCrumbDot
            key={`dot-${i}`}
            className={activeIndexes.includes(i) ? "active" : "inactive"}
          />
        ))}
      </BreadCrumb>
    </NavContainer> 
    </center>
  );
}

export default NavBar;
