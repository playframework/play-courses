Drop table if exists products;

CREATE TABLE products (
    id SERIAL CONSTRAINT PK_PRODUCTS PRIMARY KEY,
    ean BIGINT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    CONSTRAINT UNQ_EAN UNIQUE(ean),
    CONSTRAINT UNQ_NAME UNIQUE(name)
)
