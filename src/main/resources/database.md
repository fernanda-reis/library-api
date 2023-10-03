INSERT INTO authors (name, country)
VALUES
('William Shakespeare', 'Inglaterra'),
('Jane Austen', 'Inglaterra'),
('Leo Tolstoy', 'Rússia'),
('Gabriel García Márquez', 'Colômbia'),
('Haruki Murakami', 'Japão');
INSERT INTO categories (name)
VALUES
('Drama'),
('Romance'),
('Mistério'),
('Fantasia');

INSERT INTO books (name, year_of_release, author_id, category_id)
VALUES
('Romeu e Julieta', 1597, 1, 2), -- Autor ID 1 (William Shakespeare), Categoria ID 2 (Romance)
('Hamlet', 1603, 1, 1);          -- Autor ID 1 (William Shakespeare), Categoria ID 1 (Drama)
INSERT INTO books (name, year_of_release, author_id, category_id)
VALUES
('Orgulho e Preconceito', 1813, 2, 2), -- Autor ID 2 (Jane Austen), Categoria ID 2 (Romance)
('Razão e Sensibilidade', 1811, 2, 2); -- Autor ID 2 (Jane Austen), Categoria ID 2 (Romance)
INSERT INTO books (name, year_of_release, author_id, category_id)
VALUES
('Guerra e Paz', 1869, 3, 1),  -- Autor ID 3 (Leo Tolstoy), Categoria ID 1 (Drama)
('Anna Karenina', 1877, 3, 2); -- Autor ID 3 (Leo Tolstoy), Categoria ID 2 (Romance)
INSERT INTO books (name, year_of_release, author_id, category_id)
VALUES
('Cem Anos de Solidão', 1967, 4, 2), -- Autor ID 4 (Gabriel García Márquez), Categoria ID 2 (Romance)
('O Outono do Patriarca', 1975, 4, 3); -- Autor ID 4 (Gabriel García Márquez), Categoria ID 3 (Fantasia)
INSERT INTO books (name, year_of_release, author_id, category_id)
VALUES
('Norwegian Wood', 1987, 5, 2), -- Autor ID 5 (Haruki Murakami), Categoria ID 2 (Romance)
('1Q84', 2009, 5, 3);          -- Autor ID 5 (Haruki Murakami), Categoria ID 3 (Fantasia)


