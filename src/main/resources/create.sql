DROP TABLE IF EXISTS livros;

CREATE TABLE livros (
  codigo SERIAL PRIMARY KEY,
  titulo VARCHAR(255),
  autor VARCHAR(255),
  ano INT
);
