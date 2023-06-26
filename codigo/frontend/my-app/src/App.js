import React, { useEffect, useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'

import Home from './pages/Home'
import Machines from './pages/Machines'
import Requests from './pages/Requests'
import Results from './pages/Results'
import Header from './Header'
import TutorialHome from "./pages/Home/tutorialHome";
import TutorialMachines from "./pages/Machines/tutorialMachines";
import TutorialRequests from "./pages/Requests/tutorialRequests";
import TutorialResults from "./pages/Results/tutorialResults";

import "./styles/layouts/global.css";

function App(props) {
  const [showHeader, setShowHeader] = useState(false);
  const [machines, setMachines] = useState([]);
  const [orders, setOrders] = useState([]);

  // [] to make the requisition only once in the API
  useEffect(() => {
    fetch("http://localhost:8080/maquinas/listar")
      .then(returnMachine => returnMachine.json())
      .then(returnMachineConverted => setMachines(returnMachineConverted));
  }, []);
  
  useEffect(() => {
    fetch("http://localhost:8080/pedidos/listar")
      .then(returnOrders => returnOrders.json())
      .then(returnOrdersConverted => setOrders(returnOrdersConverted));
  }, []);
  return (
    // <p> {JSON.stringify(orders)} </p>
    <Router>
      {showHeader && <Header />}
      <Routes>
      <Route path="/" element={<TutorialHome start={() => setShowHeader(true)} />} />
      <Route path="/machines" element={<Machines vetor={machines} machines={machines} setMachines={setMachines} />} />
      <Route path="/requests" element={<Requests vetorOrders={orders} orders={orders} setOrders={setOrders} start={() => setShowHeader(false)} />} />
      <Route path="/results/:id" element={<Results start={() => setShowHeader(false) } />} />
      <Route path="/tutorialMachines" element={<TutorialMachines start={() => setShowHeader(true)} />} />
      <Route path="/tutorialRequests" element={<TutorialRequests start={() => setShowHeader(true)} />} />
      <Route path="/tutorialResults" element={<TutorialResults start={() => setShowHeader(true)} />} />
      </Routes>
    </Router>
  );
}

export default App;