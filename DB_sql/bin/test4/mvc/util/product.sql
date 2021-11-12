drop table product;
create table product (
	num number(10) primary key,
	name varchar2(30),
	price number
);

drop sequence product_seq;
create sequence product_seq;
select * from product;