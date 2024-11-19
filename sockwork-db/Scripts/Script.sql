DROP TABLE IF EXISTS t_accounts;


CREATE TABLE t_accounts (
    account_id INT GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(255),
    password VARCHAR (16),
    CONSTRAINT t_accounts_pkey PRIMARY KEY (account_id)
);


SELECT * FROM t_accounts;

--TRUNCATE TABLE t_accounts;
