import axios from 'axios';

const ROOT_URL = `http://localhost:8080`;
export const LOAD_CUSTOMER_CLASSIFICATION = 'LOAD_CUSTOMER_CLASSIFICATION';
export const LOAD_CUSTOMER_DETAIL = 'LOAD_CUSTOMER_DETAIL';

export function loadCustomerClassification(state) {
    const url = `${ROOT_URL}/customerDetail/${state.customerId}/month/${state.yearMonth}`;
    const request = axios.get(url);

    return {
        type: LOAD_CUSTOMER_CLASSIFICATION,
        payload: request
    };
}

export function loadCustomerBalance(state) {
    const url = `${ROOT_URL}/customerBalance/${state.customerId}/month/${state.yearMonth}`;
    const request = axios.get(url);

    return {
        type: LOAD_CUSTOMER_DETAIL,
        payload: request
    };
}