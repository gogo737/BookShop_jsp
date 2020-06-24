CREATE TABLE member14(
id VARCHAR2(50)  NOT NULL PRIMARY KEY,
passwd VARCHAR2(16) NOT NULL,
name VARCHAR2(10) NOT NULL,
reg_date TIMESTAMP NOT NULL,
address VARCHAR2(100) NOT NULL,
tel VARCHAR2(20) NOT NULL
);

insert into member14(id, passwd,name,reg_date, address, tel) 
values
('ksh','1234','±è¼ºÈÆ',current_timestamp,'´ë±¸±¤¿ª½Ã µ¿±¸','010-1111-1111');

CREATE TABLE book(
book_id NUMBER(10)  NOT NULL PRIMARY KEY,  --auto_increment
book_kind VARCHAR2(3) NOT NULL,
book_title VARCHAR2(100) NOT NULL,
book_price NUMBER(10) NOT NULL,
book_count 	NUMBER(5) NOT NULL,
author VARCHAR2(40) NOT NULL,
publishing_com VARCHAR2(30) NOT NULL,
publishing_date VARCHAR2(15) NOT NULL,
book_image VARCHAR2(100) default 'nothing.jpg',
book_content LONG NOT NULL,
discount_rate NUMBER(3) default 10, 
reg_date TIMESTAMP NOT NULL
);

alter table book modify book_image VARCHAR2(100);

insert into book
(book_id, book_kind,book_title, book_price,
book_count,author,publishing_com,publishing_date,
book_image, book_content,discount_rate,reg_date
) 
values
('1','1','1','1','1','1','1','1','1','1','1',current_timestamp);

insert into member14(id, passwd,name,reg_date, address, tel) 
values
('kkk@naver.com','1111','±è¼ºÈÆ',current_timestamp,'´ë±¸±¤¿ª½Ã µ¿±¸','010-1111-1111');



CREATE TABLE manager(
managerId VARCHAR2(50)  NOT NULL PRIMARY KEY,
managerpasswd VARCHAR2(16) NOT NULL
);

insert into manager(managerId, managerpasswd) 
values
('admin','1234');


CREATE TABLE bank(
account VARCHAR2(30)  NOT NULL,
bank VARCHAR2(20) NOT NULL,
name VARCHAR2(10) NOT NULL
);


insert into bank values ('810301-00-063043','±¹¹ÎÀºÇà','±è¼ºÈÆ');

create table cart(
cart_id NUMBER(10)  NOT NULL PRIMARY KEY,  --auto_increment
buyer VARCHAR2(50) NOT NULL,
book_id NUMBER(10) NOT NULL,
book_title VARCHAR2(100) NOT NULL,
buy_price 	NUMBER(10) NOT NULL,
buy_count NUMBER(3) NOT NULL,
book_image VARCHAR2(100) default 'nothing.jpg'
);

alter table cart modify book_image VARCHAR2(100);

create table buy(
buy_id NUMBER(19) NOT NULL,
buyer VARCHAR2(50) NOT NULL,
book_id VARCHAR2(12) NOT NULL,
book_title VARCHAR2(100) NOT NULL,
buy_price NUMBER(10) NOT NULL,
buy_count NUMBER(3) NOT NULL,
book_image VARCHAR2(100) default 'nothing.jpg',
buy_date TIMESTAMP NOT NULL,
account VARCHAR2(50) NOT NULL,
deliveryName VARCHAR2(10) NOT NULL,
deliveryTel VARCHAR2(20) NOT NULL, 
deliveryAddress VARCHAR2(100) NOT NULL,
sanction VARCHAR2(20) default '»óÇ° ÁØºñÁß'
);

alter table buy modify book_image VARCHAR2(100);

SELECT * FROM
(SELECT ROWNUM rnum, B.* FROM
( SELECT * FROM BOOK ORDER BY reg_date desc) B )
WHERE rnum >= ? and rnum <= ? ;


SELECT * FROM BOOK ORDER BY reg_date desc;

 
SELECT * FROM
(SELECT ROWNUM rnum, B.* FROM 
( SELECT * FROM BOOK WHERE BOOK_KIND=100 ORDER BY reg_date desc) B )
WHERE rnum >= 1 and rnum <= 4; 

SELECT * FROM
(SELECT ROWNUM rnum, B.* FROM BOOK B ORDER BY reg_date desc )
WHERE rnum >= 1 and rnum <= 4; 
 
select * from member14;
select * from book;
select * from manager;
select * from bank;
select * from cart;
TRUNCATE table buy;
select * from buy;