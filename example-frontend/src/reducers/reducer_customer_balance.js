import {LOAD_CUSTOMER_DETAIL} from '../actions/index';

export default function(state=null, action) {
    switch (action.type) {
        case LOAD_CUSTOMER_DETAIL:
            let data = action.payload.data;
            return {
                customerId: data.customerId,
                yearMonth: data.yearMonth,
                customerBalance: data.customerBalance
            };
        default:
            return state;
    }
}