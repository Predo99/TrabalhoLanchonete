-- create schema lanchonete;
use lanchonete;

-- drop table funcionario;
-- drop table complementos;
-- drop table remocoes;
-- drop table opcoes_pedido;
-- drop table opcoes_cardapio;
-- drop table cardapio;
-- drop table pedido;
-- drop table ingredientes_opcao;
-- drop table opcao;
-- drop table ingrediente;

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
('Batata', 200, 3.5),
('Bife de hamburguer', 100, 7),
('Chocolate', 50, 5),
('Leite', 300, 3.29),
('Queijo', 150, 3),
('Pão de hamburguer', 150, 5),
('Tomate', 50, 4.5),
('Bacon', 100, 2),
('Ovo', 80, 2);

insert into opcao values 
('Batata frita', 3.5, null),
('Hamburguer simples',7.5, null),
('Milkshake', 5.5, null),
('Refrigerante', 3, null);

insert into ingredientes_opcao (nomei, nomeo) values
('Bife de hamburguer', 'Hamburguer simples'),
('Pão de hamburguer', 'Hamburguer simples'),
('Tomate', 'Hamburguer simples'),
('Alface', 'Hamburguer simples'),
('Queijo', 'Hamburguer simples'),
('Batata', 'Batata frita'),
('Leite', 'Milkshake'),
('Chocolate','Milkshake');

insert into opcoes_cardapio (codigoc, nomeo) values
(1, 'Batata frita'),
(1, 'Hamburguer simples'),
(1, 'Milkshake'),
(1, 'Refrigerante');

