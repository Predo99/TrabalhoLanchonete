-- create schema lanchonete;
use lanchonete;

drop table funcionario;
drop table complementos;
drop table remocoes;
drop table opcoes_pedido;
drop table opcoes_cardapio;
drop table cardapio;
drop table pedido;
drop table ingredientes_opcao;
drop table opcao;
drop table ingrediente;


create table funcionario
(
    nomef VARCHAR(50) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    senha VARCHAR(20) NOT NULL,
    salario DOUBLE NOT NULL,
    gerente BOOLEAN NOT NULL,
    PRIMARY KEY (cpf)
);

create table ingrediente
(
    nomei VARCHAR(50) NOT NULL,
    quantidade DOUBLE NOT NULL,
    custo DOUBLE NOT NULL,
    PRIMARY KEY (nomei)
);

create table opcao
(
    nomeo VARCHAR(50) NOT NULL,
    preco DOUBLE NOT NULL,
    imagem LONGBLOB,
    
    PRIMARY KEY (nomeo)
);

create table cardapio
(
    codigoc INTEGER  NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (codigoc)
);

CREATE TABLE pedido 
(
    codigop INTEGER(11) NOT NULL AUTO_INCREMENT,
    embalagem VARCHAR(20) NOT NULL,
    preco DOUBLE NOT NULL,
    formaPagamento VARCHAR(20) NOT NULL,
    dataPedido DATE NOT NULL,
    numCard VARCHAR(30) DEFAULT NULL,
    PRIMARY KEY (codigop)
);

create table ingredientes_opcao
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    nomei VARCHAR(50) NOT NULL,
    nomeo VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (nomei) REFERENCES ingrediente(nomei) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (nomeo) REFERENCES opcao(nomeo) ON UPDATE CASCADE ON DELETE CASCADE
);

create table opcoes_cardapio
(
    codigoc INTEGER  NOT NULL,
    nomeo VARCHAR(50) NOT NULL,
    PRIMARY KEY (codigoc, nomeo),
    FOREIGN KEY (codigoc) REFERENCES cardapio(codigoc) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (nomeo) REFERENCES opcao(nomeo) ON UPDATE CASCADE ON DELETE CASCADE
);

create table opcoes_pedido
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    codigop INTEGER  NOT NULL,
    nomeo VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (codigop) REFERENCES pedido(codigop) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (nomeo) REFERENCES opcao(nomeo) ON UPDATE CASCADE ON DELETE CASCADE
);

create table complementos
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    indice INTEGER NOT NULL,
    codigop INTEGER  NOT NULL,
    nomeo VARCHAR(50) NOT NULL,
    nomei VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (codigop) REFERENCES pedido(codigop) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (nomeo) REFERENCES opcao(nomeo) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (nomei) REFERENCES ingrediente(nomei) ON UPDATE CASCADE ON DELETE CASCADE
);

create table remocoes
(
    id INTEGER NOT NULL AUTO_INCREMENT,
    indice INTEGER NOT NULL,
    codigop INTEGER  NOT NULL,
    nomeo VARCHAR(50) NOT NULL,
    nomei VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (codigop) REFERENCES pedido(codigop) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (nomeo) REFERENCES opcao(nomeo) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (nomei) REFERENCES ingrediente(nomei) ON UPDATE CASCADE ON DELETE CASCADE
);

insert into cardapio values();

insert into funcionario values 
('Gerente1', '12345678910', '1234', 5000, true),
('Funcionário1', '12345678911', '1234', 2000, false),
('Funcionário2', '12345678912', '1234', 1500, false);

insert into ingrediente values 
('Alface', 40, 1.5),
('Bacon', 80, 2),
('Batata Frita',95, 4.8),
('Batata Palha', 50, 1.5),
('Bife de hamburguer', 100, 6),
('Calabresa',80, 1.8),
('Cereja', 40, 0.5),
('Cheddar', 52, 3.8),
('Chocolate', 50, 5),
('Granulado', 35, 1.1),
('Lata de Refrigerante', 100, 4.7),
('Leite', 300, 3.29),
('Massa', 55,5),
('Milho',30,0.35),
('Molho', 42, 2.7),
('Ovo', 40,1),
('Ovomaltine', 55, 2.15),
('Pão de hamburguer', 150, 5),
('Pão Hot Dog', 85, 2.5),
('Pepperoni', 60,3.5),
('Queijo Ralado', 150, 2),
('Salsicha',85, 2),
('Tomate', 75,0.8);

insert into opcao values 
('Batata Frita com Molho', 6.5, null),
('Cachorro Quente', 5.5, null),
('Fritas com Cheddar e Bacon', 10, null),
('Hamburguer Simples',7.5, null),
('Milkshake', 5.5, null),
('Pizza Calabresa', 28, null),
('Pizza Pepperoni',32, null),
('X-Bacon', 10, null),
('X-EggBacon', 13.5, null),
('X-Tudo', 16.5, null),
('Refrigerante', 3, null);

SET FOREIGN_KEY_CHECKS=0;

insert into ingredientes_opcao (nomei,nomeo) values
('Alface', 'Hamburguer Simples'),
('Bife de Hamburguer', 'Hamburguer Simples'),
('Milho', 'Hamburguer Simples'),
('Pão de hamburguer', 'Hamburguer Simples'),
('Tomate', 'Hamburguer Simples'),
('Pão Hot Dog', 'Cachorro Quente'),
('Salsicha', 'Cachorro Quente'),
('Batata Palha', 'Cachorro Quente'),
('Milho', 'Cachorro Quente'),
('Tomate', 'Cachorro Quente'),
('Lata de Refrigerante', 'Refrigerante'),
('Pão de hamburguer', 'X-Bacon'),
('Bife de Hamburguer', 'X-Bacon'),
('Alface', 'X-Bacon'),
('Bacon', 'X-Bacon'),
('Batata Palha', 'X-Bacon'),
('Tomate', 'X-Bacon'),
('Massa', 'Pizza Calabresa'),
('Calabresa', 'Pizza Calabresa'),
('Molho', 'Pizza Calabresa'),
('Tomate', 'Pizza Calabresa'),
('Milho', 'Pizza Calabresa'),
('Queijo Ralado', 'Pizza Calabresa'),
('Massa', 'Pizza Pepperoni'),
('Molho', 'Pizza Pepperoni'),
('Pepperoni', 'Pizza Pepperoni'),
('Queijo Ralado', 'Pizza Pepperoni'),
('Ovo', 'Pizza Pepperoni'),
('Milho', 'Pizza Pepperoni'),
('Tomate', 'Pizza Pepperoni'),
('Pão de hamburguer', 'X-EggBacon'),
('Alface', 'X-EggBacon'),
('Bacon', 'X-EggBacon'),
('Ovo', 'X-EggBacon'),
('Batata Palha', 'X-EggBacon'),
('Tomate', 'X-EggBacon'),
('Bife de Hamburguer', 'X-EggBacon'),
('Batata Frita', 'Batata Frita com Molho'),
('Molho', 'Batata Frita com Molho'),
('Cheddar', 'Fritas com Cheddar e Bacon'),
('Batata Frita', 'Fritas com Cheddar e Bacon'),
('Bacon', 'Fritas com Cheddar e Bacon'),
('Pão de hamburguer', 'X-Tudo'),
('Bife de Hamburguer', 'X-Tudo'),
('Alface', 'X-Tudo'),
('Tomate', 'X-Tudo'),
('Ovo', 'X-Tudo'),
('Bacon', 'X-Tudo'),
('Cheddar', 'X-Tudo'),
('Queijo Ralado', 'X-Tudo'),
('Milho', 'X-Tudo'),
('Leite', 'Milkshake'),
('Chocolate', 'Milkshake'),
('Ovomaltine', 'Milkshake'),
('Cereja', 'Milkshake');

insert into opcoes_cardapio (codigoc, nomeo) values
(1, 'Batata Frita com Molho'),
(1, 'Cachorro Quente'),
(1, 'Fritas com Cheddar e Bacon'),
(1, 'Hamburguer Simples'),
(1, 'Lata de Refrigerante'),
(1, 'Milkshake'),
(1, 'Pizza Calabresa'),
(1, 'Pizza Pepperoni'),
(1, 'X-Bacon'),
(1, 'X-EggBacon'),
(1, 'X-Tudo');

SET FOREIGN_KEY_CHECKS=1;

