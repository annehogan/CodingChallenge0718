insert into Account (id, account_number, pin, balance, overdraft_allowance)
values(10001, 123456789,1234,800,200);

insert into Account (id, account_number, pin, balance, overdraft_allowance)
values(10002, 987654321,4321,1230,150);

-- The ATM should initialize with €2000 made up of 20 x €50s, 30 x €20s, 30 x €10s and 20 x €5s
insert into note_holder (id, note_value, note_quantity)
values(10001, 50, 20);
insert into note_holder (id, note_value, note_quantity)
values(10002, 20, 30);
insert into note_holder (id, note_value, note_quantity)
values(10003, 10, 30);
insert into note_holder (id, note_value, note_quantity)
values(10004, 5, 20);