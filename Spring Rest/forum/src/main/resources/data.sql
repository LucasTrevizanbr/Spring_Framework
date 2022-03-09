INSERT INTO USUARIO(nome, email, senha) VALUES('Maçarolo', 'aluno1@email.com', '$2a$10$hE6MO7t6W0W1jZgwnMXzBuAEwYANf2S0ZTwFNZGn2j1BpAyXknDQS');
INSERT INTO USUARIO(nome, email, senha) VALUES('Gilberto', 'aluno2@email.com', '$2a$10$RXSxoL1ZEiMGfx49vG2sJuMBnFB.IVQ2zLUCwGaAN4eglyWlfxMK6');
INSERT INTO USUARIO(nome, email, senha) VALUES('Evelyn', 'aluno3@email.com', '123456');
INSERT INTO USUARIO(nome, email, senha) VALUES('Marly Marley', 'aluno4@email.com', '123456');
INSERT INTO USUARIO(nome, email, senha) VALUES('Jarlos', 'aluno5@email.com', '123456');
INSERT INTO USUARIO(nome, email, senha) VALUES('Marlos', 'aluno6@email.com', '123456');
INSERT INTO USUARIO(nome, email, senha) VALUES('Myal', 'aluno7@email.com', '123456');
INSERT INTO USUARIO(nome, email, senha) VALUES('Jabulany', 'aluno8@email.com', '123456');
INSERT INTO USUARIO(nome, email, senha) VALUES('Derci', 'aluno9@email.com', '123456');
INSERT INTO USUARIO(nome, email, senha) VALUES('Amourant', 'aluno10@email.com', '123456');
INSERT INTO USUARIO(nome, email, senha) VALUES('Manson', 'aluno11@email.com', '123456');
INSERT INTO USUARIO(nome, email, senha) VALUES('Charles', 'aluno12@email.com', '123456');

INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');
INSERT INTO CURSO(nome, categoria) VALUES('Ansible', 'Devops');
INSERT INTO CURSO(nome, categoria) VALUES('Vagrant', 'Devops');
INSERT INTO CURSO(nome, categoria) VALUES('Kotlin', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('Figma', 'Front-end');
INSERT INTO CURSO(nome, categoria) VALUES('CSS 3', 'Front-end');
INSERT INTO CURSO(nome, categoria) VALUES('RabbitMQ', 'Devops');
INSERT INTO CURSO(nome, categoria) VALUES('Spring Data Jpa', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('UML', 'Arquitetura de software');
INSERT INTO CURSO(nome, categoria) VALUES('Patterns', 'Arquitetura de software');
INSERT INTO CURSO(nome, categoria) VALUES('Angular', 'Front-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);