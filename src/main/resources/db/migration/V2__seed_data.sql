
INSERT INTO product (name, price) VALUES
  ('Skrivbord', 1999.00),
  ('Kontorsstol', 1499.00),
  ('Bokhylla', 899.00),
  ('Skrivbordslampa', 349.00),
  ('Förvaringslåda', 199.00);

INSERT INTO customer (name, email) VALUES
  ('Anna Andersson', 'anna.andersson@example.com'),
  ('Erik Svensson', 'erik.svensson@example.com'),
  ('Maria Karlsson', 'maria.karlsson@example.com');

INSERT INTO orders (created_at, total_amount, customer_id) VALUES
  (now(), 3498.00, 1),
  (now(), 349.00, 1),
  (now(), 899.00, 2);

INSERT INTO order_product (order_id, product_id) VALUES
  (1, 1), -- order 1: Skrivbord
  (1, 2), -- order 1: Kontorsstol
  (2, 4), -- order 2: Skrivbordslampa
  (3, 3); -- order 3: Bokhylla
