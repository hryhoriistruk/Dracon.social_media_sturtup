import React from 'react';
import './App.css';
import Feed from './Feed';
import Widgets from './Widgets'

import Sidebar from './Sidebar';

function App() {
  return (
    //BEM
    <div className="app">
      <Sidebar/>
      <Feed/>
      <Widgets/>
    </div>
  );
}

export default App;
