DROP TABLE IF EXISTS suppliers CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS supplier_product CASCADE;
DROP TABLE IF EXISTS product CASCADE;

CREATE TABLE suppliers (
    id                BIGSERIAL NOT NULL,
    name              VARCHAR(200) not null,
    active            boolean not null DEFAULT TRUE,
    create_date       date default CURRENT_DATE,
    industry          VARCHAR(400),
    street_address_1  VARCHAR(400),
    street_address_2  VARCHAR(400),
    city              VARCHAR(100),
    state             VARCHAR(50),
    zip               VARCHAR(10),
    country           VARCHAR(30),
    subscribed        boolean not null DEFAULT FALSE
);

ALTER TABLE suppliers ADD CONSTRAINT supplier_pk PRIMARY KEY ( id );

CREATE TABLE users (

    id              BIGSERIAL NOT NULL,
    supplier_id     BIGSERIAL,
    login_name      VARCHAR(200),
    password        VARCHAR(200),
    first_name      VARCHAR(200),
    last_name       VARCHAR(200),
    email           VARCHAR(300) NOT NULL UNIQUE,
    registered_date   date default CURRENT_DATE
);

ALTER TABLE users ADD CONSTRAINT user_pk PRIMARY KEY ( id );

--CREATE TABLE subscriptions (
--    id              BIGSERIAL NOT NULL,
--    supplier_id     bigint NOT NULL,
--    date_start      date default CURRENT_DATE,
--    date_expire     date default CURRENT_DATE + interval '1 year',
--    price           numeric
--);

--ALTER TABLE subscriptions ADD CONSTRAINT subscription_pk PRIMARY KEY ( id );

--CREATE TABLE transactions (
--    id             BIGSERIAL NOT NULL,
--    customer_id    BIGINT NOT NULL,
--    supplier_id    BIGINT NOT NULL,
--    product_id     BIGINT NOT NULL,
--    transaction_date    date default CURRENT_DATE,
--    price          numeric,
--    quality        BIGINT
--);
--
--ALTER TABLE transactions ADD CONSTRAINT transaction_pk PRIMARY KEY ( id );

CREATE TABLE product (
    id             BIGSERIAL NOT NULL,
    category       VARCHAR(300),
    name           VARCHAR(300) not null unique
);

ALTER TABLE product ADD CONSTRAINT product_pk PRIMARY KEY ( id );

CREATE TABLE supplier_product (
    supplier_id      bigint NOT NULL,
    product_id       bigint NOT NULL
);


ALTER TABLE users
    ADD CONSTRAINT supplier_id_fk FOREIGN KEY ( supplier_id )
        REFERENCES suppliers ( id );
ALTER TABLE supplier_product
    ADD CONSTRAINT supplier_id_fk_sp FOREIGN KEY ( supplier_id )
        REFERENCES suppliers ( id );
ALTER TABLE supplier_product
    ADD CONSTRAINT product_id_fk_sp FOREIGN KEY ( product_id )
        REFERENCES product ( id );

--ALTER TABLE subscriptions
--    ADD CONSTRAINT supplier_id_fk FOREIGN KEY ( supplier_id )
--        REFERENCES suppliers ( id );

--ALTER TABLE transactions
--    ADD CONSTRAINT supplier_id_fk FOREIGN KEY ( supplier_id )
--        REFERENCES suppliers ( id );

--ALTER TABLE transactions
--    ADD CONSTRAINT product_id_fk FOREIGN KEY ( product_id )
--        REFERENCES products ( id );

