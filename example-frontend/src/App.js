import React, { Component } from 'react';
import SearchBar from './container/search-bar';
import CustomerDetail from './container/customer_detail';
import './css/App.css';

class App extends Component {

  render() {
    return (
      <div className="App">
        <SearchBar />
        <CustomerDetail />
      </div>
    );
  }
}

export default App;
