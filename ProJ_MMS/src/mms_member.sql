create table mms_member(
    id number constraint member_id_seq primary key,
    name varchar2(12) unique not null,
    addr varchar2(50) not null,
    nation varchar2(12) not null,
    email varchar2(30) not null,
    age number
    );
    
create member_id_seq
start with 0
increment by 1
minvalue 0;