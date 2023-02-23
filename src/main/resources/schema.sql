create TABLE if not exists INGREDIENT (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create TABLE if not exists BURGER (
    id identity,
    name varchar(50) not null,
    createdAt timestamp not null
);

create TABLE if not exists BURGER_INGREDIENTS (
    burger bigint not null,
    ingredient varchar(4) not null
);

alter table BURGER_INGREDIENTS add foreign key (burger) references BURGER(id);
alter table BURGER_INGREDIENTS add foreign key (ingredient) references INGREDIENT(id);

create table if not exists BURGER_ORDER (
    id identity,
    deliveryName varchar(50) not null,
    deliveryStreet varchar(50) not null,
    deliveryCity varchar(50) not null,
    deliveryState varchar(2) not null,
    deliveryZip varchar(10) not null,
    ccNumber varchar(16) not null,
    ccExpiration varchar(5) not null,
    ccCVV varchar(3) not null,
    placedAt timestamp not null
);

create table if not exists BURGER_ORDER_BURGERS (
    burgerOrder bigint not null,
    burger bigint not null
);

alter table BURGER_ORDER_BURGERS add foreign key (burgerOrder) references BURGER_ORDER(id);
alter table BURGER_ORDER_BURGERS add foreign key (burger) references BURGER(id);
