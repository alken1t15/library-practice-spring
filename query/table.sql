create table people(
  id serial8 primary key,
  LNM varchar(100) unique,
  date_born date
);
create table book(
  id serial8 primary key,
  people_id int8 references people(id) ON DELETE SET DEFAULT,
  "name" varchar(100),
  author varchar(100),
  "year" date
);

insert into people (LNM, date_born) values ('Иванов Иван Иванович','01.05.1970');
insert into people (LNM, date_born) values ('Петров Петр Петрович','05.08.1960');
insert into people (LNM, date_born) values ('Алексеев Алексей Алексеевич','05.05.1989');
insert into people (LNM, date_born) values ('Познер Владимир Владимирович','07.08.1944');
insert into people (LNM, date_born) values ('Федоров Мирон Янович','15.09.1985');