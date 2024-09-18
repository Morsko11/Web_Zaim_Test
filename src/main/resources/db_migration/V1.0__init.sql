CREATE TABLE credits (
                         id SERIAL PRIMARY KEY,
                         payment_string VARCHAR(255) NOT NULL,
                         first_payment_date DATE NOT NULL
);