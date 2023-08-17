

select table_name from user_tables;

drop table Alpha2;

drop table bcolor;

drop table fcolor;

create table alpha(
 line number(2),
 col number(2),
 fg varchar2(7 char),
 bg varchar2(7 char),
 ch char(1 char)
);

select * from alpha;

select line, col, count(*)
from alpha
group by line, col;


select count(*)
 from alpha
 where line =10
 and col =5;

select count(*) from alpha;

insert into alpha values(10,10,'black','white','A');
 
 commit;
 
 
 select * from alpha order by line, col;
 
 select * from alpha order by line, col DESC;
 
 select * from alpha order by col, line;
 
 select * from alpha order by col DESC, line DESC;