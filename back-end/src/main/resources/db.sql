

create table customer
(
    cust_id      varchar(50) not null
        primary key,
    cust_name    varchar(50) not null,
    cust_address varchar(50) not null,
    cust_salary  double      not null
);

create table item
(
    item_id    varchar(20) not null
        primary key,
    item_name  varchar(50) not null,
    item_qty   int         not null,
    item_price double      not null
);

create table `order`
(
    id          varchar(60) not null
        primary key,
    date        varchar(60) not null,
    customer_id varchar(60) not null,
    total       double      not null,
    discount    varchar(20) not null,
    sub_total   double      not null,
    cash        double      not null,
    balance     double      not null,
    constraint order_customer_cust_id_fk
        foreign key (customer_id) references customer (cust_id)
);

create table order_item_detail
(
    order_id varchar(60) null,
    item_id  varchar(60) null,
    qty      int         not null,
    constraint order_item_detail_item_item_id_fk
        foreign key (item_id) references item (item_id),
    constraint order_item_detail_order_id_fk
        foreign key (order_id) references `order` (id)
);
