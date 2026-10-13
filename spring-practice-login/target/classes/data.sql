INSERT INTO users (username, password, role) VALUES
('admin', '$2a$10$pljT.nAnONfEMlooxVh4BuNUjBGi1qHCAZvZniYCZPGnDV.1ZZw0W', 'ADMIN'),
('user01', '$2a$10$aT7xJRAIF0m.9bV3EGr8BuU5.sXx.vu/u7OQGxhnrZ3adWpH1igFG', 'USER'),
('user02', '$2a$10$nnY7Ebb2Bit43oU9cgCPQuZ9qjvqElPTJFP5IPcWJFDdoy.h29V8m', 'USER'),
('user03', '$2a$10$POFagRCRDMuULQdlXbXl3e6c.FdTXJOhYzkNu6ENHckM408FjmogC', 'USER'),
('user04', '$2a$10$9YQnUTLTWCmqDKq5Z5G.KuM20IuWQKyHPnZ3tV8TOKnknYFPqp7Sq', 'USER'),
('user05', '$2a$10$as6ZqeMyJ8EwlpUX.gwRIOSPBFN2MxRLOMTxOpcJGbSEdDq5faKbS', 'USER'),
('user06', '$2a$10$5YweHIe23LuiZ7rBpoXtMOP0D80WbaKt/RoNpVXSi4Znevzfy8cBy', 'USER'),
('user07', '$2a$10$qafDYdTjywQxRgQrP6QjW.iBXUhWbg9SbnfEUi7pD6GiJhvGGW1y6', 'USER'),
('user08', '$2a$10$cFNBraS5.j55H0XL9AUv4Ob5IH4ymANg7rBZleMWO6LsHmNyvZzZK', 'USER'),
('user09', '$2a$10$GhVNtUHlF3IYKwKqPQvjNen.umLNPoQN0DtAim4ue27h0SPe4L4za', 'USER'),
('user10', '$2a$10$/YDP4Tvyk1tsKlbbcYUcWu2J.b7Jl3wiZIjdrcgsynn/msf3TxPTi', 'USER')
ON DUPLICATE KEY UPDATE password=VALUES(password), role=VALUES(role);
