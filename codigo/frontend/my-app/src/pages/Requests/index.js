import React from 'react';
import Body from './body';
import NavBar from '../../styles/components/NavBar';

function Requests(props) {
  const { vetorOrders, orders, setOrders } = props;
  return (
    <div>
      <div style={{display:"flex", alignContents:"center", justifyContent:"center", marginTop:"1%"}}>
        <NavBar defaultActiveIndex={2} />
      </div>
      {vetorOrders.length > 0 ? (
        vetorOrders.map((obj, index) => (
          <Body key={index} vetorOrders={obj} setOrders={setOrders} orders={orders} />
        ))
      ) : (
        <Body />
      )}
    </div>
  );
}

export default Requests;