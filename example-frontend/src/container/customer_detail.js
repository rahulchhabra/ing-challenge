import React, {Component} from 'react';
import {connect} from 'react-redux';
import Moment from 'react-moment';

class CustomerDetail extends Component{
    renderTransaction(transaction) {
        const amountCell = <td>{transaction.amount}</td>;
        const emptyCell = <td></td>;
        const descCell = <td>{transaction.description}</td>;
        const dateCell = <td><Moment format='DD MMM YYYY, h:mm a' date={new Date(transaction.transactionDate)}></Moment></td>;

        if(transaction.amount > 0) {
            return (
                <tr key={transaction.transactionDate-transaction.amount}>
                    {dateCell}
                    {descCell}
                    {amountCell}{emptyCell}
                </tr>
            );

        } else {
            return (
                <tr key={transaction.transactionDate-transaction.amount}>
                    {dateCell}
                    {descCell}
                    {emptyCell}{amountCell}
                </tr>
            );

        }
    }

    render() {
        if(!this.props.customerClassification || !this.props.customerInfo) {
            return (
                <p className="no-customer-data">Please enter customer id and month to view classification, balance and transaction</p>
            )
        }
        return (
            <div className='customerDetail sectionStart'>
                <div className='row customerInfo'>
                    <div className='col-md-6 basicCustomerInfo'>
                        <h5 className='sectionHeading'>Account Information</h5>
                        <div className="row">
                            <div className='col-md-6'><span className="cLabel">Customer Id</span></div>
                            <div className='col-md-6'><span>{this.props.customerInfo.customerId}</span></div>
                        </div>
                        <div className="row">
                            <div className='col-md-6'><span className="cLabel">Current Balance</span></div>
                            <div className='col-md-6'><span>{this.props.customerInfo.customerBalance}</span></div>
                        </div>
                        <div className="row">
                            <div className='col-md-6'><span className="cLabel">Month Selected</span></div>
                            <div className='col-md-6'>
                                <input type='month' value={this.props.customerInfo.yearMonth} disabled/>
                            </div>
                        </div>
                    </div>
                    <div className='col-md-6'>
                        <div className='customerCategories'>
                            <h5 className='sectionHeading'>Classification</h5>
                            <ul>
                                {this.props.customerClassification.categories.map(category => <li key={category}>{category}</li>) }
                            </ul>

                        </div>
                    </div>
                </div>
                <div className='transactionSection sectionStart'>
                    <h5>Processed transactions</h5>
                    <div id='transactionTableContainer'>
                        <table className="table table-hover">
                            <thead className="transactionHeader">
                            <tr>
                                <th>Date</th>
                                <th>Description</th>
                                <th>Money in</th>
                                <th>Money out</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.props.customerClassification.transactions.map(this.renderTransaction)}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }
}

function mapStateToProps(state) {
    console.log("mapStateToProps state", state);
    return {customerClassification: state.customerClassification, customerInfo: state.customerInfo};
}

export default connect(mapStateToProps)(CustomerDetail);