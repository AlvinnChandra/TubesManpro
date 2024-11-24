CREATE TABLE mesin (
    id SERIAL PRIMARY KEY,
    merek VARCHAR(255) UNIQUE NOT NULL,
    kapasitas INT NOT NULL,
    tarif INT NOT NULL
);

INSERT INTO mesin (merek, kapasitas, tarif) VALUES ('LG', '20', '15000');
INSERT INTO mesin (merek, kapasitas, tarif) VALUES ('Panasonic', '10', '10000');