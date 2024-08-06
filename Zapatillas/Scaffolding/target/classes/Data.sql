-- Marcas
INSERT INTO Brands (name) VALUES ('Nike');
INSERT INTO Brands (name) VALUES ('Adidas');
INSERT INTO Brands (name) VALUES ('Puma');

-- Zapatillas de Nike
INSERT INTO Shoes (model, description, size, gender, color, price, brand_id)
VALUES ('Air Max', 'Comfortable running shoes', 42, TRUE, 'Red', 120.50, 1);

INSERT INTO Shoes (model, description, size, gender, color, price, brand_id)
VALUES ('Air Jordan', 'Iconic basketball shoes', 44, TRUE, 'Black', 150.00, 1);

INSERT INTO Shoes (model, description, size, gender, color, price, brand_id)
VALUES ('Blazer', 'Vintage style casual shoes', 40, FALSE, 'White', 100.00, 1);

INSERT INTO Shoes (model, description, size, gender, color, price, brand_id)
VALUES ('React Infinity', 'Durable running shoes', 41, TRUE, 'Blue', 130.75, 1);

-- Zapatillas de Adidas
INSERT INTO Shoes (model, description, size, gender, color, price, brand_id)
VALUES ('UltraBoost', 'High-performance running shoes', 40, FALSE, 'Black', 180.75, 2);

INSERT INTO Shoes (model, description, size, gender, color, price, brand_id)
VALUES ('Superstar', 'Classic street style shoes', 42, TRUE, 'White', 85.50, 2);

INSERT INTO Shoes (model, description, size, gender, color, price, brand_id)
VALUES ('Stan Smith', 'Timeless tennis shoes', 39, FALSE, 'Green', 75.00, 2);

INSERT INTO Shoes (model, description, size, gender, color, price, brand_id)
VALUES ('NMD', 'Innovative casual shoes', 41, TRUE, 'Grey', 170.00, 2);

-- Zapatillas de Puma
INSERT INTO Shoes (model, description, size, gender, color, price, brand_id)
VALUES ('Suede Classic', 'Stylish casual shoes', 41, TRUE, 'Blue', 90.00, 3);

INSERT INTO Shoes (model, description, size, gender, color, price, brand_id)
VALUES ('RS-X', 'Retro-inspired running shoes', 43, TRUE, 'Red', 110.00, 3);

INSERT INTO Shoes (model, description, size, gender, color, price, brand_id)
VALUES ('Cali', 'Casual west coast shoes', 38, FALSE, 'White', 95.00, 3);

INSERT INTO Shoes (model, description, size, gender, color, price, brand_id)
VALUES ('Future Rider', 'Sporty casual shoes', 40, TRUE, 'Black', 100.00, 3);
