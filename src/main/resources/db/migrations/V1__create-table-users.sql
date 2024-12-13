CREATE TABLE users(
    id UUID PRIMARY KEY NOT NULL DEFAULT gen_random_uuid(),
    name VARCHAR(70) NOT NULL,
    email VARCHAR(40) NOT NULL,
    password VARCHAR(50) NOT NULL,
);