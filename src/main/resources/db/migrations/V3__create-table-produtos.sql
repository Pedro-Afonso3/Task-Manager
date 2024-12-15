CREATE TABLE produtos(
    id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
     nome VARCHAR(100) NOT NULL,
     preco FLOAT NOT NULL,
     quantidade INTEGER NOT NULL
);