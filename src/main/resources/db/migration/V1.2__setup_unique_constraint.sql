ALTER TABLE users
ADD CONSTRAINT UQ_login_name UNIQUE(login_name);

ALTER TABLE users
ADD CONSTRAINT UQ_email UNIQUE(email);

ALTER TABLE product
ADD CONSTRAINT UQ_name UNIQUE(name);

ALTER TABLE supplier_product
ADD CONSTRAINT UQ_supplier_product_id UNIQUE(supplier_id, product_id);