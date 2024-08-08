import { Orders } from "../db/DB.js";

export function getAllOrders() {

    return new Promise((resolve, reject) => {
        $.ajax({
            url : "http://localhost:8080/order",
            method : "GET",
            success : function(data){
                resolve(data);
            },
        })
    })
}

export function saveOrder(order){

    let sendItems = []

    order.items.forEach((item) => {
        sendItems.push({
            id : item.itemCode,
            name : item.getItems,
            qty : item.itemQty,
            price : item.itemPrice,
        })
    })

    console.log("Order==============================", order)
    let orderData = {
        id : order.orderId,
        date : order.orderDate,
        customerId : order.custId,
        items : sendItems,
        total : order.total,
        discount : order.discount,
        subTotal : order.subTotal,
        cash : order.cash,
        balance : order.balance
    }
    return new Promise((resolve, reject) => {
        $.ajax({
            url: "http://localhost:8080/order",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(orderData),
            success: function (data) {
                console.log(data);
                resolve(data);
                alert('Order Placed');
            },
            error: function (error) {
                console.log(error);
                reject(error);
            }
        });
    });
}