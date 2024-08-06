INSERT INTO sensores (id, nombre, lugar, unidad) VALUES (10, 'Sensor 1', 'Sala de estar', 'Celsius');
INSERT INTO sensores (id, nombre, lugar, unidad) VALUES (11, 'Sensor 2', 'Cocina', 'Farenheit');
INSERT INTO sensores (id, nombre, lugar, unidad) VALUES (12, 'Sensor 3', 'Dormitorio', 'Kelvin');

-- Lecturas para Sensor 1
INSERT INTO lecturas (id, sensor_id, fecha, temperatura) VALUES (11, 10, '2024-07-15 11:00:00', 24.1);

-- Lecturas para Sensor 2
INSERT INTO lecturas (id, sensor_id, fecha, temperatura) VALUES (12, 11, '2024-07-15 10:30:00', 75.3);
INSERT INTO lecturas (id, sensor_id, fecha, temperatura) VALUES (13, 11, '2024-07-15 11:30:00', 76.8);

-- Lecturas para Sensor 3
INSERT INTO lecturas (id, sensor_id, fecha, temperatura) VALUES (14, 12, '2024-07-15 10:45:00', 298.5);
INSERT INTO lecturas (id, sensor_id, fecha, temperatura) VALUES (15, 12, '2024-07-15 11:45:00', 299.0);
INSERT INTO lecturas (id, sensor_id, fecha, temperatura) VALUES (16, 12, '2024-07-15 11:45:00', 299.0);