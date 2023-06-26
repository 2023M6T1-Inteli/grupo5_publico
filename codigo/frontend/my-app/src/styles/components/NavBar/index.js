import React from "react";

import {
  NavContainer,
  BreadCrumb,
  BreadCrumbDot,
  NavButtons,
  NavItem,
} from "../../layouts/NavBar.style";

function NavBar(props = {}) {
  const { defaultActiveIndex = 0 } = props;

  const currentUrl = window.location.href;
  console.log(currentUrl);

  let tutorial;

  if(currentUrl.includes("tutorial") || currentUrl == "http://localhost:3000/"){
    tutorial = true;
  } else {
    tutorial = false;
  }

  let machines;
  let requests;
  let results;

  if (tutorial) {
    machines = "tutorialMachines";
    requests = "tutorialRequests";
    results = "tutorialResults";
  } else {
    machines = "machines";
    requests = "requests";
    results = "results";
  }



  const navItems = [
    { to: "/", label: "Início" },
    { to: `/${machines}`, label: "Máquinas" },
    { to: `/${requests}`, label: "Pedidos" },
    { to: `/${results}`, label: "Resultado" },
  ];

  const [activeIndexes, setActiveIndexes] = React.useState([
    defaultActiveIndex,
  ]);

  function addActiveIndex(index = 0) {
    if (index === defaultActiveIndex || activeIndexes.includes(index)) {
      return;
    }
    const newActiveIndexes = [...activeIndexes];
    newActiveIndexes.push(index);
    setActiveIndexes(newActiveIndexes);
  }

  function removeActiveIndex(index = 0) {
    if (index === defaultActiveIndex || !activeIndexes.includes(index)) {
      return;
    }
    const newActiveIndexes = [...activeIndexes];
    const indexToRemove = newActiveIndexes.indexOf(index);
    newActiveIndexes.splice(indexToRemove, 1);
    setActiveIndexes(newActiveIndexes);
  }

  return (
    <NavContainer>
      <NavButtons>
        {navItems.map((item, index) => (
          <NavItem
            key={`nav-item-${index}`}
            onMouseEnter={() => addActiveIndex(index)}
            onMouseLeave={() => removeActiveIndex(index)}
            to={item.to}  
            className={activeIndexes.includes(index) ? "active" : "inactive"}
          >
            {item.label}
          </NavItem>
        ))}
      </NavButtons>

      <BreadCrumb>
        {Array.from({ length: 4 }).map((_, i) => (
          <BreadCrumbDot
            key={`dot-${i}`}
            className={activeIndexes.includes(i) ? "active" : "inactive"}
          />
        ))}
      </BreadCrumb>
    </NavContainer>
  );
}

export default NavBar;
