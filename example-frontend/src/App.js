import React, { Component } from 'react';
import Search from './container/Search';
import CustoemerDetail from './container/customer_detail';
import './css/App.css';

class App extends Component {

  render() {
    return (
      <div className="App">
        <Search />
        <CustoemerDetail />
      </div>
    );
  }
}

export default App;
