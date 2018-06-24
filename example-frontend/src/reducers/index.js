import { combineReducers } from 'redux';
import CustomerClassification from './reducer_customer_classification'
import CustomerDetail from './reducer_customer_balance'

const rootReducer = combineReducers({
    customerClassification: CustomerClassification,
    customerInfo: CustomerDetail
});

export default rootReducer;
