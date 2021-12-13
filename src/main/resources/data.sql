insert into categoria (nome, limite_mensal) values ('Alimentação', 0);
insert into categoria (nome, limite_mensal) values ('Vestuário', 0);
insert into categoria (nome, limite_mensal) values ('Lazer', 200.0);
insert into categoria (nome, limite_mensal) values ('Eletronicos', 0);
insert into categoria (nome, limite_mensal) values ('Diversos', 0);
insert into categoria (nome, limite_mensal) values ('Salario', 0);
insert into lancamento (data_criacao, descricao, tipo_lancamento, valor, categoria_fk) values  (CURDATE() - INTERVAL 7 DAY, 'Recebimento Salario', 'R', 1500.0, 6);
insert into lancamento (data_criacao, descricao, tipo_lancamento, valor, categoria_fk) values  (CURDATE() - INTERVAL 7 DAY, 'Recebimento Salario', 'R', 1500.0, 6);
insert into lancamento (data_criacao, descricao, tipo_lancamento, valor, categoria_fk) values  (CURDATE() - INTERVAL 3 DAY, 'Cinema', 'D', 40.0, 3);
insert into lancamento (data_criacao, descricao, tipo_lancamento, valor, categoria_fk) values  (CURDATE(), 'Restaurante X', 'D', 52.90, 1);
insert into lancamento (data_criacao, descricao, tipo_lancamento, valor, categoria_fk) values  (CURDATE() - INTERVAL 5 DAY, 'Restaurante Y', 'D', 15.00, 1);
insert into lancamento (data_criacao, descricao, tipo_lancamento, valor, categoria_fk) values  (CURDATE() - INTERVAL 8 DAY, 'Restaurante Y', 'D', 15.00, 1);
insert into lancamento (data_criacao, descricao, tipo_lancamento, valor, categoria_fk) values  (CURDATE(), 'Roupa', 'D', 199.90, 2);
insert into lancamento (data_criacao, descricao, tipo_lancamento, valor, categoria_fk) values  (CURDATE(), 'Compra Tablet', 'D', 299.90, 4);
insert into lancamento (data_criacao, descricao, tipo_lancamento, valor, categoria_fk) values  (CURDATE(), 'Conserto carro', 'D', 500.0, 5);

