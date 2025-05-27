-- liquibase formatted sql

-- changeset Vu:1748339928314-1
CREATE TABLE brands
(
    id         INT AUTO_INCREMENT NOT NULL,
    slug       VARCHAR(255) NULL,
    is_active  BIT(1) NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT pk_brands PRIMARY KEY (id)
);

-- changeset Vu:1748339928314-2
CREATE TABLE categories
(
    id            INT AUTO_INCREMENT NOT NULL,
    parent_id     INT NULL,
    slug          VARCHAR(255) NULL,
    position      INT NULL,
    is_searchable BIT(1) NULL,
    is_active     BIT(1) NULL,
    created_at    datetime NULL,
    updated_at    datetime NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

-- changeset Vu:1748339928314-3
CREATE TABLE invalidated_token
(
    id          VARCHAR(255) NOT NULL,
    expiry_time datetime NULL,
    CONSTRAINT pk_invalidated_token PRIMARY KEY (id)
);

-- changeset Vu:1748339928314-4
CREATE TABLE option_values
(
    id         INT AUTO_INCREMENT NOT NULL,
    option_id  INT NULL,
    price      DECIMAL(18, 4) NULL,
    price_type VARCHAR(255) NULL,
    position   INT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT pk_option_values PRIMARY KEY (id)
);

-- changeset Vu:1748339928314-5
CREATE TABLE options
(
    id          INT AUTO_INCREMENT NOT NULL,
    type        VARCHAR(255) NULL,
    is_required BIT(1) NULL,
    is_global   BIT(1) NULL,
    position    INT NULL,
    deleted_at  datetime NULL,
    created_at  datetime NULL,
    updated_at  datetime NULL,
    CONSTRAINT pk_options PRIMARY KEY (id)
);

-- changeset Vu:1748339928314-6
CREATE TABLE product_categories
(
    category_id INT NOT NULL,
    product_id  INT NOT NULL
);

-- changeset Vu:1748339928314-7
CREATE TABLE product_options
(
    option_id  INT NOT NULL,
    product_id INT NOT NULL
);

-- changeset Vu:1748339928314-8
CREATE TABLE product_variants
(
    id                  INT AUTO_INCREMENT NOT NULL,
    uid                 VARCHAR(255) NULL,
    uids                VARCHAR(255) NULL,
    product_id          INT NULL,
    name                VARCHAR(255) NULL,
    price               DECIMAL(18, 4) NULL,
    special_price       DECIMAL(18, 4) NULL,
    special_price_type  VARCHAR(255) NULL,
    special_price_start datetime NULL,
    special_price_end   datetime NULL,
    selling_price       DECIMAL(18, 4) NULL,
    sku                 VARCHAR(255) NULL,
    manage_stock        BIT(1) NULL,
    qty                 INT NULL,
    in_stock            BIT(1) NULL,
    is_default          BIT(1) NULL,
    is_active           BIT(1) NULL,
    position            INT NULL,
    deleted_at          datetime NULL,
    created_at          datetime NULL,
    updated_at          datetime NULL,
    CONSTRAINT pk_product_variants PRIMARY KEY (id)
);

-- changeset Vu:1748339928314-9
CREATE TABLE product_variations
(
    product_id   INT NOT NULL,
    variation_id INT NOT NULL
);

-- changeset Vu:1748339928314-10
CREATE TABLE products
(
    id                  INT AUTO_INCREMENT NOT NULL,
    brand_id            INT NULL,
    tax_class_id        INT NULL,
    slug                VARCHAR(255) NULL,
    price               DECIMAL(18, 4) NULL,
    special_price       DECIMAL(18, 4) NULL,
    special_price_type  VARCHAR(255) NULL,
    special_price_start datetime NULL,
    special_price_end   datetime NULL,
    selling_price       DECIMAL(18, 4) NULL,
    sku                 VARCHAR(255) NULL,
    manage_stock        BIT(1) NULL,
    qty                 INT NULL,
    in_stock            BIT(1) NULL,
    viewed              INT NULL,
    is_active           BIT(1) NULL,
    new_from            datetime NULL,
    new_to              datetime NULL,
    deleted_at          datetime NULL,
    created_at          datetime NULL,
    updated_at          datetime NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

-- changeset Vu:1748339928314-11
CREATE TABLE user
(
    id         INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(50) NULL,
    last_name  VARCHAR(50) NULL,
    email      VARCHAR(100) NULL,
    phone      VARCHAR(20) NULL,
    password   VARCHAR(255) NOT NULL,
    enabled    BIT(1)       NOT NULL,
    `role`     VARCHAR(50) NULL,
    last_login datetime NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

-- changeset Vu:1748339928314-12
CREATE TABLE variation_values
(
    id           INT AUTO_INCREMENT NOT NULL,
    uid          VARCHAR(255) NULL,
    variation_id INT NULL,
    value        VARCHAR(255) NULL,
    position     INT NULL,
    created_at   datetime NULL,
    updated_at   datetime NULL,
    CONSTRAINT pk_variation_values PRIMARY KEY (id)
);

-- changeset Vu:1748339928314-13
CREATE TABLE variations
(
    id         INT AUTO_INCREMENT NOT NULL,
    uid        VARCHAR(255) NULL,
    type       VARCHAR(255) NULL,
    is_global  BIT(1) NULL,
    position   INT NULL,
    deleted_at datetime NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT pk_variations PRIMARY KEY (id)
);

-- changeset Vu:1748339928314-14
ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);

-- changeset Vu:1748339928314-15
ALTER TABLE categories
    ADD CONSTRAINT FK_CATEGORIES_ON_PARENT FOREIGN KEY (parent_id) REFERENCES categories (id);

-- changeset Vu:1748339928314-16
ALTER TABLE option_values
    ADD CONSTRAINT FK_OPTION_VALUES_ON_OPTION FOREIGN KEY (option_id) REFERENCES options (id);

-- changeset Vu:1748339928314-17
ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_BRAND FOREIGN KEY (brand_id) REFERENCES brands (id);

-- changeset Vu:1748339928314-18
ALTER TABLE product_variants
    ADD CONSTRAINT FK_PRODUCT_VARIANTS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

-- changeset Vu:1748339928314-19
ALTER TABLE variation_values
    ADD CONSTRAINT FK_VARIATION_VALUES_ON_VARIATION FOREIGN KEY (variation_id) REFERENCES variations (id);

-- changeset Vu:1748339928314-20
ALTER TABLE product_categories
    ADD CONSTRAINT fk_procat_on_category FOREIGN KEY (category_id) REFERENCES categories (id);

-- changeset Vu:1748339928314-21
ALTER TABLE product_categories
    ADD CONSTRAINT fk_procat_on_product FOREIGN KEY (product_id) REFERENCES products (id);

-- changeset Vu:1748339928314-22
ALTER TABLE product_options
    ADD CONSTRAINT fk_proopt_on_option FOREIGN KEY (option_id) REFERENCES options (id);

-- changeset Vu:1748339928314-23
ALTER TABLE product_options
    ADD CONSTRAINT fk_proopt_on_product FOREIGN KEY (product_id) REFERENCES products (id);

-- changeset Vu:1748339928314-24
ALTER TABLE product_variations
    ADD CONSTRAINT fk_provar_on_product FOREIGN KEY (product_id) REFERENCES products (id);

-- changeset Vu:1748339928314-25
ALTER TABLE product_variations
    ADD CONSTRAINT fk_provar_on_variation FOREIGN KEY (variation_id) REFERENCES variations (id);

