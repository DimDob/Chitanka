DROP TABLE IF EXISTS books CASCADE;
GRANT ALL PRIVILEGES ON DATABASE Chitanka TO postgres;

CREATE TABLE IF NOT EXISTS books (
    id UUID PRIMARY KEY NOT NULL,
    subject TEXT,
    link TEXT,
    author TEXT,
    title TEXT
);
