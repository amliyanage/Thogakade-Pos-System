import { Customers } from '../db/DB.js';

export function saveCustomer(customer) {
    let customerData = {
        id :customer.custId,
        name : customer.custName,
        address : customer.custAddress,
        salary : customer.custSalary
    }

    const customerJson = JSON.stringify(customerData)

    const http = new XMLHttpRequest()
    http.onreadystatechange = () =>{
        if (http.readyState === 4){
            if (http.status === 201){

            }
        }
    }

    http.open("POST","http://localhost:8080/customer")
    http.setRequestHeader("Content-type","application/json")
    http.send(customerJson);

}

export function getAllCustomers() {
    return Customers;
}

export function updateCustomer(index , customer){
    Customers[index] = customer;
}

export function deleteCustomer(index){
    Customers.splice(index, 1);
}