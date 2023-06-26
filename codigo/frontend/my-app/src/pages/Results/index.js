import React from 'react';
import { Link, useParams } from 'react-router-dom'
import Background from './body';
import SideBar from './sideBar';
import NewSketchDiv from './newSketchDiv';

function Results(props) {

  return (
    <div>  
      <Background/>
      <SideBar />
      <NewSketchDiv/>
    </div>
  );
}

export default Results;