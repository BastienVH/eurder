create sequence item_id_seq start with 1;
create sequence order_id_seq start with 1;
create sequence address_id_seq start with 1;
create sequence order_item_id_seq start with 1;

CREATE TABLE "Item"(
                       "id" INTEGER NOT NULL DEFAULT nextval('item_id_seq'),
                       "name" VARCHAR(255) NOT NULL,
                       "description" VARCHAR(255) NOT NULL,
                       "amount_of_stock" INTEGER NOT NULL,
                       "price" DECIMAL(8, 2) NOT NULL,
                       "currency" VARCHAR(255) CHECK
                           ("currency" IN('')) NOT NULL
);
ALTER TABLE
    "Item" ADD PRIMARY KEY("id");
CREATE TABLE "order"(
                        "id" INTEGER NOT NULL DEFAULT nextval('order_id_seq'),
                        "customer_id" UUID NOT NULL,
                        "total_price" DECIMAL(8, 2) NOT NULL,
                        "currency" VARCHAR(255) CHECK
                            ("currency" IN('')) NOT NULL
);
ALTER TABLE
    "order" ADD PRIMARY KEY("id");
CREATE TABLE "customer"(
                           "id" UUID NOT NULL,
                           "first_name" VARCHAR(255) NOT NULL,
                           "last_name" VARCHAR(255) NOT NULL,
                           "email" VARCHAR(255) NOT NULL,
                           "address_id" INTEGER NOT NULL,
                           "phone_number_id" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "customer" ADD PRIMARY KEY("id");
CREATE TABLE "address"(
                          "id" INTEGER NOT NULL DEFAULT nextval('address_id_seq'),
                          "street_name" VARCHAR(255) NOT NULL,
                          "house_number" VARCHAR(255) NOT NULL,
                          "postal_code" VARCHAR(255) NOT NULL,
                          "country" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "address" ADD PRIMARY KEY("id");
CREATE TABLE "order_item"(
                             "id" INTEGER NOT NULL DEFAULT nextval('order_item_id_seq'),
                             "item_id" INTEGER NOT NULL,
                             "amount_to_buy" INTEGER NOT NULL,
                             "delivery_date" DATE NOT NULL,
                             "order_id" INTEGER NOT NULL,
                             "price" DECIMAL(8, 2) NOT NULL,
                             "currency" VARCHAR(255) CHECK
                                 ("currency" IN('')) NOT NULL
);
ALTER TABLE
    "order_item" ADD PRIMARY KEY("id");
ALTER TABLE
    "order_item" ADD CONSTRAINT "order_item_item_id_foreign" FOREIGN KEY("item_id") REFERENCES "Item"("id");
ALTER TABLE
    "order_item" ADD CONSTRAINT "order_item_order_id_foreign" FOREIGN KEY("order_id") REFERENCES "order"("id");
ALTER TABLE
    "customer" ADD CONSTRAINT "customer_address_id_foreign" FOREIGN KEY("address_id") REFERENCES "address"("id");
ALTER TABLE
    "order" ADD CONSTRAINT "order_customer_id_foreign" FOREIGN KEY("customer_id") REFERENCES "customer"("id");