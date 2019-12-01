create schema lanchonete;

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

create table pedido
(
    codigop INTEGER NOT NULL AUTO_INCREMENT,
    embalagem VARCHAR(20) NOT NULL,
    preco DOUBLE NOT NULL,
    formaPagamento VARCHAR(20) NOT NULL,
    dataPedido DATE NOT NULL,
    
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
    id INTEGER NOT NULL AUTO_INCREMENT,
    codigoc INTEGER  NOT NULL,
    nomeo VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
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

insert into funcionario values 
('Gerente1', '12345678910', '1234', 5000, true),
('Funcionário1', '12345678911', '1234', 2000, false),
('Funcionário2', '12345678912', '1234', 1500, false);

insert into cardapio values();