CREATE TABLE pessoa (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(50),
    complemento VARCHAR(50),
    bairro VARCHAR(50),
    cep VARCHAR(50),
    cidade VARCHAR(50),
    estado VARCHAR(50),
    ativo BOOLEAN NOT NULL
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
	values ('Walter White', 'Rua de Albuquerque', '10', null, 'IDK', '38.400-121', 'Albuquerque', 'Novo México', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
	values ('Tony Soprano', 'Rua Morning', '110', 'Casa', 'NJ', '11.400-121', 'Newark', 'Nova Jersey', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
	values ('Logan Roy', 'Rua da Succession', '23', null, 'NY', '54.212-121', 'Nova Iorque', 'Nova Iorque', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
	values ('Bruce Wayne', 'Rua Sem Nome', '123', 'Mansão', 'GC', '38.400-121', 'Gotham City', 'Gotham', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
	values ('Barry Allen', 'Rua Star', '321', null, 'CC', '56.400-121', 'Central City', 'California', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
	values ('Diana Prince', 'Rua Grega', '100', null, 'Temys', '77.400-121', 'Temyscera', 'OO', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
	values ('Oliver Queen', 'Rua Cidade Estrela', '1120', 'Casa', 'Centro', '12.400-121', 'Starling City', 'SC', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
	values ('Charles Bartowski', 'Rua BM', '433', null, 'Centro', '31.400-121', 'Los Angeles', 'California', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
	values ('Hailee Steinfeld', 'Rua CP', '566', null, 'Central Park', '38.400-001', 'Nova Iorque', 'Nova Iorque', true);
INSERT INTO pessoa (nome, logradouro, numero, complemento, bairro, cep, cidade, estado, ativo)
	values ('Quentin Tarantino', 'Rua HC', '1233', 'Mansão', 'Bairro Hollywood', '99.400-121', 'Los Angeles', 'California', true);