-- V1__create_app_users_table.sql
CREATE TABLE IF NOT EXISTS app_users (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  gender VARCHAR(255) NOT NULL,
  password VARCHAR(255),
  date_of_birth VARCHAR(255) NOT NULL,
  address VARCHAR(255) NOT NULL,
  state_of_origin VARCHAR(255) NOT NULL,
  account_number VARCHAR(255) NOT NULL,
  phone_number VARCHAR(255) NOT NULL,
  status VARCHAR(255),
  created_on TIMESTAMP NOT NULL,
  modified_at TIMESTAMP
);


