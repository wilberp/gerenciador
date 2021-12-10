insert into categoria (nome, limite_mensal) values ('Alimentação', 0);
insert into categoria (nome, limite_mensal) values ('Vestuário', 0);
insert into categoria (nome, limite_mensal) values ('Lazer', 200.0);
insert into categoria (nome, limite_mensal) values ('Eletronicos', 0);
insert into categoria (nome, limite_mensal) values ('Diversos', 0);
insert into categoria (nome, limite_mensal) values ('Salario', 0);

INSERT INTO LANCAMENTO (DATA_CRIACAO, DESCRICAO, TIPO_LANCAMENTO, VALOR, CATEGORIA_FK) VALUES ('2021-12-08', 'Recebimento Salario', 'R', 1500.0, 6);
INSERT INTO LANCAMENTO (DATA_CRIACAO, DESCRICAO, TIPO_LANCAMENTO, VALOR, CATEGORIA_FK) VALUES (CURRENT_TIMESTAMP, 'Cinema', 'D', 40.0, 3);
INSERT INTO LANCAMENTO (DATA_CRIACAO, DESCRICAO, TIPO_LANCAMENTO, VALOR, CATEGORIA_FK) VALUES (CURRENT_TIMESTAMP, 'Restaurante X', 'D', 52.90, 1);
INSERT INTO LANCAMENTO (DATA_CRIACAO, DESCRICAO, TIPO_LANCAMENTO, VALOR, CATEGORIA_FK) VALUES ('2021-12-01', 'Restaurante Y', 'D', 15.00, 1);
INSERT INTO LANCAMENTO (DATA_CRIACAO, DESCRICAO, TIPO_LANCAMENTO, VALOR, CATEGORIA_FK) VALUES ('2021-11-30', 'Restaurante Y', 'D', 15.00, 1);
INSERT INTO LANCAMENTO (DATA_CRIACAO, DESCRICAO, TIPO_LANCAMENTO, VALOR, CATEGORIA_FK) VALUES (CURRENT_TIMESTAMP, 'Roupa', 'D', 199.90, 2);



