-- SQL script to insert data
INSERT INTO author (born_date, born_place, died_date, died_place, name)
VALUES
    ('1960-01-01', 'Place of Birth 1', '2000-01-01', 'Place of Death 1', 'Author 1'),
    ('1970-02-02', 'Place of Birth 2', '2010-02-02', 'Place of Death 2', 'Author 2');

INSERT INTO book (avaiability, isbn, price, publisher, stock_avaiable, tittle)
VALUES
    (TRUE, '1234567890', 29.99, 'Publisher 1', 100, 'Book 1'),
    (FALSE, '0987654321', 19.99, 'Publisher 2', 50, 'Book 2');

INSERT INTO book_author (book_id, author_id)
VALUES
    (1, 1),
    (2, 2);

INSERT INTO language (code, name, native_name)
VALUES
    ('en', 'English', 'English'),
    ('fr', 'French', 'Français');

INSERT INTO book_language (book_id, language_id)
VALUES
    (1, 1),
    (2, 2);