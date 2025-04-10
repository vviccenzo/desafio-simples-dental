CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

INSERT INTO users (name, email, password, role) VALUES
('Administrador', 'contato@simplesdental.com', 'KMbT%5wT*R!46i@@YHqx', 'ADMIN');

ALTER TABLE products
ADD COLUMN user_id BIGINT;

ALTER TABLE products
ADD CONSTRAINT fk_products_user
FOREIGN KEY (user_id) REFERENCES users(id);
