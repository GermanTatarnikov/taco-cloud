create table if not exists taco_order (
    id bigint not null primary key,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists taco (
    id bigint not null primary key,
    name varchar(50) not null,
    taco_order bigint not null,
    taco_order_key bigint not null,
    created_at timestamp not null
);

create table if not exists ingredient_ref (
    ingredient varchar(4),
    taco bigint not null,
    taco_key bigint not null
);

create table if not exists ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists taco_order_tacos (
    taco_order bigint not null,
    taco bigint not null
);

alter table ingredient
    add primary key(id);

alter table taco_order_tacos
    add foreign key (taco_order) references taco_order(id);

alter table taco_order_tacos
    add foreign key (taco) references taco(id);

alter table taco
    add foreign key (taco_order) references Taco_Order(id);

alter table ingredient_ref
    add foreign key (ingredient) references Ingredient(id);