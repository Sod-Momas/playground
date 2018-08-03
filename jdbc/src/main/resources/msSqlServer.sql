create table test(
 id int identity(1,1) primary key,
 name nchar(255),
 age int
);
GO;
insert into test(name,age) values('我的名字',11);
insert into test(name,age)  values('老人名字',89);
GO;
select * from test;