import {LOAD_CUSTOMER_CLASSIFICATION} from '../actions/index';

export default function(state=null, action) {
    switch (action.type) {
        case LOAD_CUSTOMER_CLASSIFICATION:
            const customerClassification = {
                categories: action.payload.data.categories,
                transactions: action.payload.data.processedTransactions
            };
            if(!state) {state = {}}
            state = customerClassification;
            return state;
        default:
            return state;
    }
}