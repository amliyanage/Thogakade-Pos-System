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

    return new Promise((resolve, reject) => {
        $.ajax({
            url: `http://localhost:8080/customer?id=all`,
            method: "GET",
            dataType: "json", // Ensure the response is treated as JSON
            success: function (data) {
                if (!Array.isArray(data)) {
                    reject(new Error("Expected an array of customers"));
                    return;
                }

                let returnData = data.map((customer) => ({
                    custId: customer.id,
                    custName: customer.name,
                    custAddress: customer.address,
                    custSalary: customer.salary
                }));

                console.log(returnData, "=============================================temp cust=");
                resolve(returnData);
            },
            error: function (error) {
                reject(error);
            }
        });
    });
}

export function updateCustomer(index , customer){

    let customerData = {
        id :customer.custId,
        name : customer.custName,
        address : customer.custAddress,
        salary : customer.custSalary
    }

    const customerJson = JSON.stringify(customerData)

    $.ajax({
        url : "http://localhost:8080/customer",
        method : "PUT",
        data : customerJson,
        headers : {"Content-Type": "application/json"},
        success : function (res){
            console.log("Customer Updated");
        },
    })

}

export function deleteCustomer(index){

    $.ajax({
        url : `http://localhost:8080/customer?id=${index}`,
        method : "DELETE",
        success : function (res){
            console.log("Customer Deleted");
        }
    })

}

// export function getAllCustomers(){
//     console.log("=============================================getAllCustomers");
//     getAllCustomer().then((customer) =>{
//         console.log(customer,"=============================================getAllCustomers");
//         return customer;
//     })
// }