-- liquibase formatted sql

-- changeset Alex:1-create-book-table
CREATE TABLE book (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    available BOOLEAN DEFAULT FALSE
);

-- changeset Alex:2-create-user-table
CREATE TABLE app_user (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE
);

-- changeset Alex:3-create-borrow-table
CREATE TABLE borrow_record (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    borrow_date TIMESTAMP,
    return_date TIMESTAMP
);

ALTER TABLE borrow_record
  ADD CONSTRAINT fk_user_borrow
  FOREIGN KEY (user_id) REFERENCES app_user(id);

ALTER TABLE borrow_record
  ADD CONSTRAINT fk_book_borrow
  FOREIGN KEY (book_id) REFERENCES book(id);
