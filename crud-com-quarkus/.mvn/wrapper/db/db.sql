/*Script da minha tabela no banco*/

CREAT TABELE library;

CREATE TABLE BOOKS (
	id bigserial not null primary key,
	titulo varchar(100) not null,
	email varchar(100) not null,
	age integer not null
);