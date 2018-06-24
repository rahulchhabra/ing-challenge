import React, { Component } from 'react';
import '../css/App.css';
import {connect} from 'react-redux';
import {bindActionCreators} from 'redux';
import {loadCustomerClassification, loadCustomerBalance} from '../actions/index';

class Search extends Component {

  constructor(props) {
    super(props);
    this.state = {customerId: '', yearMonth:''};

    this.onCustomerIdChange = this.onCustomerIdChange.bind(this);
    this.onYearMonthChange = this.onYearMonthChange.bind(this);
    this.onSubmitHandler = this.onSubmitHandler.bind(this);
  }

    onCustomerIdChange(event) {
        this.setState({customerId: event.target.value});
    }

    onYearMonthChange(event) {
        this.setState({yearMonth: event.target.value});
    }

    onSubmitHandler(event) {
        event.preventDefault();

        this.props.loadCustomerClassification(this.state);
        this.props.loadCustomerBalance(this.state);
    }
  render() {
    return (
      <div className="Search">
        <form className='input-group' onSubmit={this.onSubmitHandler}>
            <input placeholder='Enter customer id here'
                   type='number'
                   className='form-control'
                   value={this.state.customerId}
                   onChange={this.onCustomerIdChange} />
            <input type='month'
                   className='form-control'
                   value={this.state.yearMonth}
                   onChange={this.onYearMonthChange} />
            <span className="input-group-btn">
                <button type="submit" className="btn btn-secondary submit-btn">Submit</button>
            </span>
        </form>
      </div>
    );
  }
}

function mapDispatchToProps(dispatch) {
    return bindActionCreators({ loadCustomerClassification, loadCustomerBalance} , dispatch)
}

export default connect(null, mapDispatchToProps)(Search);
