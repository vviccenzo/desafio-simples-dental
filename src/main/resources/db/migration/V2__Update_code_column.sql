ALTER TABLE products ADD COLUMN code_temp INTEGER;

UPDATE products
SET code_temp = CAST(REGEXP_REPLACE(code, 'PROD-', '', 'g') AS INTEGER);

ALTER TABLE products DROP COLUMN code;

ALTER TABLE products RENAME COLUMN code_temp TO code;