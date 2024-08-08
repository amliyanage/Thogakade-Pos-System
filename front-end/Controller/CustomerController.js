import { saveCustomer } from '../model/CustomerModel.js';
import { getAllCustomers } from '../model/CustomerModel.js';
import { updateCustomer } from '../model/CustomerModel.js';
import { deleteCustomer } from '../model/CustomerModel.js';

$(document).ready(function(){
    refresh();
});

document.querySelector('#CustomerManage #customerForm').addEventListener('submit', function(event){
    event.preventDefault();
});

var custId ;
var custName;
var custAddress;
var custSalary;

$('#CustomerManage .saveBtn').click(function(){

    custId = $('#CustomerManage .custId').val();
    custName = $('#CustomerManage .custName').val();
    custAddress = $('#CustomerManage .custAddress').val();
    custSalary = $('#CustomerManage .custSalary').val();

    let customer = {
        custId : custId,
        custName : custName,
        custAddress : custAddress,
        custSalary : custSalary
    }

    let validResult = validate(customer);

    if(validResult){
        saveCustomer(customer);
        alert('Customer Saved');
        refresh();
    }

});


async function validate(customer) {

    let valid = true;

    if ((/^C0[0-9]+$/).test(customer.custId)) {
        $('#CustomerManage .invalidCustId').text('');
        valid = true;
    } else {
        $('#CustomerManage .invalidCustId').text('Invalid Customer Id');
        valid = false;
    }

    if ((/^(?:[A-Z][a-z]*)(?: [A-Z][a-z]*)*$/).test(customer.custName)) {
        $('#CustomerManage .invalidCustName').text('');

        if (valid) {
            valid = true;
        }
    } else {
        $('#CustomerManage .invalidCustName').text('Invalid Customer Name');
        valid = false;
    }

    if ((/^[A-Z][a-z, ]+$/).test(customer.custAddress)) {
        $('#CustomerManage .invalidCustAddress').text('');

        if (valid) {
            valid = true;
        }
    } else {
        $('#CustomerManage .invalidCustAddress').text('Invalid Customer Address');
        valid = false;
    }

    if (customer.custSalary != null && customer.custSalary > 0) {
        $('#CustomerManage .invalidCustSalary').text('');
        if (valid) {
            valid = true;
        }
    } else {
        $('#CustomerManage .invalidCustSalary').text('Invalid Customer Salary');
        valid = false;
    }

    let customers = await getAllCustomers();
    for (let i = 0; i < customers.length; i++) {
        if (customers[i].custId === customer.custId) {
            $('#CustomerManage .invalidCustId').text('Customer Id Already Exists');
            valid = false;
        }
    }

    return valid;
}

function loadTable(customer){
    console.log(customer,"=============#$$$$$$$$$========================table Load");
    $('#CustomerManage .tableRow').append(
        '<tr> ' +
            '<td>' + customer.custId + '</td>' +
            '<td>' + customer.custName + '</td>' +
            '<td>' + customer.custAddress + '</td>' +
            '<td>' + customer.custSalary + '</td>' +
        '</tr>' 
    );
}

function extractNumber(id) {
    var match = id.match(/C0(\d+)/);
    if (match && match.length > 1) {
        return parseInt(match[1]);
    }
    return null;
}

async function createCustomerId() {
    let customers = await getAllCustomers();
    console.log(customers,"Enna yanna apirh");
    if (!customers || customers.length === 0) {
        $('#CustomerManage .custId').val("C01")
    } else {
        let lastCustomer = customers[customers.length - 1];
        console.log(lastCustomer,"_-------------------------last");
        let id = lastCustomer && lastCustomer.custId ? lastCustomer.custId : 'C00';
        console.log(id);
        let number = extractNumber(id);
        number++;
        console.log(number)
        const nextId = 'C0' + number;
        console.log(nextId);
        $('#CustomerManage .custId').val(nextId)
    }
}

async function refresh() {
    createCustomerId()
    $('#CustomerManage .custName').val('');
    $('#CustomerManage .custAddress').val('');
    $('#CustomerManage .custSalary').val('');
    $('#CustomerManage .invalidCustId').text('');
    $('#CustomerManage .invalidCustName').text('');
    $('#CustomerManage .invalidCustAddress').text('');
    $('.counts .customers h2').text(await getAllCustomers().length);
    reloadTable();
    // loadCustomers()
}

$('#CustomerManage .cleatBtn').click(function(){
    refresh();
});

$('#CustomerManage .searchBtn').click(function(){
    let customer = searchCustomer($('#CustomerManage .custId').val());
});

async function searchCustomer(id) {
    try {
        const customers = await getAllCustomers();
        let customer = customers.find(c => c.custId === id);
        console.log(customer, " =================")
        if(customer){
            $('#CustomerManage .custName').val(customer.custName);
            $('#CustomerManage .custAddress').val(customer.custAddress);
            $('#CustomerManage .custSalary').val(customer.custSalary);
        }
        else{
            alert('Customer Not Found');
        }
        return customer;
    } catch (error) {
        console.error(error)
        return null;
    }
}

$('#CustomerManage .updateBtn').click(async function () {

    let UpdateCustomer = {
        custId: 'C00',
        custName: $('#CustomerManage .custName').val(),
        custAddress: $('#CustomerManage .custAddress').val(),
        custSalary: $('#CustomerManage .custSalary').val()
    }

    let validResult = validate(UpdateCustomer);

    UpdateCustomer.custId = $('#CustomerManage .custId').val();

    if (validResult) {
        let customers = await getAllCustomers();
        let index = customers.findIndex(c => c.custId === UpdateCustomer.custId);
        updateCustomer(index, UpdateCustomer);
        alert('Customer Updated');
        refresh();
    }

});

async function reloadTable() {
    let customers = await getAllCustomers();
    $('#CustomerManage .tableRow').empty()
    console.log(customers, "=====================================================table Load");
    // customers.forEach(c => {
    //     loadTable(c);
    // });
    getAllCustomers().then((customer) => {
        customer.forEach((customer) => {
            loadTable(customer);
        })
    }).catch(
        (error) => {
            console.log(error);
        }
    )
}

$('#CustomerManage .removeBtn').click(async function () {
    let customers = await getAllCustomers();
    let id = $('#CustomerManage .custId').val();

    let index = customers.findIndex(c => c.custId === id);
    if (index >= 0) {
        deleteCustomer(id);
        alert('Customer Deleted');
        refresh();
    } else {
        alert('Customer Not Found');
    }
});

$('#CustomerManage .tableRow').on('click', 'tr', function(){
    let id = $(this).children('td:eq(0)').text();
    let name = $(this).children('td:eq(1)').text();
    let qty = $(this).children('td:eq(2)').text();
    let price = $(this).children('td:eq(3)').text();

    $('#CustomerManage .custId').val(id);
    $('#CustomerManage .custName').val(name);
    $('#CustomerManage .custAddress').val(qty);
    $('#CustomerManage .custSalary').val(price);
});

// export function loadCustomers(){
//     getAllCustomers().then((customer) =>{
//         console.log(customer,"=============================================getAllCustomers");
//         customer.map((customer) => {
//             loadTable(customer);
//         });
//     })
// }

