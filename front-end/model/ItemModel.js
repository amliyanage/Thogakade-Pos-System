import { Items } from '../db/DB.js';

export function saveItem(item) {

    let sendItem = {
        id : item.itemId,
        name : item.itemName,
        qty : item.itemQty,
        price : item.itemPrice
    }

    $.ajax({
        url : "http://localhost:8080/item",
        method : "POST",
        contentType : "application/json",
        data : JSON.stringify(sendItem),
        success : function (data){
            console.log(data);
        }
    })
}

export function getAllItems() {
    // $.ajax({
    //     url : "http://localhost:8080/item?id=all",
    //     method : "GET",
    //     success : function (data){
    //         let returnData = [];
    //         data.map((item) => {
    //             returnData.push({
    //                 itemId : item.id,
    //                 itemName : item.name,
    //                 itemQty : item.qty,
    //                 itemPrice : item.price
    //             })
    //         })
    //         return returnData;
    //     }
    // })

    return new Promise((resolve, reject) => {
        $.ajax({
            url: `http://localhost:8080/item?id=all`,
            method: "GET",
            dataType: "json", // Ensure the response is treated as JSON
            success: function (data) {
                if (!Array.isArray(data)) {
                    reject(new Error("Expected an array of customers"));
                    return;
                }

                let returnData = data.map((item) => ({
                    itemId: item.id,
                    itemName: item.name,
                    itemQty: item.qty,
                    itemPrice: item.price
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

export function deleteItem(index){
    $.ajax({
        url : `http://localhost:8080/item?id=${index}`,
        method : "DELETE",
        success : function (data){
            console.log(data);
        }
    })
}

export function updateItem(index, item){
    let sendItem = {
        id : item.itemId,
        name : item.itemName,
        qty : item.itemQty,
        price : item.itemPrice
    }

    $.ajax({
        url : `http://localhost:8080/item`,
        method : "PUT",
        contentType : "application/json",
        data : JSON.stringify(sendItem),
        success : function (data){
            console.log(data);
        }
    })
}