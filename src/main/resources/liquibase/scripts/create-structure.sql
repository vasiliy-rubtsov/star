-- liquibase formatted sql
-- changeset vasiliy.rubtsov:1
CREATE TABLE products(
    id SERIAL NOT NULL PRIMARY KEY,
    product_id VARCHAR(50) NOT NULL,
    product_name VARCHAR(500) NOT NULL,
    product_text TEXT
);

CREATE TABLE rules(
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    query VARCHAR(500) NOT NULL,
    negate BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE arguments(
    id SERIAL PRIMARY KEY,
    rule_id INT NOT NULL,
    position INT NOT NULL DEFAULT 0,
    value VARCHAR(500) NOT NULL
);

ALTER TABLE rules ADD CONSTRAINT fk__rule__recommendation FOREIGN KEY (product_id) REFERENCES products(id);
ALTER TABLE arguments ADD CONSTRAINT fk__argument__rule FOREIGN KEY (rule_id) REFERENCES rules(id);




