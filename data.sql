CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

INSERT INTO users (username, password, role) VALUES ('admin', 'admin123', 'admin');
INSERT INTO users (username, password, role) VALUES ('pemilik', 'pemilik123', 'pemilik');


CREATE TABLE pegawai (
    id SERIAL PRIMARY KEY, 
    nama VARCHAR(100) NOT NULL,
    posisi VARCHAR(100) NOT NULL
);