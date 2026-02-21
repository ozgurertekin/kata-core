INSERT INTO product (id, name, price_amount)
VALUES (1, 'Laptop Stand', 29.90);
INSERT INTO product (id, name, price_amount)
VALUES (2, 'Wireless Mouse', 19.99);
INSERT INTO product (id, name, price_amount)
VALUES (3, 'Mechanical Keyboard', 89.50);
INSERT INTO product (id, name, price_amount)
VALUES (4, 'USB-C Hub', 34.00);
INSERT INTO product (id, name, price_amount)
VALUES (5, 'Noise Cancelling Headphones', 129.00);

INSERT INTO offer (id, product_id, start_date, end_date, time_zone, discount_type, amount)
VALUES (1, 1, '2026-02-01T00:00:00Z', '2026-03-01T00:00:00Z', 'Europe/Berlin', 'PERCENTAGE', 10.00);
INSERT INTO offer (id, product_id, start_date, end_date, time_zone, discount_type, amount)
VALUES (2, 3, '2026-02-10T00:00:00Z', '2026-03-10T00:00:00Z', 'Europe/Berlin', 'EXACT_PRICE', 59.99);
INSERT INTO offer (id, product_id, start_date, end_date, time_zone, discount_type, amount)
VALUES (3, 5, '2026-02-15T00:00:00Z', '2026-04-15T00:00:00Z', 'Europe/Berlin', 'PERCENTAGE', 15.50);
