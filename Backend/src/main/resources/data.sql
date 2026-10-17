INSERT INTO categoria (nombre) VALUES ('Backend')
    ON CONFLICT (nombre) DO NOTHING;
INSERT INTO categoria (nombre) VALUES ('Frontend')
    ON CONFLICT (nombre) DO NOTHING;
INSERT INTO categoria (nombre) VALUES ('Bases de datos')
    ON CONFLICT (nombre) DO NOTHING;
INSERT INTO categoria (nombre) VALUES ('Algoritmos')
    ON CONFLICT (nombre) DO NOTHING;