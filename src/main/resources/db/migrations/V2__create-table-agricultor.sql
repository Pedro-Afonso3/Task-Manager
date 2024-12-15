CREATE TABLE agricultor(
    id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    name VARCHAR(70) NOT NULL,
    telefone VARCHAR(14) NOT NULL,
    cep VARCHAR(10) NOT NULL,
    logradouro VARCHAR(100),
    complemento VARCHAR(100),
    bairro VARCHAR(100),
    localidade VARCHAR(100),
    uf VARCHAR(2),
    estado VARCHAR(100),
    regiao VARCHAR(100),
    tipoProduto VARCHAR(20) NOT NULL,
    produtos_id UUID,
    FOREIGN KEY (produtos_id) REFERENCES produtos(id),
);