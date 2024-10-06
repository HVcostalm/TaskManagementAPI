INSERT INTO tb_funcionario(login_funcionario, email, senha, status, nivel_permissao, projeto_id) values ('TS_Heitor', 'heitor@gmail.com', 'TS_0001', true, 2, null);

INSERT INTO tb_funcionario(login_funcionario, email, senha, status, nivel_permissao, projeto_id) values ('TS_Paulo', 'paulo@gmail.com', 'TS_0002', true, 1, null);

INSERT INTO tb_funcionario(login_funcionario, email, senha, status, nivel_permissao, projeto_id) values ('TS_Isana', 'isana@gmail.com', 'TS_0003', true, 0, null);

INSERT INTO tb_projeto(nome_projeto, descricao_projeto, data_prevista_entrega, data_conclusao, status) values ('Aplicativo Mobile', 'Fazer um aplicativo mobile para gerenciar uma pet shop', '30-10-24', null, true);

UPDATE tb_funcionario set projeto_id = 1 where id_funcionario = 2;

INSERT INTO tb_funcionario_projeto(data_participacao_inicial, data_participacao_final, projeto_id, funcionario_id) values('04-10-24', null, 1, 2);

INSERT INTO tb_tarefa(nome_tarefa, descricao_tarefa, prioridade, status, projeto_id) values ('Banco de Dados', 'Construir um modelo de entidade-relacionamento no banco de dados', 2, 0, 1);