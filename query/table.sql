create table people(
  id serial8 primary key,
  LNM varchar(100) unique,
  date_born int4
);
create table book(
  id serial8 primary key,
  people_id int8 references people(id) ON DELETE SET DEFAULT,
  "name" varchar(100),
  author varchar(100),
  "year" int4
);

insert into people (LNM, date_born) values ('Иванов Иван Иванович',1970);
insert into people (LNM, date_born) values ('Петров Петр Петрович',1960);
insert into people (LNM, date_born) values ('Алексеев Алексей Алексеевич',1989);
insert into people (LNM, date_born) values ('Познер Владимир Владимирович',1944);
insert into people (LNM, date_born) values ('Федоров Мирон Янович',1985);

insert into book (name, author, year) values ('Над пропастью во ржи','Джером Сэлинджер',1951);
insert into book (name, author, year) values ('День опричника','Владимир Сорокин',2006);
insert into book (name, author, year) values ('Тайные виды на гору Фудзи','Владимир Пелевин',2018);
insert into book (name, author, year) values ('Философия Java','Брюс Эккель',2018);
insert into book (name, author, year) values ('Психопатология обыденной жизни','Фрейд Зигмунд',1904);
insert into book (name, author, year) values ('Игра в бисер','Герман Гессе',1943);
insert into book (name, author, year) values ('Бытие и время','Мартин Хайдеггер',1927);