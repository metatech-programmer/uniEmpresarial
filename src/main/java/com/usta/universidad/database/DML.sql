----------------------------------- Universidades
INSERT INTO
    UNIVERSIDADES (
        nit_universidad,
        nombre_universidad,
        fecha_fundacion,
        direccion_universidad,
        photo_universidad,
        estado_universidad
    )
VALUES
    (
        '1234567890123',
        'Universidad A',
        '2020-01-15',
        'Calle A',
        'imagen_a.jpg',
        true
    ),
    (
        '9876543210987',
        'Universidad B',
        '2015-06-30',
        'Calle B',
        'imagen_b.jpg',
        true
    ),
    (
        '5678901234567',
        'Universidad C',
        '2018-11-20',
        'Calle C',
        'imagen_c.jpg',
        true
    ),
    (
        '9012345678901',
        'Universidad D',
        '2005-03-10',
        'Calle D',
        'imagen_d.jpg',
        true
    ),
    (
        '3456789012345',
        'Universidad E',
        '2012-09-05',
        'Calle E',
        'imagen_e.jpg',
        true
    );

----------------------------------- Telefonos
INSERT INTO
    TELEFONOS (
        numero_telefono,
        estado_telefono,
        id_universidad
    )
VALUES
    ('123-456-7890', true, 1),
    ('987-654-3210', true, 2),
    ('567-890-1234', true, 3),
    ('901-234-5678', true, 4),
    ('345-678-9012', true, 5);

----------------------------------- Seccionales
INSERT INTO
    SECCIONALES (
        nombre_seccional,
        cantidad_facultad_seccional,
        estado_seccional,
        photo_seccional,
        id_universidad
    )
VALUES
    ('Seccional A', 3, true, 'imagen_a.jpg', 1),
    ('Seccional B', 2, true, 'imagen_b.jpg', 2),
    ('Seccional C', 4, true, 'imagen_c.jpg', 3),
    ('Seccional D', 5, true, 'imagen_d.jpg', 4),
    ('Seccional E', 3, true, 'imagen_e.jpg', 5);

----------------------------------- Directores
INSERT INTO
    DIRECTORES (
        documento_director,
        nombre_director,
        apellido_director,
        estado_director,
        id_universidad
    )
VALUES
    ('12345678', 'Juan', 'Pérez', true, 1),
    ('98765432', 'María', 'Gómez', true, 2),
    ('56789012', 'Carlos', 'López', true, 3),
    ('90123456', 'Ana', 'Martínez', true, 4),
    ('34567890', 'Pedro', 'Rodríguez', true, 5);