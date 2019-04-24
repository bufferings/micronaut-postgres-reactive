CREATE TABLE IF NOT EXISTS users (
  id SERIAL,
  first_name VARCHAR(30),
  last_name VARCHAR(30),
  CONSTRAINT pk_user PRIMARY KEY (id)
);
