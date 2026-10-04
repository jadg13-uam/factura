INSERT INTO categoria (nombre, activa) VALUES
                                           ('Bebidas', TRUE),
                                           ('Alimentos', TRUE),
                                           ('Limpieza', TRUE),
                                           ('Tecnología', TRUE);

INSERT INTO producto (
    codigo,
    nombre,
    precio_venta,
    existencia,
    categoria_id
) VALUES
      ('P001', 'Café', 85.00, 20, 1),
      ('P002', 'Arroz', 45.00, 50, 2),
      ('P003', 'Jabón', 35.00, 30, 3),
      ('P004', 'Teclado', 450.00, 10, 4);