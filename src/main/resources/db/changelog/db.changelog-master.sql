-- liquibase formatted sql

-- changeset Vu:1748796327079-1
CREATE TABLE brands
(
    id         INT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255) NOT NULL,
    is_active  BIT(1)       NOT NULL,
    file_logo  VARCHAR(255) NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT pk_brands PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-2
CREATE TABLE categories
(
    id         INT AUTO_INCREMENT NOT NULL,
    parent_id  INT NULL,
    name       VARCHAR(255) NOT NULL,
    position   INT NULL,
    is_active  BIT(1)       NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT pk_categories PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-3
CREATE TABLE entity_files
(
    id          INT AUTO_INCREMENT NOT NULL,
    file_id     INT          NOT NULL,
    entity_id   INT          NOT NULL,
    entity_type VARCHAR(255) NOT NULL,
    zone        VARCHAR(255) NOT NULL,
    created_at  datetime NULL,
    updated_at  datetime NULL,
    CONSTRAINT pk_entity_files PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-4
CREATE TABLE files
(
    id         INT AUTO_INCREMENT NOT NULL,
    user_id    INT          NOT NULL,
    filename   VARCHAR(255) NOT NULL,
    disk       VARCHAR(255) NOT NULL,
    `path`     VARCHAR(255) NOT NULL,
    extension  VARCHAR(255) NOT NULL,
    mime       VARCHAR(255) NOT NULL,
    size       VARCHAR(255) NOT NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT pk_files PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-5
CREATE TABLE invalidated_token
(
    id         INT AUTO_INCREMENT NOT NULL,
    email      VARCHAR(100) NULL,
    token      VARCHAR(255) NULL,
    `role`     VARCHAR(50) NULL,
    created_at datetime NULL,
    expires_at datetime NULL,
    acceped_at datetime NULL,
    CONSTRAINT pk_invalidated_token PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-6
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

-- changeset Vu:1748796327079-7
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

-- changeset Vu:1748796327079-8
CREATE TABLE order_product_variation_values
(
    id                         INT AUTO_INCREMENT NOT NULL,
    order_product_variation_id INT NOT NULL,
    variation_value_id         INT NOT NULL,
    CONSTRAINT pk_order_product_variation_values PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-9
CREATE TABLE order_product_variations
(
    id               INT AUTO_INCREMENT NOT NULL,
    order_product_id INT          NOT NULL,
    variation_id     INT          NOT NULL,
    type             VARCHAR(255) NOT NULL,
    value            VARCHAR(255) NOT NULL,
    updated_at       datetime NULL,
    created_at       datetime NULL,
    CONSTRAINT pk_order_product_variations PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-10
CREATE TABLE order_products
(
    id                 INT AUTO_INCREMENT NOT NULL,
    order_id           INT            NOT NULL,
    product_id         INT            NOT NULL,
    product_variant_id INT NULL,
    unit_price         DECIMAL(18, 4) NOT NULL,
    quy                INT            NOT NULL,
    line_total         DECIMAL(18, 4) NOT NULL,
    CONSTRAINT pk_order_products PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-11
CREATE TABLE orders
(
    id                  INT AUTO_INCREMENT NOT NULL,
    customer_amail      VARCHAR(100) NULL,
    customer_phone      VARCHAR(20) NULL,
    customer_first_name VARCHAR(50) NULL,
    customer_last_name  VARCHAR(50) NULL,
    billing_first_name  VARCHAR(255) NULL,
    billing_last_name   VARCHAR(255) NULL,
    billing_address1    VARCHAR(255) NULL,
    billing_address2    VARCHAR(255) NULL,
    billing_city        VARCHAR(255) NULL,
    billing_state       VARCHAR(255) NULL,
    billing_zip         VARCHAR(255) NULL,
    billing_country     VARCHAR(255) NULL,
    shipping_first_name VARCHAR(255) NULL,
    shipping_last_name  VARCHAR(255) NULL,
    shipping_address_1  VARCHAR(255) NULL,
    shipping_address_2  VARCHAR(255) NULL,
    shipping_city       VARCHAR(255) NULL,
    shipping_state      VARCHAR(255) NULL,
    shipping_zip        VARCHAR(255) NULL,
    shipping_country    VARCHAR(255) NULL,
    sub_total           DECIMAL(18, 4) NULL,
    shipping_method     VARCHAR(255) NULL,
    shipping_cost       DECIMAL(18, 4) NULL,
    coupon_id           BIGINT NULL,
    discount            DECIMAL(18, 4) NULL,
    total               DECIMAL(18, 4) NULL,
    payment_method      VARCHAR(255) NULL,
    currency            VARCHAR(255) NULL,
    currency_rate       DECIMAL(18, 4) NULL,
    locale              VARCHAR(255) NULL,
    status              VARCHAR(255) NULL,
    note                TEXT NULL,
    deleted             BIT(1) NULL,
    created_at          datetime NULL,
    updated_at          datetime NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-12
CREATE TABLE product_categories
(
    category_id INT NOT NULL,
    product_id  INT NOT NULL
);

-- changeset Vu:1748796327079-13
CREATE TABLE product_options
(
    option_id  INT NOT NULL,
    product_id INT NOT NULL
);

-- changeset Vu:1748796327079-14
CREATE TABLE product_variants
(
    id                  INT AUTO_INCREMENT NOT NULL,
    name                VARCHAR(255) NOT NULL,
    product_id          INT NULL,
    price               DECIMAL(18, 4) NULL,
    special_price       DECIMAL(18, 4) NULL,
    special_price_type  INT NULL,
    special_price_start datetime NULL,
    special_price_end   datetime NULL,
    selling_price       DECIMAL(18, 4) NULL,
    sku                 VARCHAR(255) NULL,
    manage_stock        BIT(1) NULL,
    qty                 INT NULL,
    in_stock            BIT(1) NULL,
    is_active           BIT(1) NULL,
    position            INT NULL,
    deleted_at          datetime NULL,
    created_at          datetime NULL,
    updated_at          datetime NULL,
    CONSTRAINT pk_product_variants PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-15
CREATE TABLE product_variations
(
    product_id   INT NOT NULL,
    variation_id INT NOT NULL
);

-- changeset Vu:1748796327079-16
CREATE TABLE products
(
    id                  INT AUTO_INCREMENT NOT NULL,
    thumbnail           VARCHAR(255) NULL,
    brand_id            INT NULL,
    name                VARCHAR(255)   NOT NULL,
    `description`       LONGTEXT NULL,
    short_description   TEXT NULL,
    price               DECIMAL(18, 4) NOT NULL,
    special_price       DECIMAL(18, 4) NULL,
    special_price_type  INT NULL,
    special_price_start datetime NULL,
    special_price_end   datetime NULL,
    selling_price       DECIMAL(18, 4) NULL,
    sku                 VARCHAR(255) NULL,
    manage_stock        BIT(1) NULL,
    qty                 INT NULL,
    in_stock            BIT(1) NULL,
    is_active           BIT(1) NULL,
    new_from            datetime NULL,
    new_to              datetime NULL,
    deleted_at          datetime NULL,
    created_at          datetime NULL,
    updated_at          datetime NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-17
CREATE TABLE users
(
    id         INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(50)  NOT NULL,
    last_name  VARCHAR(50)  NOT NULL,
    email      VARCHAR(100) NOT NULL,
    phone      VARCHAR(20) NULL,
    password   VARCHAR(255) NOT NULL,
    enabled    BIT(1)       NOT NULL,
    `role`     VARCHAR(255) NOT NULL,
    last_login datetime NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-18
CREATE TABLE variation_values
(
    id           INT AUTO_INCREMENT NOT NULL,
    label        VARCHAR(255) NOT NULL,
    variation_id INT          NOT NULL,
    value        VARCHAR(255) NULL,
    position     INT NULL,
    created_at   datetime NULL,
    updated_at   datetime NULL,
    CONSTRAINT pk_variation_values PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-19
CREATE TABLE variations
(
    id         INT AUTO_INCREMENT NOT NULL,
    name       VARCHAR(255) NOT NULL,
    type       VARCHAR(255) NOT NULL,
    is_global  BIT(1) NULL,
    position   INT NULL,
    deleted_at datetime NULL,
    created_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT pk_variations PRIMARY KEY (id)
);

-- changeset Vu:1748796327079-20
ALTER TABLE order_product_variation_values
    ADD CONSTRAINT opv_variation_unique UNIQUE (order_product_variation_id, variation_value_id);

-- changeset Vu:1748796327079-21
ALTER TABLE invalidated_token
    ADD CONSTRAINT uc_invalidated_token_email UNIQUE (email);

-- changeset Vu:1748796327079-22
ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

-- changeset Vu:1748796327079-23
ALTER TABLE categories
    ADD CONSTRAINT FK_CATEGORIES_ON_PARENT FOREIGN KEY (parent_id) REFERENCES categories (id);

-- changeset Vu:1748796327079-24
ALTER TABLE entity_files
    ADD CONSTRAINT FK_ENTITY_FILES_ON_FILE FOREIGN KEY (file_id) REFERENCES files (id);

-- changeset Vu:1748796327079-25
ALTER TABLE option_values
    ADD CONSTRAINT FK_OPTION_VALUES_ON_OPTION FOREIGN KEY (option_id) REFERENCES options (id);

-- changeset Vu:1748796327079-26
ALTER TABLE order_products
    ADD CONSTRAINT FK_ORDER_PRODUCTS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE;

-- changeset Vu:1748796327079-27
ALTER TABLE order_products
    ADD CONSTRAINT FK_ORDER_PRODUCTS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE;

-- changeset Vu:1748796327079-28
ALTER TABLE order_products
    ADD CONSTRAINT FK_ORDER_PRODUCTS_ON_PRODUCT_VARIANT FOREIGN KEY (product_variant_id) REFERENCES product_variants (id) ON DELETE SET NULL;

-- changeset Vu:1748796327079-29
ALTER TABLE order_product_variations
    ADD CONSTRAINT FK_ORDER_PRODUCT_VARIATIONS_ON_ORDER_PRODUCT FOREIGN KEY (order_product_id) REFERENCES order_products (id) ON DELETE CASCADE;

-- changeset Vu:1748796327079-30
ALTER TABLE order_product_variations
    ADD CONSTRAINT FK_ORDER_PRODUCT_VARIATIONS_ON_VARIATION FOREIGN KEY (variation_id) REFERENCES variations (id) ON DELETE CASCADE;

-- changeset Vu:1748796327079-31
ALTER TABLE order_product_variation_values
    ADD CONSTRAINT FK_ORDER_PRODUCT_VARIATION_VALUES_ON_ORDER_PRODUCT_VARIATION FOREIGN KEY (order_product_variation_id) REFERENCES order_product_variations (id) ON DELETE CASCADE;

-- changeset Vu:1748796327079-32
ALTER TABLE order_product_variation_values
    ADD CONSTRAINT FK_ORDER_PRODUCT_VARIATION_VALUES_ON_VARIATION_VALUE FOREIGN KEY (variation_value_id) REFERENCES variation_values (id) ON DELETE CASCADE;

-- changeset Vu:1748796327079-33
ALTER TABLE products
    ADD CONSTRAINT FK_PRODUCTS_ON_BRAND FOREIGN KEY (brand_id) REFERENCES brands (id);

-- changeset Vu:1748796327079-34
ALTER TABLE product_variants
    ADD CONSTRAINT FK_PRODUCT_VARIANTS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);

-- changeset Vu:1748796327079-35
ALTER TABLE variation_values
    ADD CONSTRAINT FK_VARIATION_VALUES_ON_VARIATION FOREIGN KEY (variation_id) REFERENCES variations (id) ON DELETE CASCADE;

-- changeset Vu:1748796327079-36
ALTER TABLE product_categories
    ADD CONSTRAINT fk_procat_on_category FOREIGN KEY (category_id) REFERENCES categories (id);

-- changeset Vu:1748796327079-37
ALTER TABLE product_categories
    ADD CONSTRAINT fk_procat_on_product FOREIGN KEY (product_id) REFERENCES products (id);

-- changeset Vu:1748796327079-38
ALTER TABLE product_options
    ADD CONSTRAINT fk_proopt_on_option FOREIGN KEY (option_id) REFERENCES options (id);

-- changeset Vu:1748796327079-39
ALTER TABLE product_options
    ADD CONSTRAINT fk_proopt_on_product FOREIGN KEY (product_id) REFERENCES products (id);

-- changeset Vu:1748796327079-40
ALTER TABLE product_variations
    ADD CONSTRAINT fk_provar_on_product FOREIGN KEY (product_id) REFERENCES products (id);

-- changeset Vu:1748796327079-41
ALTER TABLE product_variations
    ADD CONSTRAINT fk_provar_on_variation FOREIGN KEY (variation_id) REFERENCES variations (id);

